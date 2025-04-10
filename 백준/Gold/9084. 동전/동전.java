import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [조건 및 풀이]
 * 1. 냅색 알고리즘을 활용한다.
 * 2. 중복이 가능하기 때문에, 작은 숫자부터 limit까지 탐색하며 갱신한다.
 * 3. dp[limit]을 출력한다.
 */
public class Main {
	
	private static BufferedReader br;
	private static StringTokenizer st;
	
	private static int countOfCoins;
	private static int limit;
	private static int[] dp;
	private static int[] coins;
	private static int result;
	
	
	/**
	 * 메인 메서드.
	 */
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		br = new BufferedReader(new InputStreamReader(System.in));
		int testCaseNum = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= testCaseNum; testCase++) {
			init();
			knapsack();
			sb.append(result).append('\n');
		}
		System.out.println(sb);
	}
	
	/**
	 * Knapsack Algorithm.
	 */
	private static void knapsack() {
		int coin;
		for (int index = 0; index < countOfCoins; index++) {
			coin = coins[index];
			
			for (int cur = coin; cur <= limit; cur++) {
				dp[cur] += dp[cur - coin];
			}
		}
		result = dp[limit];
	}
	
	/**
	 * 초기 세팅 메서드.
	 */
	private static void init() throws IOException {
		result = 0;
		countOfCoins = Integer.parseInt(br.readLine());
		coins = new int[countOfCoins];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int index = 0; index < countOfCoins; index++) {
			coins[index] = Integer.parseInt(st.nextToken());
		}
		limit = Integer.parseInt(br.readLine());
		dp = new int[limit + 1];
		dp[0] = 1;
	}
}
