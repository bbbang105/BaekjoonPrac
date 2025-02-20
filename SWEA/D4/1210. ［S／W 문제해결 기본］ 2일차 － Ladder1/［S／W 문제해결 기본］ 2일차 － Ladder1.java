import java.util.*;
import java.io.*;

/**
 * 
 * [조건]
 * 1. 배열은 100*100이다.
 * 2. 0은 평면, 1은 사다리, 2는 도착 지점을 의미한다.
 * 3. 도착 지점으로 가는 시작 점이 몇 열인지 출력하라.
 * 
 * [풀이]
 * 1. 도착 지점에서부터 위로 올라간다.
 * 2. 올라가고 있는 중에는, 좌우를 탐색하며 먼저 1이 있는 곳으로 이동한다.
 * 3. 좌우로 이동하고 있는 중에는, 상 만 탐색하며 1이 있는 곳으로 이동한다.
 * 4. 2번과 3번을 반복하며, 더이상 위로 이동할 수 없는 경우 시작점을 찾은 것으로 판단하고 종료한다.
 * 5. (첫번째 행에도 막대가 있을 수 있으므로, 좌우 탐색을 먼저 한 후 없다면 위로 이동하는 방식으로 진행한다.) -> *수정* 첫번째 행은 시작점이므로 막대 존재 X
 */
public class Solution {
	
	// 아래로 이동할 필요가 없으므로, '상 좌 우' 만 할당
	private static final int[] DX = {-1, 0, 0};
	private static final int[] DY = {0, -1, 1};
	private static final int SIZE = 100;
	private static final int COUNT_OF_TEST_CASE = 10;
	
	private static int[][] board = new int[SIZE][SIZE];
	private static BufferedReader br;
	private static int endX;
	private static int endY;
	private static int result;
	private static int curDirection;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for (int testCase = 1; testCase <= COUNT_OF_TEST_CASE; testCase++) {
			int testNum = Integer.parseInt(br.readLine());
			initBoard();
			findStartingPoint();
			sb.append("#").append(testCase).append(" ").append(result).append('\n');
		}
		System.out.print(sb);
	}
	
	/**
	 * 사다리판을 입력 받아 생성하는 메서드.
	 * 
	 */
	private static void initBoard() throws IOException {
		StringTokenizer st;
		
		for (int rowIndex = 0; rowIndex < SIZE; rowIndex++) {
			st = new StringTokenizer(br.readLine());
			for (int colIndex = 0; colIndex < SIZE; colIndex++) {
				board[rowIndex][colIndex] = Integer.parseInt(st.nextToken());
				if (board[rowIndex][colIndex] == 2) {
					endX = rowIndex;
					endY = colIndex;
				}
			}
		}
	}
	
	/**
	 * 시작점을 찾아 반환하는 메서드.
	 * 
	 */
	private static void findStartingPoint() {
		boolean isMoveToUp = true; // 처음에는 무조건 위로 이동
		curDirection = 0;
		int curX = endX;
		int curY = endY;
		boolean isEnd = false;
		
		while (!isEnd) {
			if (isMoveToUp) {
				// 1. 위로 이동하는 경우
				for (int directionIndex = 1; directionIndex < 3; directionIndex++) {
					// 좌우 먼저 탐색
					int nx = curX + DX[directionIndex];
					int ny = curY + DY[directionIndex];
					
					if (nx < 0 || nx >= SIZE || ny < 0 || ny >= SIZE) {
						continue;
					}
					
					if (board[nx][ny] == 1) {
						curX = nx;
						curY = ny;
						curDirection = directionIndex;
						isMoveToUp = false;
						break;
					}
				}
				if (isMoveToUp) {
					// 찾지 못 한 경우 이동처리
					curX += DX[curDirection];
					if (curX < 0) {
						// 종료 조건 확인
						// 더이상 올라갈 수 없는 경우, 결과값을 할당하고 종료
						result = curY;
						isEnd = true;
					}
				}
			} else if (!isMoveToUp) {
				// 2. 좌 또는 우로 이동하는 경우, 상만 탐색
				
				// * 무조건 마지막에는 상으로 이동하므로 범위를 넘어가는 경우는 X
				//if (nx < 0 || nx >= SIZE || ny < 0 || ny >= SIZE) {
				//	continue;
				//}
				if (board[curX + DX[0]][curY] == 1) {
					// 상으로 이동하는 경우
					curX += DX[0];
					curDirection = 0;
					isMoveToUp = true;
				} else {
					// 찾지 못 한 경우 이동처리
					curX += DX[curDirection];
					curY += DY[curDirection];
				}
			}
		}
	}
}
