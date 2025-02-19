import java.util.*;
import java.io.*;

/**
 * 
 * [조건]
 * 1. 달팽이 크기 N은 1이상 10이하의 정수이다.
 * 2. 1부터 N*N의 숫자가 시계방향으로 이루어져 있다.
 * 3. 1번 이동할 때 마다 기록하는 값이 1씩 커진다. (초기값 1)
 *
 * [풀이]
 * 1. 우하좌상 방향으로 순환하며 움직인다.
 * 2. (0,0)은 1로 기본적으로 값을 넣는다.
 */
public class Solution {
	
	// 우 하 좌 상
	private static final int[] DX = {0, 1, 0, -1};
	private static final int[] DY = {1, 0, -1, 0};
	
	private static int[][] snail;
	private static int snailSize;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCaseNum = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for (int testCase = 1; testCase <= testCaseNum; testCase++) {
			snailSize = Integer.parseInt(br.readLine());
			snail = new int[snailSize][snailSize];
			makeSnail();
			sb.append("#").append(testCase).append('\n').append(makeOutput());
		}
		System.out.print(sb);
	}
	
	/**
	 * 달팽이에 값을 기록하며 생성하는 메서드.
	 * 
	 */
	private static void makeSnail() {
		int directionIndex = 0;
		int curX = 0;
		int curY = 0;
		snail[0][0] = 1; // 초기값 설정
		
		int nx;
		int ny;
		
		for (int curNum = 2; curNum <= snailSize * snailSize; curNum++) {
			nx = curX + DX[directionIndex];
			ny = curY + DY[directionIndex];
			
			if (nx < 0 || nx >= snailSize || ny < 0 || ny >= snailSize || snail[nx][ny] != 0) {
				// 범위를 넘거나 이미 값이 있는 곳에 도달한 경우
				// 방향을 변경한 후 재할당
				directionIndex = (directionIndex + 1) % 4;
				nx = curX + DX[directionIndex];
				ny = curY + DY[directionIndex];
			}
			// 값 갱신
			snail[nx][ny] = curNum;
			curX = nx;
			curY = ny;
		}
	}
	
	/**
	 * 최종적인 출력값을 생성해 반환하는 메서드. 
	 *
	 */
	private static StringBuilder makeOutput() {
		StringBuilder snailString = new StringBuilder();
		for (int rowIndex = 0; rowIndex < snailSize; rowIndex++) {
			for (int colIndex = 0; colIndex < snailSize; colIndex++) {
				snailString.append(snail[rowIndex][colIndex]).append(" ");
			}
			snailString.append('\n');
		}
		return snailString;
	}
}
