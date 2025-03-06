import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * [조건]
 * 1. 사이즈는  N*N이다. N은 2이상 100이하의 정수이다.
 * 2. 100일 동안 치즈를 갉아 먹는데, X번째 날에는 맛있는 정도가 X인 칸을 먹는다.
 * 3. 맛있는 정도는 1부터 100까지의 정수이다.
 * 4. 치즈 덩어리는 상하좌우로 인접한 칸을 말한다.
 * 5. 덩어리 개수가 가장 많을 때의 개수를 구하라.
 * 
 * [풀이]
 * 1. 입력을 받음과 동시에 최대 맛있는 정도와 숫자를 저장해둔다. (중복 제거를 위해 Set 사용)
 * 2. 고유한 맛있는 정도를 기준으로 탐색을 시작한다.
 * 3. 방문하지 않았고, 현재 맛 정도보다 큰 숫자인 경우에만 이동이 가능하다!
 * 4. 덩어리가 몇 개인지 파악하고, 만약 더 크다면 값을 갱신한다.
 * 5. 최소 1 덩어리는 나올 수 있기 때문에 초기값을 1로 설정해둔다.
 */
public class Solution {
	
	// 4방향
	private static final int[] DX = {-1, 1, 0, 0};
	private static final int[] DY = {0, 0, -1, 1};
	// BFS 관련
	private static int[][] cheeses;
	private static boolean[][] isVisited;
	private static Queue<Cheese> q;
	private static int size;
	private static int maxBulks; // 최대 덩어리 수
	private static Set<Integer> uniqueTastes; // 고유한 맛의 정도
	private static int uniqueTaste; // 고유한 맛의 정도
	// 입출력 관련
	private static BufferedReader br;
	private static StringTokenizer st;
	private static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int testCaseNum = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= testCaseNum; testCase++) {
			init();
			findMaxBulks();
			sb.append("#").append(testCase).append(" ").append(maxBulks).append('\n');
		}
		System.out.println(sb);
	}
	
	/**
	 * BFS를 활용해 최대 덩어리 수를 찾는 메서드.
	 * 
	 */
	private static void findMaxBulks() {

		int countOfBulks; // 덩어리 개수
		for (int taste : uniqueTastes) {
			uniqueTaste = taste;
			countOfBulks = 0;
			
			for (int rowIndex = 0; rowIndex < size; rowIndex++) {
				for (int colIndex = 0; colIndex < size; colIndex++) {
					if (!isVisited[rowIndex][colIndex] && cheeses[rowIndex][colIndex] > uniqueTaste) {
						bfs(new Cheese(rowIndex, colIndex));
						countOfBulks++;
					}
				}
			}
			maxBulks = Math.max(maxBulks, countOfBulks);
			isVisited = new boolean[size][size];
		}
	}
	
	/**
	 * BFS
	 * 
	 */
	private static void bfs(Cheese cheese) {
		
		q = new LinkedList<>();
		q.offer(cheese);
		int x, y, nx, ny;
		while (!q.isEmpty()) {
			Cheese curCheese = q.poll();
			x = curCheese.x;
			y = curCheese.y;
			isVisited[x][y] = true;
			
			for (int directionIndex = 0; directionIndex < 4; directionIndex++) {
				nx = x + DX[directionIndex];
				ny = y + DY[directionIndex];
				if (canMove(nx, ny)) {
					isVisited[nx][ny] = true;
					q.offer(new Cheese(nx, ny));
				}
			}
		}
	}
	
	/**
	 * 이동 가능한지 여부를 반환하는 메서드.
	 * 
	 */
	private static boolean canMove(int nx, int ny) {
		return nx >= 0 && nx < size && ny >= 0 && ny < size && !isVisited[nx][ny] && cheeses[nx][ny] > uniqueTaste;
	}
	
	/**
	 * 초기 세팅을 진행하는 메서드.
	 * 
	 */
	private static void init() throws IOException {
		maxBulks = 1; // 초기값 1로 설정
		size = Integer.parseInt(br.readLine());
		cheeses = new int[size][size];
		isVisited = new boolean[size][size];
		uniqueTastes = new HashSet<>();
		
		int taste;
		for (int rowIndex = 0; rowIndex < size; rowIndex++) {
			st = new StringTokenizer(br.readLine());
			for (int colIndex = 0; colIndex < size; colIndex++) {
				taste = Integer.parseInt(st.nextToken());
				cheeses[rowIndex][colIndex] = taste;
				uniqueTastes.add(taste);
			}
		}
	}
	
	/**
	 * 치즈 좌표의 정보가 담긴 클래스. 
	 *
	 */
	static class Cheese {
		int x;
		int y;
		
		Cheese(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
