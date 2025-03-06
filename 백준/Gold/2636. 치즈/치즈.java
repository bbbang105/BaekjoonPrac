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
 * 1. 세로 R과 가로 C의 길이는 1이상 100이하의 정수이다.
 * 2. 치즈가 있는 칸은 1, 치즈가 없는 칸은 0이다.
 * 3. 판의 가장 자리에는 치즈가 존재하지 않는다.
 * 4. 공기와 접촉하는 (인접한) 치즈는 한 시간 뒤에 녹아서 없어진다.
 * 5. 치즈는 꼭 한 덩어리가 아니며, 녹아없어지는 경우에 따라 여러 개로 나뉠 수 있다.
 * 6. 치즈가 모두 녹아 없어지는 시간과, 모두 녹기 한 시간 전 남아 있는 치즈 조각 칸의 개수를 출력하라.
 *
 * [풀이]
 * 1. 우선 BFS를 통해서 외부 공기와 접촉한 치즈를 저장해 둔다.
 * 2. 탐색이 끝나면 접촉한 치즈들을 모두 0으로 바꿔서 치즈를 없애 준다.
 * 3. 없애준 치즈 개수만큼 남아있는 치즈 개수에서 뺀다.
 * 4. 위 과정을 반복하며 치즈들을 없애주고, 만약 남아 있는 치즈 개수가 0이 된다면 결과를 출력한다.
 */
public class Main {

	// 4방향
	private static final int[] DX = {-1, 1, 0, 0};
	private static final int[] DY = {0, 0, -1, 1};
	// BFS 관련
	private static int R;
	private static int C;
	private static int[][] cheeses;
	private static boolean[][] isVisited;
	private static Queue<Location> externalAirs; // 외부 공기들
	private static List<Location> meltingCheeses; // 녹아 없어질 치즈들
	private static int dayOfAllMelting; // 치즈가 모두 녹아 없어지는 시간
	private static int countOfRemainingCheeses; // 남아 있는 치즈의 개수
	// 입출력 관련
	private static BufferedReader br;
	private static StringTokenizer st;
	private static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		init();
		findResult();
		sb.append(dayOfAllMelting).append('\n').append(countOfRemainingCheeses);
		System.out.println(sb);
	}
	
	/**
	 * BFS를 활용하여 결과를 도출하는 메서드.
	 * 
	 */
	private static void findResult() {
		while (true) {
			meltingCheeses = new ArrayList<>();
			
			spreadExternalAirs(new Location(0, 0)); // 가장자리는 무조건 공기이므로 시작점으로 둠
			
			if (meltingCheeses.size() == countOfRemainingCheeses) {
				// 한 시간 후에 모든 치즈가 녹아 없어지는 경우
				break;
			}
			
			for (Location meltingCheese : meltingCheeses) {
				// 녹아 없어지는 치즈들 처리
				cheeses[meltingCheese.x][meltingCheese.y] = 0;
			}
			
			countOfRemainingCheeses -= meltingCheeses.size();
			dayOfAllMelting++;
		}
	}
	
	/**
	 * 외부 공기를 탐색하면서 접촉한 치즈를 찾는 메서드.
	 * 
	 */
	private static void spreadExternalAirs(Location location) {
		externalAirs = new LinkedList<>();
		externalAirs.offer(location);
		isVisited = new boolean[R][C];
		
		int x, y, nx, ny;
		while (!externalAirs.isEmpty()) {
			Location curLocation = externalAirs.poll();
			x = curLocation.x;
			y = curLocation.y;
			
			for (int directionIndex = 0; directionIndex < 4; directionIndex++) {
				nx = x + DX[directionIndex];
				ny = y + DY[directionIndex];
				if (canMove(nx, ny)) {
					int next = cheeses[nx][ny];
					Location nextLocation = new Location(nx, ny);
					if (next == 0) {
						// 외부 공기인 경우 -> 더 퍼짐
						isVisited[nx][ny] = true;
						externalAirs.offer(nextLocation);
					} else if (next == 1) {
						// 치즈인 경우 -> 외부 공기와 접촉 처리를 위해 저장
						isVisited[nx][ny] = true;
						meltingCheeses.add(nextLocation);
					}
				}
			}
		}
	}
	
	/**
	 * 이동 가능 여부를 반환하는 메서드.
	 * 
	 */
	private static boolean canMove(int nx, int ny) {
		return nx >= 0 && nx < R && ny >= 0 && ny < C && !isVisited[nx][ny];
	}
	
	/**
	 * 초기 세팅을 진행하는 메서드.
	 * 
	 */
	private static void init() throws IOException {
		sb = new StringBuilder();
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		cheeses = new int[R][C];
		dayOfAllMelting = 1;
		
		int cur;
		for (int rowIndex = 0; rowIndex < R; rowIndex++) {
			st = new StringTokenizer(br.readLine());
			for (int colIndex = 0; colIndex < C; colIndex++) {
				cur = Integer.parseInt(st.nextToken());
				if (cur == 1) {
					countOfRemainingCheeses++;
				}
				cheeses[rowIndex][colIndex] = cur;
			}
		}
	}
	
	/**
	 * 좌표의 정보를 담은 클래스. 
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
