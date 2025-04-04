import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [조건]
 * 1. 재료의 수 N은 1이상 20이하이다.
 * 2. 제한 칼로리 L은 1이상 10의 4승 이하이다.
 * 3. 같은 재료를 여러 번 사용할 수 없다.
 *
 * [풀이]
 * 1. 부분 집합 탐색 대신 0/1 Knapsack DP로 해결한다.
 * 2. dp[c] = 칼로리 c일 때 최대 맛 점수로 정의한다.
 * 3. 각 재료마다 뒤에서부터 dp를 갱신하여 중복 선택을 방지한다.
 * 4. 최종적으로 dp[0] ~ dp[L] 중 최대값을 출력한다.
 */
public class Solution {

    private static BufferedReader br;
    private static StringTokenizer st;
    private static int[][] ingredients;
    private static int ingredientCounts;
    private static int caloryLimit;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        int testCaseNum = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int testCase = 1; testCase <= testCaseNum; testCase++) {
            init();
            int maxScore = solveByDP();
            sb.append("#").append(testCase).append(" ").append(maxScore).append('\n');
        }
        System.out.print(sb);
    }

    /**
     * 값들을 입력 받고 배열을 구성하는 메서드.
     */
    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        ingredientCounts = Integer.parseInt(st.nextToken());
        caloryLimit = Integer.parseInt(st.nextToken());
        ingredients = new int[ingredientCounts][2];

        for (int i = 0; i < ingredientCounts; i++) {
            st = new StringTokenizer(br.readLine());
            ingredients[i][0] = Integer.parseInt(st.nextToken()); // 점수
            ingredients[i][1] = Integer.parseInt(st.nextToken()); // 칼로리
        }
    }

    /**
     * DP를 활용하여 최대 점수를 구하는 메서드.
     */
    private static int solveByDP() {
        int[] dp = new int[caloryLimit + 1];

        for (int i = 0; i < ingredientCounts; i++) {
            int score = ingredients[i][0];
            int calory = ingredients[i][1];

            // 뒤에서부터 갱신 -> 중복 선택 방지
            for (int c = caloryLimit; c >= calory; c--) {
                dp[c] = Math.max(dp[c], dp[c - calory] + score);
            }
        }

        int max = 0;
        for (int v : dp) max = Math.max(max, v);
        return max;
    }
}
