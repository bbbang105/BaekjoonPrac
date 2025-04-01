import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * [조건]
 * 1. 연구소의 크기는 N * M이다.
 * 2. N과 M은 3이상 8이하이다.
 * 3. 0은 빈 칸, 1은 벽, 2는 바이러스가 있는 위치이다.
 * 4. 바이러스의 개수는 2이상 10이하이다.
 * 5. 바이러스는 빈칸의 상하좌우로 계속 퍼져나갈 수 있다.(BFS)
 * 6. 벽은 3개를 세워야 한다.(백트래킹)
 * 
 * [풀이]
 * 1. 빈칸의 위치와 바이러스의 위치를 미리 저장해둔다.
 * 2. 백트래킹을 통해서 빈칸을 벽으로 바꾸어준다.
 * 3. 세운 벽의 개수가 3개가 되었다면 해당 그래프로 BFS를 시작하며, 최대 안전 영역을 갱신한다.
 */
public class Main {
	
	private static final int[] DX = {-1, 1, 0, 0};
	private static final int[] DY = {0, 0, -1, 1};
	
	private static int R;
	private static int C;
	private static int[][] graph;
	private static List<Location> blanks;
	private static List<Location> viruses;
	private static boolean[] visitedBlanks;
	private static boolean[][] isVisited;
	private static int result;
	
	public static void main(String[] args) throws IOException {
		init();
		findResult(0, 0);
		System.out.println(result);
	}
	
	/**
	 * 결과를 도출하는 메서드.
	 * 백트래킹을 활용한다.
	 */
	private static void findResult(int idx, int countOfWalls) {
		if (countOfWalls == 3) {
			bfs();
			countAndUpdateResult();
			return;
		}
		
		for (int index = idx; index < blanks.size(); index++) {
			if (!visitedBlanks[index]) {
				visitedBlanks[index] = true;
				graph[blanks.get(index).x][blanks.get(index).y] = 1;
				findResult(index + 1, countOfWalls + 1);
				visitedBlanks[index] = false;
				graph[blanks.get(index).x][blanks.get(index).y] = 0;
			}
		}
	}
	
	/**
	 * 바이러스를 퍼뜨리는 메서드.
	 */
	private static void bfs() {
		isVisited = new boolean[R][C];
		Queue<Location> q = new LinkedList<>();
		for (Location virus : viruses) {
			q.offer(virus);
		}
		
		Location cur;
		int x, y, nx, ny;
		while (!q.isEmpty()) {
			cur = q.poll();
			x = cur.x;
			y = cur.y;
			
			for (int directionIndex = 0; directionIndex < 4; directionIndex++) {
				nx = x + DX[directionIndex];
				ny = y + DY[directionIndex];
				if (canMove(nx, ny)) {
					isVisited[nx][ny] = true;
					q.offer(new Location(nx, ny));
				}
			}
		}
	}
	
	/**
	 * 안전 영역을 계산하고 갱신하는 메서드.
	 */
	private static void countAndUpdateResult() {
		int countOfSafetyAreas = 0;
		for (Location blank : blanks) {
			if (!isVisited[blank.x][blank.y]) {
				countOfSafetyAreas++;
			}
		}
		result = Math.max(result, countOfSafetyAreas - 3); // 벽 개수 3개는 제외
	}
	
	/**
	 * 다음 칸으로 이동할 수 있는지 여부를 반환하는 메서드.
	 */
	private static boolean canMove(int nx, int ny) {
		return  nx >= 0 && nx < R && ny >= 0 && ny < C && !isVisited[nx][ny] && graph[nx][ny] == 0;
	}
	
	/**
	 * 초기 세팅을 진행하는 메서드.
	 */
	private static void init() throws IOException {
		blanks = new ArrayList<>();
		viruses = new ArrayList<>();
		result = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		graph = new int[R][C];
		for (int rowIndex = 0; rowIndex < R; rowIndex++) {
			st = new StringTokenizer(br.readLine());
			int cur;
			for (int colIndex = 0; colIndex < C; colIndex++) {
				cur = Integer.parseInt(st.nextToken());
				if (cur == 0) {
					// 빈칸인 경우
					blanks.add(new Location(rowIndex, colIndex));
				}
				if (cur == 2) {
					// 바이러스인 경우
					viruses.add(new Location(rowIndex, colIndex));
				}
				graph[rowIndex][colIndex] = cur;
			}
		}
		visitedBlanks = new boolean[blanks.size()];
	}
	
	/**
	 * 위치 정보를 담은 클래스.
	 */
	static class Location {
		int x;
		int y;
		
		public Location(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
