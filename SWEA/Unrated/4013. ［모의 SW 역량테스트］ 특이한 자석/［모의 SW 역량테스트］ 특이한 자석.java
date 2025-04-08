import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [조건]
 * 1. 각 날은 N극 또는 S극으로 구성된다. N극은 0, S극은 1로 주어진다.
 * 2. 자석은 총 4개가 있다.
 * 3. 자석을 움직일 때, 붙어 있는 자석 2개의 날이 다른 경우에만 반대 방향으로 1칸 회전한다.
 * 4. 최종적으로 자석을 움직인 후에 빨간 화살표 위치에 있는 극에 따라 점수가 부여된다.
 * 	4-1. N극인 경우에는 모두 0점이다.
 * 	4-2. S극인 경우에는 1~4번까지 차등적으로, 1 2 4 8 점을 획득한다.
 * 5. 자석을 회전시키는 횟수 K는 1이상 20이하의 정수이다.
 * 6. 회전 방향은 시계 방향이 1, 반시계 방향이 -1로 주어진다.
 * 7. 모든 자석들은 일괄적으로 움직인다. 즉, 돌리기 전 초기상태를 기준으로 돌릴 자석들을 결정해야 한다.
 */
public class Solution {
	
	private static BufferedReader br;
	private static StringTokenizer st;
	
	private static int result;
	private static int countOfCmds;
	private static int[][] magnets;
	private static CMD[] cmds;
	private static int[] redPoints;
	private static boolean[] isVisited;

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
	 * 결과를 도출하는 메서드.
	 */
	private static void findResult() {
		for (CMD cmd : cmds) {
			isVisited = new boolean[5];
			move(cmd);
		}
		int redPoint;
		int score = 1;
		for (int index = 1; index <= 4; index++) {
			redPoint = redPoints[index];
			if (magnets[index][redPoint] == 1) {
				// S극인 경우
				result += score;
			}
			score *= 2;
		}
	}
	
	/**
	 * 탐색하며 자석을 돌리는 메서드.
	 */
	private static void move(CMD cmd) {
		int[] rotateDir = new int[5]; // 1~4번 자석 회전 방향 저장
		rotateDir[cmd.magnetNum] = cmd.direction;

		// 왼쪽으로 전파
		for (int i = cmd.magnetNum; i > 1; i--) {
			int rightPole = magnets[i][(redPoints[i] - 2 + 8) % 8];     // 왼쪽 자석의 오른쪽 극
			int leftPole = magnets[i - 1][(redPoints[i - 1] + 2) % 8];  // 오른쪽 자석의 왼쪽 극
			if (rightPole != leftPole) {
				rotateDir[i - 1] = -rotateDir[i];
			} else break;
		}

		// 오른쪽으로 전파
		for (int i = cmd.magnetNum; i < 4; i++) {
			int rightPole = magnets[i][(redPoints[i] + 2) % 8];
			int leftPole = magnets[i + 1][(redPoints[i + 1] - 2 + 8) % 8];
			if (rightPole != leftPole) {
				rotateDir[i + 1] = -rotateDir[i];
			} else break;
		}

		// 실제 회전 적용
		for (int i = 1; i <= 4; i++) {
			if (rotateDir[i] == 0) continue;
			redPoints[i] = (redPoints[i] - rotateDir[i] + 8) % 8;
		}
	}
	
	/**
	 * 초기 세팅 메서드.
	 */
	private static void init() throws IOException {
		redPoints = new int[5];
		result = 0;
		countOfCmds = Integer.parseInt(br.readLine());
		magnets = new int[5][8];
		cmds = new CMD[countOfCmds];
		
		for (int index = 1; index <= 4; index++) {
			st = new StringTokenizer(br.readLine());
			for (int bladeIndex = 0; bladeIndex < 8; bladeIndex++) {
				magnets[index][bladeIndex] = Integer.parseInt(st.nextToken());
			}
		}
		
		int magnetNum, direction;
		for (int index = 0; index < countOfCmds; index++) {
			st = new StringTokenizer(br.readLine());
			magnetNum = Integer.parseInt(st.nextToken());
			direction = Integer.parseInt(st.nextToken());
			cmds[index] = new CMD(magnetNum, direction);
		}
	}
	
	/**
	 * 움직일 자석 번호, 방향을 담는 클래스.
	 */
	static class CMD {
		int magnetNum;
		int direction;
		
		CMD(int magnetNum, int direction) {
			this.magnetNum = magnetNum;
			this.direction = direction;
		}
	}
}
