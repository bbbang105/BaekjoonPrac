import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [조건]
 * 1. 수영장 이용권은 1일, 1달, 3달, 1년 4종류이다.
 * 2. 모든 종류의 이용권 요금은 10이상 3,000이하의 정수이다.
 * 3. 각 달의 이용 계획은 각 달의 마지막 일자보다 크지 않다.
 * 4. 각 이용권의 요금과 각 달의 이용 계획이 주어질 때, 가장 적은 비용으로 수영장을 이용할 수 있는 방법을 찾아 그 값을 출력하라.
 * 
 * [풀이]
 * 1. dp[i] = i월까지의 최소 비용으로 정의한다.
 * 2. 각 월마다 1일권, 1달권, 3달권을 비교하여 최소 비용을 갱신한다.
 * 3. 마지막에 1년권과 비교하여 최소값을 출력한다.
 */
public class Solution {

    private static BufferedReader br;
    private static StringTokenizer st;

    private static int[] monthlyPlans;
    private static int[] dp;
    private static int oneDayFee, oneMonthFee, threeMonthFee, oneYearFee;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        int testCaseNum = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int testCase = 1; testCase <= testCaseNum; testCase++) {
            init();
            sb.append("#").append(testCase).append(" ").append(findMinimumFee()).append('\n');
        }
        System.out.print(sb);
    }

    /**
     * 초기 세팅을 진행하는 메서드.
     */
    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        oneDayFee = Integer.parseInt(st.nextToken());
        oneMonthFee = Integer.parseInt(st.nextToken());
        threeMonthFee = Integer.parseInt(st.nextToken());
        oneYearFee = Integer.parseInt(st.nextToken());

        monthlyPlans = new int[13];
        dp = new int[13];

        st = new StringTokenizer(br.readLine());
        for (int index = 1; index <= 12; index++) {
            monthlyPlans[index] = Integer.parseInt(st.nextToken());
        }
    }

    /**
     * DP를 활용하여 최소 비용을 구하는 메서드.
     */
    private static int findMinimumFee() {
        for (int i = 1; i <= 12; i++) {
            // 1일권
            int dayFee = dp[i - 1] + monthlyPlans[i] * oneDayFee;

            // 1달권
            int monthFee = dp[i - 1] + oneMonthFee;

            // 3달권
            int threeFee = (i >= 3 ? dp[i - 3] : 0) + threeMonthFee;

            dp[i] = Math.min(dayFee, Math.min(monthFee, threeFee)); // 
        }
        return Math.min(dp[12], oneYearFee);
    }
}
