import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [조건]
 * 1. 사이즈 N은 1이상 20이하의 정수이다.
 * 2. S는 “left”, “right”, “up”, “down”의 넷 중 하나이다.
 * 3. 각 칸에 있는 숫자들은 2이상 1024 이하의 2의 제곱수들이다.
 * 4. 타일은 1번만 밀게 되며, 모든 타일이 해당 방향으로 밀린다.
 * 5. 만약 2 2 가 겹쳐지면 4로 변하게 되며, 그 뒤에 4가 있더라도 둘 다 합쳐지지는 않는다. (4 4)
 * 6. 같은 숫자가 3개 이상 연속으로 주어지면, 벽에 더 먼저 닿는 숫자들부터 합쳐진다.
 * 7. 0은 타일이 없는 빈 칸이다.
 * 8. 타일을 모두 이동시킨 후의 모습을 출력하라.
 * 
 * [풀이]
 * 1. 미는 방향에서부터 반대 방향 끝까지 탐색한다.
 * 2. 즉, up이라면 맨 위에서부터 맨 아래까지 탐색한다.
 * 3-1. 현재 숫자가 0이라면 패스한다.
 * 3-2. 탐색하며 현재 숫자와 다음으로 처음 만나는 숫자가 동일하다면, 합친 후에 맨 위부터 쌓는다.
 * 3-3. 병합한 숫자는 newBoard에 넣고, board는 합친 숫자를 0 처리한다.
 * 4. 만약 끝까지 같은 숫자를 만나지 못 했거나, 0이 아닌 다른 숫자를 만났다면 병합할 수 없으므로 그대로 넣어준다.
 * 5. 남은 칸은 모두 0으로 채운다.
 * 6. 각 커맨드에 따라서 메서드를 만들고 이를 호출하여 답을 구한다.
 *
 */
public class Solution {
	
	private static BufferedReader br;
	private static StringTokenizer st;
	private static int size;
	private static String command;
	private static int[][] board;
	private static int[][] newBoard;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		int testCaseNum = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for (int testCase = 1; testCase <= testCaseNum; testCase++) {
			init();
			switch (command) {
			case "up":
				goToUp();
				break;
			case "down":
				goToDown();
				break;
			case "left":
				goToLeft();
				break;
			case "right":
				goToRight();
				break;
			default:
				break;
			}
			
			sb.append("#").append(testCase).append(" ").append('\n');
			for (int rowIndex = 0; rowIndex < size; rowIndex++) {
				for (int colIndex = 0; colIndex < size; colIndex++) {
					sb.append(newBoard[rowIndex][colIndex]).append(" ");
				}
				sb.append('\n');
			}
		}
		System.out.print(sb);
	}
	
	/**
	 * 타일들을 모두 위로 미는 메서드.
	 * 
	 */
	private static void goToUp() {
		int fillIndex; // 위에서부터 채울 인덱스
		int curNum;    // 현재 숫자
		int moveNum;   // 움직이는 정도
		boolean isMerge;   // 병합 여부
		
		for (int colIndex = 0; colIndex < size; colIndex++) {
			fillIndex = 0;
			for (int rowIndex = 0; rowIndex < size; rowIndex++) {
				curNum = board[rowIndex][colIndex];
				if (curNum == 0) {
					// 숫자가 없다면 무시
					continue;
				}
				moveNum = 1;
				isMerge = false;
				while (rowIndex + moveNum < size) {
					if (curNum == board[rowIndex + moveNum][colIndex]) {
						newBoard[fillIndex][colIndex] = curNum * 2;
						board[rowIndex + moveNum][colIndex] = 0; // 병합했으므로 0으로 바꿔줌
						isMerge = true;
						break;
					} else if (board[rowIndex + moveNum][colIndex] != 0) {
						// 현재 숫자와 같지 않고 0이 아니라면 병합 불가 -> 반복문 종료
						break;
					}
					moveNum++;
				}
				if (!isMerge) {
					// 병합하지 않는 경우 그대로 넣음
					newBoard[fillIndex][colIndex] = curNum;
				}
				fillIndex++;
			}
		}
	}
	
	/**
	 * 타일들을 모두 아래로 미는 메서드.
	 * 
	 */
	private static void goToDown() {
		int fillIndex; // 아래에서부터 채울 인덱스
		int curNum;    // 현재 숫자
		int moveNum;   // 움직이는 정도
		boolean isMerge;   // 병합 여부
		
		for (int colIndex = 0; colIndex < size; colIndex++) {
			fillIndex = size - 1;
			for (int rowIndex = size - 1; rowIndex >= 0; rowIndex--) {
				curNum = board[rowIndex][colIndex];
				if (curNum == 0) {
					// 숫자가 없다면 무시
					continue;
				}
				moveNum = 1;
				isMerge = false;
				while (rowIndex - moveNum >= 0) {
					if (curNum == board[rowIndex - moveNum][colIndex]) {
						newBoard[fillIndex][colIndex] = curNum * 2;
						board[rowIndex - moveNum][colIndex] = 0; // 병합했으므로 0으로 바꿔줌
						isMerge = true;
						break;
					} else if (board[rowIndex - moveNum][colIndex] != 0) {
						// 현재 숫자와 같지 않고 0이 아니라면 병합 불가 -> 반복문 종료
						break;
					}
					moveNum++;
				}
				if (!isMerge) {
					// 병합하지 않는 경우 그대로 넣음
					newBoard[fillIndex][colIndex] = curNum;
				}
				fillIndex--;
			}
		}
	}
	
	/**
	 * 타일들을 모두 왼쪽으로 미는 메서드.
	 * 
	 */
	private static void goToLeft() {
		int fillIndex; // 왼쪽에서부터 채울 인덱스
		int curNum;    // 현재 숫자
		int moveNum;   // 움직이는 정도
		boolean isMerge;   // 병합 여부
		
		for (int rowIndex = 0; rowIndex < size; rowIndex++) {
			fillIndex = 0;
			for (int colIndex = 0; colIndex < size; colIndex++) {
				curNum = board[rowIndex][colIndex];
				if (curNum == 0) {
					// 숫자가 없다면 무시
					continue;
				}
				moveNum = 1;
				isMerge = false;
				while (colIndex + moveNum < size) {
					if (curNum == board[rowIndex][colIndex + moveNum]) {
						newBoard[rowIndex][fillIndex] = curNum * 2;
						board[rowIndex][colIndex + moveNum] = 0; // 병합했으므로 0으로 바꿔줌
						isMerge = true;
						break;
					} else if (board[rowIndex][colIndex + moveNum] != 0) {
						// 현재 숫자와 같지 않고 0이 아니라면 병합 불가 -> 반복문 종료
						break;
					}
					moveNum++;
				}
				if (!isMerge) {
					// 병합하지 않는 경우 그대로 넣음
					newBoard[rowIndex][fillIndex] = curNum;
				}
				fillIndex++;
			}
		}
	}
	
	/**
	 * 타일들을 모두 오른쪽으로 미는 메서드.
	 * 
	 */
	private static void goToRight() {
		int fillIndex; // 오른쪽에서부터 채울 인덱스
		int curNum;    // 현재 숫자
		int moveNum;   // 움직이는 정도
		boolean isMerge;   // 병합 여부
		
		for (int rowIndex = 0; rowIndex < size; rowIndex++) {
			fillIndex = size - 1;
			for (int colIndex = size - 1; colIndex >= 0; colIndex--) {
				curNum = board[rowIndex][colIndex];
				if (curNum == 0) {
					// 숫자가 없다면 무시
					continue;
				}
				moveNum = 1;
				isMerge = false;
				while (colIndex - moveNum >= 0) {
					if (curNum == board[rowIndex][colIndex - moveNum]) {
						newBoard[rowIndex][fillIndex] = curNum * 2;
						board[rowIndex][colIndex - moveNum] = 0; // 병합했으므로 0으로 바꿔줌
						isMerge = true;
						break;
					} else if (board[rowIndex][colIndex - moveNum] != 0) {
						// 현재 숫자와 같지 않고 0이 아니라면 병합 불가 -> 반복문 종료
						break;
					}
					moveNum++;
				}
				if (!isMerge) {
					// 병합하지 않는 경우 그대로 넣음
					newBoard[rowIndex][fillIndex] = curNum;
				}
				fillIndex--;
			}
		}
	}
	
	/**
	 * 초기 세팅을 진행하는 메서드.
	 * 
	 */
	private static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		size = Integer.parseInt(st.nextToken());
		command = st.nextToken();
		board = new int[size][size];
		newBoard = new int[size][size];
		
		for (int rowIndex = 0; rowIndex < size; rowIndex++) {
			st = new StringTokenizer(br.readLine());
			for (int colIndex = 0; colIndex < size; colIndex++) {
				board[rowIndex][colIndex] = Integer.parseInt(st.nextToken());
			}
		}
	}
}
