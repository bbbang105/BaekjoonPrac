import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * [조건]
 * 1. 사이즈 N은 7이상 12이하이다.
 * 2. 코어의 개수는 1개이상 12개 이하이다.
 * 3. 전원이 연결되지 않는 코어가 존재할 수 있다.
 * 4. 0은 빈 셀, 1은 코어를 의미한다.
 * 
 * [풀이]
 * 1. 입력받으며 코어라면 리스트에 담는다.
 * 	1-1. 벽에 붙어있는 코어는 무조건 연결가능하므로 리스트에 담지 않는다.
 * 2. 저장한 모든 코어를 대상으로 상하좌우로 연결을 시도한다.
 * 	2-1. 지금은 조금 더 연결이 길어도, 이것이 최소값이 될 수도 있기 때문에 모든 경우를 고려한다. (백트래킹)
 * 	2-2. 해당 방향으로 최대한 움직이며, 중간에 선을 만나면 이동하지 않는다.
 * 	2-3. 연결에 성공했다면, 방문 처리를 한 후 다음 코어로 넘어간다.
 * 3. 결과값을 계속 추가한 후, 마지막에 정렬하여 최종 값을 구한다.
 * 
 */
public class Solution {
	
	private static final int[] DX = {-1, 1, 0, 0};
	private static final int[] DY = {0, 0, -1, 1};
	
	private static BufferedReader br;
	private static StringBuilder sb;
	private static StringTokenizer st;
	
	private static List<Result> results;
	private static int size;
	private static int[][] cells;
	private static boolean[] visitedCores;
	private static List<Core> cores;
	private static int countOfLinkedCores;
	private static int countOfLines;
	

	public static void main(String[] args) throws IOException {
		sb = new StringBuilder();
		br = new BufferedReader(new InputStreamReader(System.in));
		int testCaseNum = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= testCaseNum; testCase++) {
			init();
			findResult(0, countOfLinkedCores, 0);
			Collections.sort(results);
			sb.append("#").append(testCase).append(" ").append(results.get(0).countOfLines).append('\n');
		}
		System.out.println(sb);
	}
	
	/**
	 * 결과를 도출하는 메서드.
	 * 백트래킹을 활용한다.
	 */
	private static void findResult(int idx, int countOfLinkedCores, int countOfLines) {
		results.add(new Result(countOfLinkedCores, countOfLines));
		
		Core core;
		int x, y, nx, ny;
		for (int coreIndex = idx; coreIndex < cores.size(); coreIndex++) {
			if (visitedCores[coreIndex]) {
				continue;
			}
			core = cores.get(coreIndex);
			x = core.x;
			y = core.y;
			for (int directionIndex = 0; directionIndex < 4; directionIndex++) {
				if (canMoveThisDirection(directionIndex, x, y)) {
					int amount = 1;
					while (true) {
						// 1. 방문 처리를 해줌(= 선 연결)
						nx = x + DX[directionIndex] * amount;
						ny = y + DY[directionIndex] * amount;
						if (nx < 0 || nx >= size || ny < 0 || ny >= size) {
							// 가장자리를 넘으면 종료
							break;
						}
						cells[nx][ny] = 2; // 2는 선을 의미함
						amount++;
					}
					// 2. 다음 코어로 이동
					visitedCores[coreIndex] = true;
					findResult(coreIndex + 1, countOfLinkedCores + 1, countOfLines + amount - 1);
					// 3. 탐색을 마쳤다면 다시 미방문 처리
					visitedCores[coreIndex] = false;
					amount = 1;
					while (true) {
						nx = x + DX[directionIndex] * amount;
						ny = y + DY[directionIndex] * amount;
						if (nx < 0 || nx >= size || ny < 0 || ny >= size) {
							// 가장자리를 넘으면 종료
							break;
						}
						cells[nx][ny] = 0; // 선을 제거함
						amount++;
					}
				}
			}
		}
	}
	
	/**
	 * 해당 방향으로 쭉 이동해 연결할 수 있는지 여부를 반환하는 메서드.
	 */
	private static boolean canMoveThisDirection(int directionIndex, int x, int y) {
		boolean isPossible = true;
		int amount = 1;
		int nx, ny;
		
		while (true) {
			nx = x + DX[directionIndex] * amount;
			ny = y + DY[directionIndex] * amount;
			if (nx < 0 || nx >= size || ny < 0 || ny >= size) {
				break;
			}
			if (cells[nx][ny] != 0) {
				// 이미 선이 있거나 || 코어가 있는 경우 이동 불가능
				isPossible = false;
				break;
			}
			amount++;
		}
		return isPossible;
	}
	
	/**
	 * 초기 세팅을 진행하는 메서드.
	 */
	private static void init() throws IOException {
		results = new ArrayList<>();
		size = Integer.parseInt(br.readLine());
		cells = new int[size][size];
		cores = new ArrayList<>();
		countOfLinkedCores = 0;
		countOfLines = 0;
		
		int cur;
		for (int rowIndex = 0; rowIndex < size; rowIndex++) {
			st = new StringTokenizer(br.readLine());
			for (int colIndex = 0; colIndex < size; colIndex++) {
				cur = Integer.parseInt(st.nextToken());
				cells[rowIndex][colIndex] = cur;
				if (cur == 1) {
					// 코어인 경우
					
					if (rowIndex == 0 
							|| rowIndex == size - 1
							|| colIndex == 0
							|| colIndex == size - 1
					) {
						// 벽에 붙어 있다면 고려 x
						continue;
					}
					cores.add(new Core(rowIndex, colIndex));
				}
			}
		}
		visitedCores = new boolean[cores.size()];
	}
	
	/**
	 *  코어의 위치를 담은 클래스.
	 */
	static class Core {
		int x;
		int y;
		
		Core(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	/**
	 * 결과 정보를 담은 클래스.
	 * 코어 개수로 내림차순 -> 연결 선 개수로 오름차순 정렬한다.
	 */
	static class Result implements Comparable<Result> {
		int countOfLinkedCores;
		int countOfLines;
		
		Result(int countOfLinkedCores, int countOfLines) {
			this.countOfLinkedCores = countOfLinkedCores;
			this.countOfLines = countOfLines;
		}

		@Override
		public int compareTo(Result o) {
			if (this.countOfLinkedCores == o.countOfLinkedCores) {
				return this.countOfLines - o.countOfLines;
			}
			return o.countOfLinkedCores - this.countOfLinkedCores;
		}
	}
}
