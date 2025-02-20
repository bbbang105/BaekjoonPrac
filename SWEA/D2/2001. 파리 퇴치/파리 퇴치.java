import java.io.*;
import java.util.*;

/**
 * [조건]
 * 1. N x N 크기의 배열이 주어지며, 각 칸의 숫자는 해당 위치에 존재하는 파리의 개수를 의미한다.
 * 2. M x M 크기의 파리채를 한 번 내리쳐 최대한 많은 파리를 잡을 때의 개수를 구해야 한다.
 *
 * [풀이]
 * 1. prefixSum 배열을 생성하여 (0,0)부터 (i,j)까지의 합을 저장한다.
 * 2. M x M 영역의 합을 누적합을 이용해서 구한다.
 * 3. 누적합 계산은 (1,1)부터 시작하며, [i-1][j] 와 [i][j-1] 은 더하고 [i-1][j-1] 은 빼준다.
 * 3. 최대 파리 개수를 비교 및 갱신한다.
 * 4. 최종적으로 maxKillCount를 스트링빌더에 삽입한다.
 */
public class Solution {

    private static BufferedReader br;
    private static int[][] flies; // 파리 정보가 담긴 2차원 배열
    private static int[][] prefixSum; // 누적합 배열
    private static int boardSize; // 배열 크기 (N)
    private static int toolSize;  // 파리채 크기 (M)
    private static int maxKillCount;  // 최대로 죽일 수 있는 파리 개수

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        int testCaseNum = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int testCase = 1; testCase <= testCaseNum; testCase++) {
            init();  // 입력값 초기화
            calculatePrefixSum(); // 누적합 계산
            findMaxKillCount();  // 최대 파리 개수 찾기
            sb.append("#").append(testCase).append(" ").append(maxKillCount).append('\n');
        }
        System.out.print(sb);
    }

    /**
     * 입력 받아 파리 정보 배열을 구성하는 메서드.
     */
    private static void init() throws IOException {
        maxKillCount = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        boardSize = Integer.parseInt(st.nextToken());
        toolSize = Integer.parseInt(st.nextToken());
        flies = new int[boardSize + 1][boardSize + 1]; // 1-based index
        prefixSum = new int[boardSize + 1][boardSize + 1];

        for (int rowIndex = 1; rowIndex <= boardSize; rowIndex++) {
            st = new StringTokenizer(br.readLine());
            for (int colIndex = 1; colIndex <= boardSize; colIndex++) {
                flies[rowIndex][colIndex] = Integer.parseInt(st.nextToken());
            }
        }
    }

    /**
     * 2차원 누적합을 계산하는 메서드.
     */
    private static void calculatePrefixSum() {
        for (int i = 1; i <= boardSize; i++) {
            for (int j = 1; j <= boardSize; j++) {
                prefixSum[i][j] = flies[i][j] 
                                + prefixSum[i - 1][j] 
                                + prefixSum[i][j - 1] 
                                - prefixSum[i - 1][j - 1];
            }
        }
    }

    /**
     * 각 영역의 총 파리 개수를 누적합을 이용해 계산하고, maxKillCount를 비교 및 갱신하는 메서드.
     */
    private static void findMaxKillCount() {
        int enableMoveCount = boardSize - toolSize + 1; // 파리채의 범위

        for (int rowIndex = 1; rowIndex <= enableMoveCount; rowIndex++) {
            for (int colIndex = 1; colIndex <= enableMoveCount; colIndex++) {
                int x1 = rowIndex;
                int y1 = colIndex;
                int x2 = rowIndex + toolSize - 1;
                int y2 = colIndex + toolSize - 1;

                // M x M 영역의 합을 누적합을 이용해 구하기
                int curSum = prefixSum[x2][y2] 
                           - prefixSum[x1 - 1][y2] 
                           - prefixSum[x2][y1 - 1] 
                           + prefixSum[x1 - 1][y1 - 1];

                maxKillCount = Math.max(maxKillCount, curSum);
            }
        }
    }
}
