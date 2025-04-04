import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [조건]
 * 1. N과 M은 1이상 30이하이며, N은 M이하이다.
 * 2. N개 만큼 다리를 지어야 하며, 교차하면 안된다.
 * 3. 교차하지 않는다는 것은 중복 조합이 아닌 조합을 활용해야 함을 의미한다.
 * 4. 다리를 지을 수 있는 경우의 수를 구하라.
 *
 * [풀이]
 * 1. 다리를 놓는 경우는 조합 문제로, M개 중 N개를 고르는 경우의 수이다.
 * 2. 조합 공식을 그대로 쓰면 팩토리얼 오버플로우가 발생할 수 있으므로,
 *    곱하면서 나누는 방식으로 안전하게 계산한다.
 */
public class Main {

	private static long result;
	private static int N;
	private static int M;

	private static BufferedReader br;
	private static StringTokenizer st;

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
			sb.append(result).append('\n');
		}
		System.out.println(sb);
	}

	/**
	 * 조합 계산을 통해 결과를 도출하는 메서드.
	 */
	private static void findResult() {
		result = combination(M, N);
	}

	/**
	 * 조합을 안전하게 계산하는 메서드.
	 */
	private static long combination(int n, int r) {
		long res = 1;
		for (int i = 1; i <= r; i++) {
			res *= (n - i + 1);
			res /= i;
		}
		return res;
	}

	/**
	 * 초기 세팅 메서드.
	 */
	private static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
	}
}
