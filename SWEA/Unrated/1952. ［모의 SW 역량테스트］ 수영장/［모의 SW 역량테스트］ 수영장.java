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
 * 1. 첫번째 인덱스부터 탐색을 시작한다.
 * 2. 이용 계획이 없다면 건너뛴다.
 * 3. 1일권, 1달권, 3달권을 사용하는 경우로 탐색한다.
 * 4. 인덱스가 12이상이라면 최소값을 비교 후 갱신한다. (11, 12월에도 3달권을 구입할 수 있다.)
 * *
 */
public class Solution {

    private static BufferedReader br;
    private static StringTokenizer st;
    private static int[] monthlyPlans;
    private static int oneDayFee, oneMonthFee, threeMonthFee, oneYearFee;
    private static int minimumFee;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        int testCaseNum = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int testCase = 1; testCase <= testCaseNum; testCase++) {
            init();
            minimumFee = oneYearFee;
            findMinimumFee(1, 0);
            sb.append("#").append(testCase).append(" ").append(minimumFee).append('\n');
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
        st = new StringTokenizer(br.readLine());
        for (int index = 1; index <= 12; index++) {
            monthlyPlans[index] = Integer.parseInt(st.nextToken());
        }
    }

    /**
     * 백트래킹을 활용하여 최소 비용을 찾는 메서드.
     */
    private static void findMinimumFee(int index, int sum) {
        if (index > 12) {
            minimumFee = Math.min(minimumFee, sum);
            return;
        }

        // 이용 계획이 없으면 건너뜀
        if (monthlyPlans[index] == 0) {
            findMinimumFee(index + 1, sum);
            return;
        }

        // 1일권 구입
        findMinimumFee(index + 1, sum + (monthlyPlans[index] * oneDayFee));

        // 1달권 구입
        findMinimumFee(index + 1, sum + oneMonthFee);

        // 3달권 구입
        findMinimumFee(index + 3, sum + threeMonthFee);
    }
}
