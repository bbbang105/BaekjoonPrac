import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [조건]
 * 1. 물건의 개수 N은 1이상 100이하이다.
 * 2. 가방의 부피 K는 1이상 1,000이하이다.
 * 3. 물건은 각각 부피와 가치를 가진다.
 * 4. 가방에 담을 수 있는 최대 가치를 구하여라.
 * 
 * [풀이]
 * 1. 냅색 알고리즘을 활용한다.
 */
public class Solution {
	
	private static BufferedReader br;
	private static StringTokenizer st;
	
	private static int result;
	private static int[][] things;
	private static int[] dp;
	private static int countOfThings;
	private static int limit;
	
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
	 * 냅색 알고리즘을 활용해 결과를 도출하는 메서드.
	 */
	private static void findResult() {
		int weight, value;
		for (int index = 1; index <= countOfThings; index++) {
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
		st = new StringTokenizer(br.readLine());
		countOfThings = Integer.parseInt(st.nextToken());
		limit = Integer.parseInt(st.nextToken());
		things = new int[countOfThings + 1][2];
		dp = new int[limit + 1];
		
		int weight, value;
		for (int index = 1; index <= countOfThings; index++) {
			st = new StringTokenizer(br.readLine());
			weight = Integer.parseInt(st.nextToken());
			value = Integer.parseInt(st.nextToken());
			things[index][0] = weight;
			things[index][1] = value;
		}
	}
}
