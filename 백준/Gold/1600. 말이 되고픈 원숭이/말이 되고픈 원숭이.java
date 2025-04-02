import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * [조건]
 * 1. 원숭이는 상하좌우로 움직인다.
 * 2. 말은 체스의 나이트와 같이 움직인다.
 * 3. 원숭이는 K번만 말처럼 움직일 수 있다.(K는 0이상 30이하의 정수이다.)
 * 4. 0은 평지, 1은 장애물이다. 말은 장애물을 뛰어넘을 수 있다. 
 * 5. 동작 수는 이동 or 말로 변하고 이동 둘 다 1씩 올라간다.
 * 6. 가로 세로 길이 W,H는 1이상 200이하의 자연수이다.
 * 7. 시작점(좌상단) -> 도착점(우하단)까지 걸리는 최소한의 동작 수를 구하라.
 * 
 * [풀이]
 * 1. BFS 방식으로 푼다.
 * 2. 원숭이, 말의 이동 방향을 미리 정해둔다.
 * 3. 말로 변할 수 있는 횟수 K도 함께 저장하여 구분한다. 때문에 3차원 배열로 방문처리를 진행한다.
 * 4. 도착점에 도착하면 동작 수를 최소로 갱신한다.
 */
public class Main {
	
	private static final int[] MONKEY_DX = {-1, 1, 0, 0};
	private static final int[] MONKEY_DY = {0, 0, -1, 1};
	private static final int[] HORSE_DX = {-1, -1, -2, -2, 1, 1, 2, 2};
	private static final int[] HORSE_DY = {-2, 2, -1, 1, -2, 2, -1, 1};
	
	private static BufferedReader br;
	private static StringTokenizer st;
	
	private static int result;
	private static int limitOfChange; // 변화 가능 횟수
	private static int W;
	private static int H;
	private static int[][] graph;
	private static boolean[][][] isVisited; // 변화 횟수 저장을 위해 3차원으로
	
	/**
	 * 메인 메서드. 
	 */
	public static void main(String[] args) throws IOException {
		init();
		bfs();
		result = result == 40_001 ? -1 : result; // 이동 불가능(= 40_001 이라면 -1)
		System.out.println(result);
	}
	
	/**
	 * BFS 
	 */
	private static void bfs() {
		Queue<Location> q = new LinkedList<>();
		q.offer(new Location(0, 0, 0, 0)); // 초기 시작점
		
		Location location;
		int x, y, nx, ny, countOfChangeToHorse, countOfMovement;
		while (!q.isEmpty()) {
			location = q.poll();
			x = location.x;
			y = location.y;
			countOfChangeToHorse = location.countOfChangeToHorse;
			countOfMovement = location.countOfMovement;
			
			// 0. 도착한 경우
			if (x == W - 1 && y == H - 1) {
				result = Math.min(result, countOfMovement); // 최소값으로 갱신
				continue;
			}
			
			// 1. 원숭이로는 무조건 이동
			for (int monkeyDirectionIndex = 0; monkeyDirectionIndex < 4; monkeyDirectionIndex++) {
				nx = x + MONKEY_DX[monkeyDirectionIndex];
				ny = y + MONKEY_DY[monkeyDirectionIndex];
				if (canMove(nx, ny, countOfChangeToHorse)) {
					isVisited[nx][ny][countOfChangeToHorse] = true;
					q.offer(new Location(nx, ny, countOfChangeToHorse, countOfMovement + 1));
				}
			}
			
			// 2. 말로 변할 수 있는 횟수가 남아있다면
			if (countOfChangeToHorse < limitOfChange) {
				for (int horseDirectionIndex = 0; horseDirectionIndex < 8; horseDirectionIndex++) {
					nx = x + HORSE_DX[horseDirectionIndex];
					ny = y + HORSE_DY[horseDirectionIndex];
					// countOfChangeToHorse에 1을 더한 상태에서 확인
					if (canMove(nx, ny, countOfChangeToHorse + 1)) {
						isVisited[nx][ny][countOfChangeToHorse + 1] = true;
						q.offer(new Location(nx, ny, countOfChangeToHorse + 1, countOfMovement + 1));
					}
				}
			}
		}
	}
	
	/**
	 * 이동 가능 여부를 반환하는 메서드.
	 */
	private static boolean canMove(int x, int y, int countOfChangeToHorse) {
		return x >= 0 && x < W && y >= 0 && y < H && !isVisited[x][y][countOfChangeToHorse] && graph[x][y] != 1;
	}
	
	/**
	 * 초기 세팅을 진행하는 메서드.
	 */
	private static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		result = 40_001;
		limitOfChange = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		// 가로, 세로 길이 순으로 들어오므로 반대로
		H = Integer.parseInt(st.nextToken()); 
		W = Integer.parseInt(st.nextToken());
		graph = new int[W][H];
		isVisited = new boolean[W][H][limitOfChange + 1];
		
		for (int rowIndex = 0; rowIndex < W; rowIndex++) {
			st = new StringTokenizer(br.readLine());
			for (int colIndex = 0; colIndex < H; colIndex++) {
				graph[rowIndex][colIndex] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	/**
	 * 좌표 등 정보를 담은 클래스.
	 */
	static class Location {
		int x;
		int y;
		int countOfChangeToHorse; // 말로 변한 횟수
		int countOfMovement; // 동작 횟수
		
		Location(int x, int y, int countOfChangeToHorse, int countOfMovement) {
			this.x = x;
			this.y = y;
			this.countOfChangeToHorse = countOfChangeToHorse;
			this.countOfMovement = countOfMovement;
		}
	}
}
