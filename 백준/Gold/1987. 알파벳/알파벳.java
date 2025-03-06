import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [조건]
 * 1. 세로 R 가로 C는 1이상 20이하이다.
 * 2. 알파벳 개수는  C개이며, 중복이 있을 수 있다.
 * 3. 말은 상하좌우로 이동할 수 있고, 지금까지 지나온 모든 알파벳과 달라야만 이동할 수 있다.
 * 4. 좌측 상단 (0,0)부터 시작한다.
 * 5. 최대한 지날 수 있는 칸 수를 출력하라.
 * 
 * [풀이]
 * 1. 아스키코드를 활용하여 지나온 알파벳을 확인한다.
 * 	- 대문자 알파벳은 65 ~ 90 이므로 방문 처리를 할 때 65를 빼주어 인덱스로 활용한다.
 * 	- 사이즈가 26인 방문 배열을 만들고 체크한다.
 * 2. 최대로 이동한 후 값을 갱신한다.
 */
public class Main {
	
	private static final int[] DX = {-1, 1, 0, 0};
	private static final int[] DY = {0, 0, -1, 1};
	// BFS 관련
	private static int[][] board;
	private static boolean[] isVisited;
	private static int maxMoveCount = 1; // 초기값은 1로 할당
	private static int R, C;
	// 입출력 관련
	private static BufferedReader br;
	private static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		init();
		isVisited[board[0][0]] = true;
		findMaxMoveCount(0, 0, 1);
		System.out.println(maxMoveCount);
	}
	
	/**
	 * DFS를 활용하여 최대 이동 횟수를 찾는 메서드.
	 * 
	 */
	private static void findMaxMoveCount(int x, int y, int moveCount) {
		
		maxMoveCount = Math.max(maxMoveCount, moveCount);
		
		for (int directionIndex = 0; directionIndex < 4; directionIndex++) {
			int nx = x + DX[directionIndex];
			int ny = y + DY[directionIndex];
			if (canMove(nx, ny)) {
				isVisited[board[nx][ny]] = true;
				findMaxMoveCount(nx, ny, moveCount + 1);
				isVisited[board[nx][ny]] = false;
			}
		}
	}
	
	/**
	 * 이동 가능한지 여부를 반환하는 메서드.
	 * 
	 */
	private static boolean canMove(int nx, int ny) {
		return nx >= 0 && nx < R && ny >= 0 && ny < C && !isVisited[board[nx][ny]];
	}
	
	/**
	 * 초기 세팅을 진행하는 메서드.
	 * 
	 */
	private static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		board = new int[R][C];
		isVisited = new boolean[26]; // 알파벳 방문 배열
		String line;
		
		for (int rowIndex = 0; rowIndex < R; rowIndex++) {
			line = br.readLine();
			for (int colIndex = 0; colIndex < C; colIndex++) {
				board[rowIndex][colIndex] = line.charAt(colIndex) - 65; // 0부터 25까지 존재함
			}
		}
	}
}
