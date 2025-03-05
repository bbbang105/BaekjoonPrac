import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * [조건]
 * 1. 정수  N은 2이상 10^12이하이다.
 * 2. 아래의 2가지 연산이 존재한다. 연산마다 횟수가 1씩 증가한다.
 * 	2-1. N을 N + 1로 바꾼다.
 * 	2-2. 루트 N이 정수일 때, N을 루트 N으로 바꿀 수 있다.
 * 3. 게임의 목표는 N을 2로 만드는 것이다. N을 2로 만들기 위한 횟수의 최솟값을 구하라.
 *
 */
public class Solution {
    private static final int TARGET_NUMBER = 2;
    private static BufferedReader br;
    private static StringBuilder sb;
    private static long curNum;
    private static int minimumCount;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        int testCaseNum = Integer.parseInt(br.readLine());
        sb = new StringBuilder();

        for (int testCase = 1; testCase <= testCaseNum; testCase++) {
            init();
            findMinimumCount();
            sb.append("#").append(testCase).append(" ").append(minimumCount).append('\n');
        }
        System.out.println(sb);
    }

    /**
     * 최소 연산 횟수를 찾는 메서드.
     * 
     */
    private static void findMinimumCount() {
        while (curNum != TARGET_NUMBER) {
            // 현재 값이 제곱수인지 확인
            long sqrt = (long) Math.sqrt(curNum);
            if (sqrt * sqrt == curNum) {
                curNum = sqrt;
                minimumCount++;
            } else {
                // 제곱수가 아닐 경우, 가장 가까운 제곱수로 이동
                long nextPerfectSquare = (sqrt + 1) * (sqrt + 1);
                minimumCount += (nextPerfectSquare - curNum); // +1 연산 횟수 추가
                curNum = nextPerfectSquare; // 이동 후 변환
            }
        }
    }

    /**
     * 초기 세팅을 진행하는 메서드.
     * 
     */
    private static void init() throws IOException {
        curNum = Long.parseLong(br.readLine());
        minimumCount = 0;
    }
}
