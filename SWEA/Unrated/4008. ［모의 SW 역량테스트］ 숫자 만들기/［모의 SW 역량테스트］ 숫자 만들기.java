import java.util.*;
import java.io.*;

/**
 * [조건]
 * 1. 숫자의 개수 N은 3이상 12이하이다.
 * 2. 연산자 카드 개수의 총합은 항상 N-1이다.
 * 3. 연산자 카드를 모두 사용해서 수식을 만들어야 한다.
 * 4. 연산자 우선순위는 고려하지 않고, 좌에서 우로 차례대로 계산한다.
 * 5. 나눗셈을 계산할 때 소숫점은 고려하지 않는다.
 * 6. 연산 중 값은 -1억 ~ 1억으로 보장된다.
 *
 * [풀이]
 * 1. 연산자와 숫자를 저장한다.
 * 2. 백트래킹을 통해서 사용할 수 있는 연산자가 남아있다면 계산한다.
 * 3. index == N, 즉 모든 연산자와 숫자를 사용했다면 결과를 갱신한다.
 *
 */
public class Solution {
	
	private static final char[] CMD = {'+', '-', '*', '/'};

    private static int[] operators = new int[4]; // +, -, *, /
    private static int[] nums;
    private static int N;
    private static int maxResult, minResult;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCaseNum = Integer.parseInt(br.readLine().trim());

        StringBuilder sb = new StringBuilder();
        for (int testCase = 1; testCase <= testCaseNum; testCase++) {
            init(br);
            maxResult = Integer.MIN_VALUE;
            minResult = Integer.MAX_VALUE;

            backTracking(1, nums[0]);

            sb.append("#").append(testCase).append(" ").append(maxResult - minResult).append('\n');
        }
        System.out.print(sb);
    }

    /**
     * 입력 값을 받아 초기 세팅을 진행하는 메서드.
     */
    private static void init(BufferedReader br) throws IOException {
        N = Integer.parseInt(br.readLine().trim());
        nums = new int[N];

        // 연산자 개수 입력 (+, -, *, / 순서)
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            operators[i] = Integer.parseInt(st.nextToken());
        }

        // 숫자 리스트 입력
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
    }

    /**
     * 연산자 순열을 직접 탐색하며 계산하는 메서드.
     */
    private static void backTracking(int index, int result) {
        if (index == N) {
            maxResult = Math.max(maxResult, result);
            minResult = Math.min(minResult, result);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (operators[i] > 0) {
                operators[i]--;

                int newResult = operate(result, CMD[i], nums[index]);
                backTracking(index + 1, newResult);

                operators[i]++;
            }
        }
    }

    /**
     * 연산자와 피연산자를 받아 계산하는 메서드.
     */
    private static int operate(int operator, char cmd, int operand) {
        switch (cmd) {
            case '+': return operator + operand;
            case '-': return operator - operand;
            case '*': return operator * operand;
            case '/': return (int) operator / operand;
            default: throw new IllegalArgumentException("Invalid operator: " + cmd);
        }
    }
}
