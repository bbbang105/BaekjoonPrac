import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [조건 및 풀이]
 * 1. 공부 시간(배낭 무게 제한) N은 1이상 10,000이하이다.
 * 2. 과목 수 K는 1이상 1,000이하이다.
 * 3. 중요도(가치) I는 1이상 100,000이하이다.
 * 4. 필요한 공부시간(무게) T는 1이상 10,000이하이다.
 * 5. 전형적인  Knapsack 알고리즘.
 */
public class Main {
	
	private static int limit;
	private static int countOfSubjects;
	private static int[][] subjects;
	private static int[] dp;
	private static int result;
	
	/**
	 * 메인 메서드.
	 */
	public static void main(String[] args) throws IOException {
		init();
		knapsack();
		System.out.println(result);
	}
	
	/**
	 * Knapsack Algorithm.
	 */
	private static void knapsack() {
		int value, time;
		for (int index = 0; index < countOfSubjects; index++) {
			value = subjects[index][0];
			time = subjects[index][1];
			
			for (int timeLimit = limit; timeLimit >= time; timeLimit--) {
				if (timeLimit < 0) {
					break;
				}
				dp[timeLimit] = Math.max(dp[timeLimit], dp[timeLimit - time] + value);
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
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		limit = Integer.parseInt(st.nextToken());
		countOfSubjects = Integer.parseInt(st.nextToken());
		subjects = new int[countOfSubjects][2];
		dp = new int[limit + 1];
		
		for (int index = 0; index < countOfSubjects; index++) {
			st = new StringTokenizer(br.readLine());
			subjects[index][0] = Integer.parseInt(st.nextToken());
			subjects[index][1] = Integer.parseInt(st.nextToken());
		}
	}
}
