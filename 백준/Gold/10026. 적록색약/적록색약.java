import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * [조건]
 * 1. 적록색약인 사람은 빨간색과 초록색을 같은 색으로 판단한다.
 * 2. 색상은 RGB 세 종류가 있다.
 * 3. 사이즈 N은 1이상 100이하이다.
 * 4. 구역은 같은 색으로 이루어져있고, 같은 색상이 상하좌우로 인접해 있다면 같은 구역이다.(BFS)
 * 5. 동일한 입력 값에서, 적록색약이 아닌 사람과 적록색약인 사람이 보았을 때의 구역 개수를 각각 구해 출력하라.
 *
 * [풀이]
 * 1. 좌측 상단부터 미방문인 경우에는 BFS를 호출한다.
 * 2. 호출 횟수마다 +1씩 구역 개수를 더해준다.
 * 3. 적록색약 X, 적록색약 O인 두 경우를 각각 구한다.
 */

public class Main {
	
	private static final int[] DX = {-1, 1, 0, 0};
	private static final int[] DY = {0, 0, -1, 1};
	// 보드
	private static int size;
	private static int[][] board; // R == 0, G == 1, B ==2 로 바꾸어 저장
	private static boolean[][] isVisited;
	// 결과
	private static int countOfAreasForNodeBlindness; // 적록색약 X인 사람이 보는 구역의 수
	private static int countOfAreasForBlindness;	 // 적록색약 O인 사람이 보는 구역의 수
	// 입출력 관련
	private static BufferedReader br;
	private static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		init();
		findAreasForNodeBlindness(); // 적록색약 X
		findAreasForBlindness();     // 적록색약 O
		System.out.println(countOfAreasForNodeBlindness + " " + countOfAreasForBlindness);
	}
	
	// 적록색약 X용 BFS
	private static void findAreasForNodeBlindness() {
		isVisited = new boolean[size][size];
		Queue<Location> q;
		
		for (int rowIndex = 0; rowIndex < size; rowIndex++) {
			for (int colIndex = 0; colIndex < size; colIndex++) {
				if (!isVisited[rowIndex][colIndex]) {
					countOfAreasForNodeBlindness++;
					
					q = new LinkedList<>();
					q.offer(new Location(rowIndex, colIndex));
					isVisited[rowIndex][colIndex] = true;
					int firstColor = board[rowIndex][colIndex];
					
					Location curLocation;
					int x, y, nx, ny;
					while(!q.isEmpty()) {
						curLocation = q.poll();
						x = curLocation.x;
						y = curLocation.y;
						
						for (int directionIndex = 0; directionIndex < 4; directionIndex++) {
							nx = x + DX[directionIndex];
							ny = y + DY[directionIndex];
									
							if (canMove(nx, ny)) {
								int nextColor = board[nx][ny];
								if (firstColor == nextColor) {
									isVisited[nx][ny] = true;
									q.offer(new Location(nx, ny));
								}
							}
						}
					}
				}
			}
		}
	}
	
	// 적록색약 O용 BFS
		private static void findAreasForBlindness() {
			isVisited = new boolean[size][size];
			Queue<Location> q;
			
			for (int rowIndex = 0; rowIndex < size; rowIndex++) {
				for (int colIndex = 0; colIndex < size; colIndex++) {
					if (!isVisited[rowIndex][colIndex]) {
						countOfAreasForBlindness++;
						
						q = new LinkedList<>();
						q.offer(new Location(rowIndex, colIndex));
						isVisited[rowIndex][colIndex] = true;
						int firstColor = board[rowIndex][colIndex];
						if (firstColor == 1) {
							// 녹색인 경우 적색으로 통일해 줌
							firstColor = 0;
						}
						
						Location curLocation;
						int x, y, nx, ny;
						while(!q.isEmpty()) {
							curLocation = q.poll();
							x = curLocation.x;
							y = curLocation.y;
							
							for (int directionIndex = 0; directionIndex < 4; directionIndex++) {
								nx = x + DX[directionIndex];
								ny = y + DY[directionIndex];
								if (canMove(nx, ny)) {
									int nextColor = board[nx][ny];
									if (nextColor == 1) {
										// 녹색인 경우 적색으로 통일해 줌
										nextColor = 0;
										board[nx][ny] = 0;
									}
									if (firstColor == nextColor) {
										isVisited[nx][ny] = true;
										q.offer(new Location(nx, ny));
									}
								}
							}
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
		return nx >= 0 && nx < size && ny >= 0 && ny < size && !isVisited[nx][ny];
	}
	
	/**
	 * 초기 세팅을 진행하는 메서드.
	 * 
	 */
	private static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		size = Integer.parseInt(br.readLine());
		board = new int[size][size];
		countOfAreasForNodeBlindness = 0;
		countOfAreasForBlindness = 0;
		
		String line;
		for (int rowIndex = 0; rowIndex < size; rowIndex++) {
			line = br.readLine();
			for (int colIndex = 0; colIndex < size; colIndex++) {
				char curChar = line.charAt(colIndex);
				int curNum;
				if (curChar == 'R') {
					curNum = 0;
				} else if (curChar == 'G') {
					curNum = 1;
				} else {
					curNum = 2;
				}
				board[rowIndex][colIndex] = curNum;
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
