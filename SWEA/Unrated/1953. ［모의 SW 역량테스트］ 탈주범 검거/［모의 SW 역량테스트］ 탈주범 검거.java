import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * [조건]
 * 1. 터널의 세로 크기 N, 가로 크기 M은 각각 5이상 50이하이다.
 * 2. 탈출 후 소요된 시간은 1이상 20이하이다.
 * 3. 맨홀 뚜껑은 항상 터널이 있는 위치에 존재한다.(= 아예 움직이지 못 하는 경우는 없다.)
 * 4. 시간당 1의 거리를 움직일 수 있다.
 * 5. 1 ~ 7번까지 터널 구조물 타입이 정해져있다.
 * 6. 경과된 시간이 주어질 때, 최종적으로 탈주범이 위치할 수 있는 장소의 개수를 구하라.
 * 
 * [풀이]
 * 1. BFS를 활용한다.
 * 2. 터널 구조물 타입에 따른 이동방향을 배열로 미리 만들어둔다.
 * 3. 큐에 들어가는 Location에는 x, y, time, type을 저장한다. (time이 시간을 넘으면 이동x)
 * 4. 방문 처리를 하며, 처음 방문일 시 result++한다.
 * 5. 큐가 empty 할 때까지 반복한다.
 */
public class Solution {
	
	private static final int[][] DX = {
			{}, // 0번은 없음
			{-1, 1, 0, 0}, // 상하좌우
			{-1, 1}, // 상하
			{0, 0},  // 좌우
			{-1, 0}, // 상우
			{1, 0},  // 하우
			{1, 0},  // 하좌
			{-1, 0}  // 상좌
	};
	
	private static final int[][] DY = {
			{}, // 0번은 없음
			{0, 0, -1, 1}, // 상하좌우
			{0, 0},  // 상하
			{-1, 1}, // 좌우
			{0, 1},  // 상우
			{0, 1},  // 하우
			{0, -1}, // 하좌
			{0, -1}  // 상좌
	};
	
	private static BufferedReader br;
	private static StringTokenizer st;
	
	private static int result;
	private static int R, C;
	private static int startX, startY;
	private static int timeLimit;
	private static int[][] graph;
	private static boolean[][] isVisited;
	
	/**
	 * 메인 메서드. 
	 */
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		br = new BufferedReader(new InputStreamReader(System.in));
		int testCaseNum = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= testCaseNum; testCase++) {
			init();
			findResult();
			sb.append("#").append(testCase).append(" ").append(result).append('\n');
		}
		System.out.println(sb);
	}
	
	/**
	 * BFS를 활용하여 결과를 도출하는 메서드.
	 */
	private static void findResult() {
		Queue<Location> q = new LinkedList<>();
		q.offer(new Location(startX, startY, 0, graph[startX][startY]));
		isVisited[startX][startY] = true;
		
		Location cur;
		int x, y, nx, ny, time, type;
		int[] moveToX, moveToY;
		while (!q.isEmpty()) {
			cur = q.poll();
			x = cur.x;
			y = cur.y;
			time = cur.time;
			type = cur.type;
			if (time == timeLimit - 1) {
				continue;
			}
			
			moveToX = DX[type];
			moveToY = DY[type];
			for (int directionIndex = 0; directionIndex < moveToX.length; directionIndex++) {
				nx = x + moveToX[directionIndex];
				ny = y + moveToY[directionIndex];
				if (canMove(x, y, nx, ny)) {
					isVisited[nx][ny] = true;
					q.offer(new Location(nx, ny, time + 1, graph[nx][ny]));
					result++;
				}
			}
		}
	}
	
	/**
	 * 이동 가능 여부를 반환하는 메서드.
	 */
	private static boolean canMove(int originX, int originY, int x, int y) {
		if (x < 0 || x >= R || y < 0 || y >= C || isVisited[x][y]) {
			return false;
		}
		
		boolean canMove = false;
		int nextType = graph[x][y];
		int[] moveToX = DX[nextType];
		int[] moveToY = DY[nextType];
		for (int directionIndex = 0; directionIndex < moveToX.length; directionIndex++) {
			int nx = x + moveToX[directionIndex];
			int ny = y + moveToY[directionIndex];
			if (originX == nx && originY == ny) {
				// 다음 터널과 원래 터널이 연결되는 경우
				canMove = true;
				break;
			}
		}
		return canMove;
	}
	
	/**
	 * 초기 세팅을 진행하는 메서드.
	 */
	private static void init() throws IOException {
		result = 1; // 시작점 포함하므로 1부터 시작
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		startX = Integer.parseInt(st.nextToken());
		startY = Integer.parseInt(st.nextToken());
		timeLimit = Integer.parseInt(st.nextToken());
		graph = new int[R][C];
		isVisited = new boolean[R][C];
		
		for (int rowIndex = 0; rowIndex < R; rowIndex++) {
			st = new StringTokenizer(br.readLine());
			for (int colIndex = 0; colIndex < C; colIndex++) {
				graph[rowIndex][colIndex] = Integer.parseInt(st.nextToken());
			}
		}
	}

	/**
	 * 위치 정보를 담은 클래스.
	 */
	static class Location {
		int x;
		int y;
		int time;
		int type;
		
		Location(int x, int y, int time, int type) {
			this.x = x;
			this.y = y;
			this.time = time;
			this.type = type;
		}
	}
}
