import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * [조건]
 * 1. 수열이 길이 N은 1이상 1,000이하이다.
 * 2. 각 수열의 원소는 1이상 2^31-1 이하의 자연수이다.
 * 3. 최대 증가 부분 수열의 길이를 출력하라.
 * 
 * [풀이]
 * 1. 첫번째 원소부터 마지막 원소까지 탐색을 진행한다.
 * 2. 만약 본인보다 큰 수를 만난다면, 비교를 진행한다.
 * 	2-1. dp[본인] + 1 > dp[본인보다 큰 수] 라면 갱신해 준다.
 * 3. 탐색이 종료된 후 dp 배열에서의 최댓값을 결과로 한다. 
 */
public class Solution {
	
	private static BufferedReader br;
	private static StringTokenizer st;
	
	private static int result;
	private static int countOfNumbers;
	private static int[] dp;
	private static int[] nums;

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
	 * LCS 알고리즘을 활용하여 결과를 도출하는 메서드.
	 */
	private static void findResult() {
		int curNum, nextNum;
		for (int curIndex = 0; curIndex < countOfNumbers - 1; curIndex++) {
			for (int nextIndex = curIndex + 1; nextIndex < countOfNumbers; nextIndex++) {
				curNum = nums[curIndex];
				nextNum = nums[nextIndex];
				
				if (nextNum > curNum) {
					if (dp[curIndex] + 1 > dp[nextIndex]) {
						dp[nextIndex] = dp[curIndex] + 1;
					}
				}
			}
		}
		// 최대값 찾기
		for (int index = 0; index < countOfNumbers; index++) {
			result = Math.max(result, dp[index]);
		}
	}
	
	/**
	 * 초기 세팅 메서드.
	 */
	private static void init() throws IOException {
		result = 0;
		countOfNumbers = Integer.parseInt(br.readLine());
		dp = new int[countOfNumbers];
		Arrays.fill(dp, 1); // 기본 길이 1로 채움
		nums = new int[countOfNumbers];
		
		st = new StringTokenizer(br.readLine());
		for (int index = 0; index < countOfNumbers; index++) {
			nums[index] = Integer.parseInt(st.nextToken());
		}
	}
}
