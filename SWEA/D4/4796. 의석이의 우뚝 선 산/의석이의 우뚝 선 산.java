import java.util.Scanner;

/**
 * [조건]
 * 1. 산의 개수 N은 3이상 50,000이하이다.
 * 2. 각 산의 높이 h는 1ㅇ;싱 10^9이하이다.
 * 3. 우뚝 선 산은 본인을 기준으로, 좌측 숫자와 우측 숫자가 감소하는 구조여야만 한다.
 * 4. 즉, 1 < 4 < 6 > 5 > 3 과 같은 구조이다.
 * 5. 우뚝 선 산이 될 수 있는 구간의 개수를 출력하라.
 * 
 * [풀이]
 * 1. 첫번째 인덱스부터 탐색을 시작하며, 좌측에서부터 높이가 올라가고 있는지 개수를 파악한다.
 * 2. 더이상 올라가지 못 하면 해당 위치에서부터는 오른쪽으로 탐색을 시작하며 개수를 세어준다.
 * 3. 모두 탐색을 완료한 후, 만약 leftCount, rightCount 둘 다 1이상이라면 우뚝 선 산이 가능하므로 leftCount * rightCount을 더해준다.
 */
public class Solution {

    private static Scanner sc = new Scanner(System.in);
    private static int[] mountains;
    private static int N;
    private static int result;

    public static void main(String[] args) {
        int testCaseNum = sc.nextInt();
        StringBuilder sb = new StringBuilder();

        for (int testCase = 1; testCase <= testCaseNum; testCase++) {
            init();
            result = 0;
            findHighestMountainsCount();
            sb.append("#").append(testCase).append(" ").append(result).append('\n');
        }
        System.out.print(sb);
    }

    /**
     * 초기 세팅을 진행하는 메서드.
     */
    private static void init() {
        N = sc.nextInt();
        mountains = new int[N];

        for (int index = 0; index < N; index++) {
            mountains[index] = sc.nextInt();
        }
    }

    /**
     * 우뚝 선 산의 개수를 찾는 메서드.
     */
    private static void findHighestMountainsCount() {
        int leftCount = 0; // 왼쪽에서 연속 증가한 개수
        int index = 0;

        while (index < N - 1) {
            // 왼쪽 증가 구간 찾기
            leftCount = 0;
            while (index < N - 1 && mountains[index] < mountains[index + 1]) {
                leftCount++;
                index++;
            }

            if (index == N - 1) {
            	// 끝까지 증가만 하는 경우
            	return;
            }

            // 오른쪽 감소 구간 찾기
            int rightCount = 0;
            while (index < N - 1 && mountains[index] > mountains[index + 1]) {
                rightCount++;
                index++;
            }

            // 좌 우측 둘 다 1 이상이라면 우뚝 선 산이 가능하므로 더해줌
            if (leftCount > 0 && rightCount > 0) {
                result += leftCount * rightCount;
            }
        }
    }
}
