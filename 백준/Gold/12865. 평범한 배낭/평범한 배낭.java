import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * [조건 및 풀이]
 * 1. 물품의 수 N은 1이상 100이하이다.
 * 2. 무게 K는 1이상 100,000이하이다.
 * 3. 각 물건의 무게와 가치가 주어진다.
 * 4. 입력 받는 모든 수는 정수이다.
 * 5. 배낭에 넣을 수 있는 물건들의 가치합의 최댓값을 출력하라.(Knapsack)
 */
public class Main {
	
	private static int[] dp;
	private static int[][] things;
	private static int result;
	private static int countOfThings;
	private static int limit;

	/**
	 * 메인 메서드.
	 */
	public static void main(String[] args) throws IOException {
		init();
		findResult();
		System.out.println(result);
	}
	
	/**
	 * Knapsack 알고리즘.
	 */
	private static void findResult() {
		int weight, value;
		for (int index = 0; index < countOfThings; index++) {
			weight = things[index][0];
			value = things[index][1];
			
			for (int bagLimit = limit; bagLimit >= weight; bagLimit--) {
				dp[bagLimit] = Math.max(dp[bagLimit], dp[bagLimit - weight] + value);
			}
		}
		
		for (int num : dp) {
			result = Math.max(result, num);
		}
	}
	
	/**
	 * 초기 세팅 메서드.
	 */
	private static void init() throws IOException {
		result = 0;
		StringTokenizer st;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		countOfThings = Integer.parseInt(st.nextToken());
		limit = Integer.parseInt(st.nextToken());
		dp = new int[limit + 1];
		things = new int[countOfThings][2];
		
		for (int index = 0; index < countOfThings; index++) {
			st = new StringTokenizer(br.readLine());
			things[index][0] = Integer.parseInt(st.nextToken());
			things[index][1] = Integer.parseInt(st.nextToken());
		}
	}
}
