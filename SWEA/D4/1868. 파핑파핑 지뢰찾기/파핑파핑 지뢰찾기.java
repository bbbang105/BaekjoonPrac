import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * [조건]
 * 1. 사이즈  N은 1이상 300이하이다.
 * 2. '*'은 지뢰, '.'은 지뢰 없음을 의미한다.
 * 3. 특정 좌표를 클릭하면 8방향에 있는 지뢰를 탐색할 수 있다.
 * 4. 만약 0이라면, 해당 좌표에서부터 8방향을 다시 탐색하여 숫자를 표시할 수 있다.
 * 5. 모든 칸에 숫자를 표시할 수 있는 최소 횟수를 출력하라.
 *
 * [풀이]
 * 0. 지뢰가 있는 곳을 -1, 없는 곳을 0으로 둔다.
 * 1. 지뢰를 기준으로 우선 탐색하여 주변 8방향의 값을 +1씩 해준다.
 * 2. 전체 그래프를 탐색하며, 아래의 값들을 저장한다.
 * 	2-1. 0인 좌표들만 큐에 저장한다.
 *  2-2. 0 초과 (1, 2..) 인 좌표들만 큐에 저장한다. 이때 2-1과 다른 큐이다.
 *  2-3. 지뢰는 저장하지 않는다.
 * 3. 0의 좌표에서 BFS를 호출하며, 방문 처리를 하고 호출 횟수마다 클릭 수 +1을 한다.
 *  3-1. 8방향 내에 0이 있다면 큐에 넣어준다.
 *  3-2. 지뢰를 제외한 모든 곳에 방문 처리를 한다.
 *  3-3. BFS를 한 번 호출할 때 마다 클릭 수가 +1 된다.
 * 4. 3번 탐색을 통해서 방문하지 못 한 0초과의 숫자들을 클릭 수에 더해서 최종 값을 출력한다.
 */
public class Solution {
	
	// 8방향
	private static final int[] DX = {-1, 1, 0, 0, -1, -1, 1, 1};
	private static final int[] DY = {0, 0, -1, 1, -1, 1, -1, 1};
	// 지뢰 찾기 보드
	private static int size;
	private static int[][] board; // 지뢰가 있는 곳 -1, 없는 곳 0
	private static boolean[][] isVisited;
	private static int countOfClicks;
	private static Queue<Location> bombs; // 지뢰 좌표들
	private static Queue<Location> zeroLocations; 	 // 0 좌표들
	private static Queue<Location> overZeroLocations; // 0 초과 좌표들
	private static Queue<Location> q; // BFS용 큐
	// 입출력 관련
	private static BufferedReader br;
	private static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int testCaseNum = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= testCaseNum; testCase++) {
			init();
			findCountOfClicks();
			sb.append("#").append(testCase).append(" ").append(countOfClicks).append('\n');
		}
		System.out.println(sb);
	}
	
	/**
	 * 최소 클릭 수를 찾는 메서드.
	 * 
	 */
	private static void findCountOfClicks() {
		// 1. 지뢰를 기준으로 주변 8방향의 값을 더한다.
		addBombCount();
		// 2. 지뢰를 제외한 좌표들을 따로 큐에 저장한다.
		saveLocations();
		// 3. 0의 좌표에서 BFS를 호출하며, 방문 처리를 하고 호출 횟수마다 클릭 수 +1을 한다.
		Location curZeroLocation;
		while (!zeroLocations.isEmpty()) {
			curZeroLocation = zeroLocations.poll();
			if (!isVisited[curZeroLocation.x][curZeroLocation.y]) {
				// 아직 미방문 한 0 좌표인 경우 BFS 호출
				bfs(curZeroLocation);
				countOfClicks++;
			}
		}
		// 4. 마지막으로 0 초과 좌표 중에서 방문하지 않은 곳이 있다면 +1씩 한다.
		Location curOverZeroLocation;
		while (!overZeroLocations.isEmpty()) {
			curOverZeroLocation = overZeroLocations.poll();
			if (!isVisited[curOverZeroLocation.x][curOverZeroLocation.y]) {
				countOfClicks++;
			}
		}
	}
	
	/**
	 * 주변 지뢰의 개수를 할당하는 메서드.
	 * 
	 */
	private static void addBombCount() {
		isVisited = new boolean[size][size];
		int x, y, nx, ny;
		Location curLocation;
		while (!bombs.isEmpty()) {
			curLocation = bombs.poll();
			x = curLocation.x;
			y = curLocation.y;
			
			for (int directionIndex = 0; directionIndex < 8; directionIndex++) {
				nx = x + DX[directionIndex];
				ny = y + DY[directionIndex];
				if (canMove(nx, ny)) {
					if (board[nx][ny] != -1) {
						// 지뢰가 아닌 경우 +1
						board[nx][ny] += 1;
					}
				}
			}
		}
	}
	
	/**
	 * 지뢰를 제외한 좌표들을 따로 큐에 저장하는 메서드.
	 * 
	 */
	private static void saveLocations() {
		zeroLocations = new LinkedList<>();
		overZeroLocations = new LinkedList<>();
		
		for (int rowIndex = 0; rowIndex < size; rowIndex++) {
			for (int colIndex = 0; colIndex < size; colIndex++) {
				if (board[rowIndex][colIndex] == 0) {
					// 0인 좌표 저장
					zeroLocations.offer(new Location(rowIndex, colIndex));
				} else if (board[rowIndex][colIndex] > 0) {
					// 0 초과인 좌표 저장
					overZeroLocations.offer(new Location(rowIndex, colIndex));
				}
			}
		}
	}
	
	/**
	 * BFS
	 * 
	 */
	private static void bfs(Location location) {
		q = new LinkedList<>();
		q.offer(location);
		isVisited[location.x][location.y] = true;
		
		int x, y, nx, ny;
		Location curLocation;
		while (!q.isEmpty()) {
			curLocation = q.poll();
			x = curLocation.x;
			y = curLocation.y;
			
			for (int directionIndex = 0; directionIndex < 8; directionIndex++) {
				nx = x + DX[directionIndex];
				ny = y + DY[directionIndex];
				if (canMove(nx, ny)) {
					if (board[nx][ny] == 0) {
						q.offer(new Location(nx, ny));
					}
					isVisited[nx][ny] = true;
				}
			}
		}
	}
	
	/**
	 * 이동 가능한지 여부를 반환하는 메서드. 
	 *
	 */
	private static boolean canMove(int nx, int ny) {
		return nx >= 0 && nx < size && ny >= 0 && ny < size && !isVisited[nx][ny]; 
	}
	
	/**
	 * 초기 세팅을 진행하는 메서드. 
	 *
	 */
	private static void init() throws IOException {
		countOfClicks = 0;
		size = Integer.parseInt(br.readLine());
		board = new int[size][size];
		bombs = new LinkedList<>();
		
		for (int rowIndex = 0; rowIndex < size; rowIndex++) {
			String line = br.readLine();
			for (int colIndex = 0; colIndex < size; colIndex++) {
				char curChar = line.charAt(colIndex);
				int curNum;
				if (curChar == '*') {
					// 지뢰 좌표 추가
					bombs.offer(new Location(rowIndex, colIndex));
					curNum = -1;
				} else {
					// 빈 칸
					curNum = 0;
				}
				board[rowIndex][colIndex] = curNum;
			}
		}
	}
	
	/**
	 * 좌표 정보를 저장하는 클래스.
	 *
	 */
	static class Location {
		int x;
		int y;
		
		Location(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
