import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * [조건]
 * 1. 사이즈 N은 1이상 100이하이다.
 * 2. 거리 정보가 2차원 배열로 들어온다.
 * 3. 출발지 == 좌상단 / 도착지 == 우하단
 * 4. 상하좌우로 이동가능하며, 배열에 있는 값만큼 시간을 소요해 복구할 수 있다.
 * 5. 출발지 -> 도착지의 최단 거리를 출력하라.
 * 
 * [풀이]
 * 1. 다익스트라 알고리즘을 활용한다.(우선순위 큐 활용)
 * 2. 현재 최단 거리 저장 배열 dist를 2차원 배열로 만든다.
 */
public class Solution {
	
	private static final int[] DX = {-1, 1, 0, 0};
	private static final int[] DY = {0, 0, -1, 1};
	private static final int INF = 1_000_000_000;
	
	private static BufferedReader br;
	
	private static int size;
	private static int[][] graph;
	private static int[][] dist;
	private static int result;
	
	/**
	 * 메인 메서드.
	 */
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		br = new BufferedReader(new InputStreamReader(System.in));
		int testCaseNum = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= testCaseNum; testCase++) {
			init();
			dijkstra();
			result = dist[size - 1][size - 1];
			sb.append("#").append(testCase).append(" ").append(result).append('\n');
		}
		System.out.println(sb);
	}
	
	/**
	 * 다익스트라 알고리즘. 우선순위 큐를 활용한다.
	 */
	private static void dijkstra() {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.offer(new Edge(0, 0, 0)); // 시작점을 넣어줌
		boolean[][] isVisited = new boolean[size][size];
		
		Edge edge;
		int x, y;
		while (!pq.isEmpty()) {
			edge = pq.poll();
			x = edge.x;
			y = edge.y;
			
			if (isVisited[x][y]) {
				continue;
			}
			isVisited[x][y] = true;
			
			int nx, ny, weight;
			for (int directionIndex = 0; directionIndex < 4; directionIndex++) {
				nx = x + DX[directionIndex];
				ny = y + DY[directionIndex];
				if (canMove(nx, ny)) {
					weight = graph[nx][ny];
					if (dist[nx][ny] > dist[x][y] + weight) {
						dist[nx][ny] = dist[x][y] + weight;
						pq.offer(new Edge(nx, ny, dist[nx][ny]));
					}
				}
			}
		}
	}
	
	/**
	 * 이동 가능 여부를 반환하는 메서드.
	 */
	private static boolean canMove(int x, int y) {
		return x >= 0 && x < size && y >= 0 && y < size;
	}
	
	/**
	 * 초기 세팅을 진행하는 메서드.
	 */
	private static void init() throws IOException {
		size = Integer.parseInt(br.readLine());
		graph = new int[size][size];
		dist = new int[size][size];
		for (int[] row : dist) {
			Arrays.fill(row, INF);
		}
		dist[0][0] = 0; // 출발지
		result = 0;
		
		String line;
		for (int rowIndex = 0; rowIndex < size; rowIndex++) {
			line = br.readLine();
			for (int colIndex = 0; colIndex < size; colIndex++) {
				graph[rowIndex][colIndex] = line.charAt(colIndex) - '0';
			}
		}
	}
	
	/**
	 * 간선 정보를 담은 클래스.
	 * 가중치를 기준으로 오름차순 정렬된다.
	 */
	static class Edge implements Comparable<Edge> {
		int x;
		int y;
		int weight;
		
		Edge (int x, int y, int weight) {
			this.x = x;
			this.y = y;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}
}
