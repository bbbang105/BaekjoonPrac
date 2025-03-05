import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [조건]
 * 1. 크기가 2^N X 2^N인 2차원 배열을 Z모양으로 탐색한다.
 * 2. 방문 순서는 왼쪽 위 -> 오른쪽 위 -> 왼쪽 아래 -> 오른쪽 아래 순이다.
 * 3. N > 1이라면, 배열을 4등분 한 후에 재귀적으로 탐색을 수행한다.
 * 4. 사이즈 N, 행 r, 열 c가 주어졌을 때, 해당 위치가 몇 번째 방문인지 출력한다.
 *
 * [최적화된 풀이]
 * 1. 탐색하는 범위를 4등분하여 현재 좌표가 속한 사분면만 탐색한다.
 * 2. 목표 좌표가 속하지 않은 사분면은 탐색하지 않고, 건너뛴 개수만큼 result를 증가시킨다.
 * 3. 목표 위치에 도달하면 즉시 탐색을 종료한다.
 */
public class Main {
	private static int findRowIndex;
	private static int findColIndex;
	private static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int size = Integer.parseInt(st.nextToken());
		findRowIndex = Integer.parseInt(st.nextToken());
		findColIndex = Integer.parseInt(st.nextToken());
		result = 0;

		divideAndConquer((int) Math.pow(2, size), 0, 0);
		System.out.println(result);
	}

	/**
	 * 분할 정복을 이용하여 목표 좌표의 방문 순서를 찾는다.
	 *
	 */
	private static void divideAndConquer(int moveAmount, int x, int y) {
		// 탐색할 범위가 1x1 크기면 종료
		if (moveAmount == 1) {
			return;
		}

		int next = moveAmount / 2; // 현재 크기를 반으로 나눈 크기
		int areaSize = next * next; // 한 사분면의 칸 개수

		// 목표 좌표가 속한 사분면을 찾아 이동 (건너뛸 사분면은 result를 증가시킴)
		if (findRowIndex < x + next && findColIndex < y + next) { // 왼쪽 위
			divideAndConquer(next, x, y);
		} else if (findRowIndex < x + next && findColIndex >= y + next) { // 오른쪽 위
			result += areaSize;
			divideAndConquer(next, x, y + next);
		} else if (findRowIndex >= x + next && findColIndex < y + next) { // 왼쪽 아래
			result += areaSize * 2;
			divideAndConquer(next, x + next, y);
		} else { // 오른쪽 아래
			result += areaSize * 3;
			divideAndConquer(next, x + next, y + next);
		}
	}
}
