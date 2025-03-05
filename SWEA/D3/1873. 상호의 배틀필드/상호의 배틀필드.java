import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [조건]
 * 1. 높이 H와 너비 W는 2이상 20이하이다.
 * 2. 넣을 입력의 개수 N은 0이상 100이하이다.
 * 3. 전차는 맵 밖으로는 이동할 수 없다.
 * 4. 포탄이 벽돌 벽에 부딪히면 벽이 부서지고 평지가 된다.
 * 5. 벽돌 벽이 아닌 강철 벽에 부딪히면 포탄이 멈춘다.
 * 6. 전차는 평지로만 이동할 수 있다. 이동하지 않는 경우더라도 전차 머리는 돌아간다.
 * 7. 초기 게임 맵과 사용자가 넣을 입력이 순서대로 주어질 때, 모든 입력을 처리한 후 맵의 상태를 출력하라.
 */
public class Solution {
	
	private static final int[] DX = {-1, 1, 0, 0};
	private static final int[] DY = {0, 0, -1, 1};
	
	private static BufferedReader br;
	private static StringTokenizer st;
	private static StringBuilder sb;
	private static int height;
	private static int width;
	private static char[][] map;
	private static int countOfCommands;
	private static String commands;
	// 탱크 관련 변수
	private static int tankDirection;
	private static int tankX;
	private static int tankY;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int testCaseNum = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= testCaseNum; testCase++) {
			init();
			gameStart();
			insertResultToStringBuilder(testCase);
		}
		System.out.println(sb);
	}
	
	/**
	 * 게임을 진행하는 메서드.
	 * 
	 */
	private static void gameStart() {
		int cmdIndex = 0;
		while (cmdIndex < countOfCommands) {
			char curCmd = commands.charAt(cmdIndex);
			
			switch (curCmd) {
			case 'S' :
				shoot();
				break;
			case 'U' :
				goToUp();
				break;
			case 'D' :
				goToDown();
				break;
			case 'L' :
				goToLeft();
				break;
			case 'R' :
				goToRight();
				break;
			default :
				break;
			}
			cmdIndex++;
		}
	}
	
	/**
	 *  머리 방향으로 포탄을 발사하는 메서드.
	 * 
	 */
	private static void shoot() {
		int nx, ny;
		int moveAmount = 1; // 이동하는 정도
		
		while (true) {
			nx = tankX + DX[tankDirection] * moveAmount;
			ny = tankY + DY[tankDirection] * moveAmount;
			if (!isInside(nx, ny)) {
				break;
			}
			char next = map[nx][ny];
			if (next == '*') {
				// 벽돌로 만들어진 벽이라면 파괴 -> 평지로 변함
				map[nx][ny] = '.';
				break;
			} else if (next == '#') {
				// 강철로 만들어진 벽이라면 포탄이 멈춤
				break;
			}
			moveAmount++; // 그 외에는 계속 포탄 이동
		}
	}
	
	/**
	 * 탱크 머리를 위쪽으로 돌리고 위가 평지라면 이동하는 메서드.
	 * 
	 */
	private static void goToUp() {
		tankDirection = 0;
		int nx = tankX + DX[tankDirection];
		int ny = tankY + DY[tankDirection];
		
		map[tankX][tankY] = '.';
		if (isLand(nx, ny)) {
			tankX = nx;
			tankY = ny;
		} 
		map[tankX][tankY] = '^';
	}
	
	/**
	 * 탱크 머리를 위쪽으로 돌리고 아래가 평지라면 이동하는 메서드.
	 * 
	 */
	private static void goToDown() {
		tankDirection = 1;
		int nx = tankX + DX[tankDirection];
		int ny = tankY + DY[tankDirection];
		
		map[tankX][tankY] = '.';
		if (isLand(nx, ny)) {
			tankX = nx;
			tankY = ny;
		} 
		map[tankX][tankY] = 'v';
	}
	
	/**
	 * 탱크 머리를 왼쪽으로 돌리고 왼쪽이 평지라면 이동하는 메서드.
	 * 
	 */
	private static void goToLeft() {
		tankDirection = 2;
		int nx = tankX + DX[tankDirection];
		int ny = tankY + DY[tankDirection];
		
		map[tankX][tankY] = '.';
		if (isLand(nx, ny)) {
			tankX = nx;
			tankY = ny;
		} 
		map[tankX][tankY] = '<';
	}
	
	/**
	 * 탱크 머리를 오른쪽으로 돌리고 오른쪽이 평지라면 이동하는 메서드.
	 * 
	 */
	private static void goToRight() {
		tankDirection = 3;
		int nx = tankX + DX[tankDirection];
		int ny = tankY + DY[tankDirection];
		
		map[tankX][tankY] = '.';
		if (isLand(nx, ny)) {
			tankX = nx;
			tankY = ny;
		} 
		map[tankX][tankY] = '>';
	}
	
	/**
	 * 탱크 머리 모양에 따른 방향 인덱스를 결정해주는 메서드.
	 * 
	 */
	private static int judgeTankDirection(char tankHead) {
		int directionIndex = -1;
		
		switch (tankHead) {
		case '^' :
			directionIndex = 0;
			break;
		case 'v' :
			directionIndex = 1;
			break;
		case '<' :
			directionIndex = 2;
			break;
		case '>' :
			directionIndex = 3;
			break;
		default :
			break;
		}
		return directionIndex;
	}
	
	/**
	 * 이동할 좌표가 범위 내에 있는지 여부를 반환하는 메서드.
	 * 
	 */
	private static boolean isInside(int nx, int ny) {
		if (nx < 0 || nx >= height || ny < 0 || ny >= width) {
			return false;
		}
		return true;
	}
	
	/**
	 * 이동할 좌표가 범위 내에 있으며, 평지인지 여부를 반환하는 메서드.
	 * 
	 */
	private static boolean isLand(int nx, int ny) {
		if (isInside(nx, ny) && map[nx][ny] == '.') {
			return true;
		}
		return false;
	}
	
	/**
	 * 초기 세팅을 진행하는 메서드.
	 * 
	 */
	private static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		height = Integer.parseInt(st.nextToken());
		width = Integer.parseInt(st.nextToken());
		map = new char[height][width];
		
		for (int rowIndex = 0; rowIndex < height; rowIndex++) {
			String line = br.readLine();
			for (int colIndex = 0; colIndex < width; colIndex++) {
				char curChar = line.charAt(colIndex);
				if (curChar == '<' || curChar == 'v' || curChar == '>' || curChar == '^') {
					// 초기 탱크 위치를 발견한 경우 할당
					tankDirection = judgeTankDirection(curChar);
					tankX = rowIndex;
					tankY = colIndex;
				}
				map[rowIndex][colIndex] = curChar;
			}
		}
		countOfCommands = Integer.parseInt(br.readLine());
		commands = br.readLine();
	}
	
	/**
	 * 최종 결과를 삽입하는 메서드.
	 * 
	 */
	private static void insertResultToStringBuilder(int testCase) {
		sb.append("#").append(testCase).append(" ");
		StringBuilder result = new StringBuilder();
		for (int rowIndex = 0; rowIndex < height; rowIndex++) {
			for (int colIndex = 0; colIndex < width; colIndex++) {
				result.append(map[rowIndex][colIndex]);
			}
			result.append('\n');
		}
		sb.append(result);
	}
}
