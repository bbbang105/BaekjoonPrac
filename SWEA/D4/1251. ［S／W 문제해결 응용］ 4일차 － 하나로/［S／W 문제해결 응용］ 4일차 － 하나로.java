import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * [조건]
 * 1. 모든 섬을 연결해야 한다.
 * 2. 환경 부담금 == 환경 부담 세율(E)와 해저터널 길이(L)의 제곱의 곱만큼 지불 / E * L ^ 2
 * 3. 총 환경 부담금을 최소로 지불, N개의 모든 섬을 연결할 수 있는 교통 시스템을 설계하라.
 * 4. 결과는 소수 첫째 자리에서 반올림하여 정수 형태로 출력하라.
 * 5. 섬의 개수 N은 1이상 1,000이하이다.
 * 6. X, Y 좌표는 0이상 1_000_000이하이다.
 * 7. 환경 부담 세율 실수 E는 0이상 1이하이다.
 *
 * [풀이]
 * 1. 간선이 따로 주어지지 않기 때문에, 모든 섬과 섬을 우선 연결하여 저장한다.
 * 2. 해당 값을 통해서 프림 알고리즘을 적용한다.
 * 
 */
public class Solution {
	
	private static int countOfIsland;
	private static double E;
	private static int[] rows;
	private static int[] cols;
	private static double result;
	private static List<Edge>[] graph;
	private static boolean[] isVisited;
	
	private static BufferedReader br;
	private static StringTokenizer st;
	private static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		sb = new StringBuilder();
		br = new BufferedReader(new InputStreamReader(System.in));
		int testCaseNum = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= testCaseNum; testCase++) {
			init();
			prim();
			sb.append("#").append(testCase).append(" ").append((long) Math.round(result)).append('\n');
		}
		System.out.println(sb);
	}
	
	/**
	 * 프림 알고리즘을 활용하여 결과를 도출하는 메서드.
	 */
	private static void prim() {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.offer(new Edge(1, 0));
		
		Edge edge;
		int cur;
		double weight;
		while (!pq.isEmpty()) {
			edge = pq.poll();
			cur = edge.vertex;
			weight = edge.weight;
			
			if (isVisited[cur]) {
				continue;
			}
			
			isVisited[cur] = true;
			result += E * Math.pow(weight, 2);
			
			for (Edge next : graph[cur]) {
				if (!isVisited[next.vertex]) {
					pq.offer(next);
				}
			}
		}
	}
	
	/**
	 * 초기 세팅을 진행하는 메서드.
	 */
	private static void init() throws IOException {
		countOfIsland = Integer.parseInt(br.readLine());
		rows = new int[countOfIsland + 1];
		cols = new int[countOfIsland + 1];
		result = 0.0;
		graph = new ArrayList[countOfIsland + 1];
		for (int index = 1; index <= countOfIsland; index++) {
			graph[index] = new ArrayList<>();
		}
		isVisited = new boolean[countOfIsland + 1];
		
		st = new StringTokenizer(br.readLine());
		for (int colIndex = 1; colIndex <= countOfIsland; colIndex++) {
			cols[colIndex] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int rowIndex = 1; rowIndex <= countOfIsland; rowIndex++) {
			rows[rowIndex] = Integer.parseInt(st.nextToken());
		}
		
		E = Double.parseDouble(br.readLine());
		
		double weight;
		for (int from = 1; from < countOfIsland; from++) {
			for (int to = from + 1; to <= countOfIsland; to++) {
				weight = calculateEuclideanDistance(rows[from], cols[from], rows[to], cols[to]);
				graph[from].add(new Edge(to, weight));
				graph[to].add(new Edge(from, weight));
			}
		}
	}
	
	/**
	 * 유클리디안 거리를 계산하여 반환하는 메서드.
	 */
	private static double calculateEuclideanDistance(int x1, int y1, int x2, int y2) {
	    return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
	}
	
	/**
	 * 간선 정보를 저장하는 클래스.
	 * 가중치를 기준으로 오름차순 정렬된다. 
	 */
	static class Edge implements Comparable<Edge> {
		int vertex;
		double weight;
		
		Edge(int vertex, double weight) {
			this.vertex = vertex;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.weight, o.weight);
		}
	}
}
