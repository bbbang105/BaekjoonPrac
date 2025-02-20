import java.util.*;
import java.io.*;

/**
 * 
 * [조건]
 * 1. 2차원 숫자 배열의 크기 N은 1이상 1024이하이다.
 * 2. 합을 구해야 하는 개수 M은 1이상 100,000이하이다.
 * 3. 수는 1,000보다 작거나 같은 자연수이다.
 * 4. M개 만큼 x1,y1,x2,y2 의 입력을 받는다.
 * 5. 4번의 구간값을 구한 후 출력하라.
 * 
 * [풀이]
 * 0. 누적합 & 구간합 문제에서는 본인보다 이전의 값을 가져와서 계산하므로, 시작 인덱스를 (1,1)로 잡는다.
 * 1. 구간합은 누적합을 활용해서 풀 수 있다.
 * 2. 입력을 받는 동시에 2차원 누적합 배열을 구성해 준다.
 * 3. 해당 배열을 활용하여 x1,y1,x2,y2에 따른 구간값을 구해 스트링빌더에 삽입한다.
 *
 */
public class Main {
	
	private static BufferedReader br;
	private static StringTokenizer st;
	private static StringBuilder sb;
	private static int[][] nums;   		// 숫자 배열
	private static int[][] prefixSum;   // 누적합 배열
	private static int numsSize; 	  	// 2차원 배열 사이즈
	private static int countOfAnswer; 	// 합을 구해야 하는 개수
	
	public static void main(String[] args) throws IOException {
		initAndCreatePrefixSum();
		
		sb = new StringBuilder();
		for (int answerIndex = 0; answerIndex < countOfAnswer; answerIndex++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			calculateAndSaveIntervalSum(x1, y1, x2, y2);
		}
		System.out.print(sb);
	}
	
	/**
	 * 입력을 받고 2차원 누적합 배열을 구성하는 메서드.
	 * 
	 */
	private static void initAndCreatePrefixSum() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		numsSize = Integer.parseInt(st.nextToken());
		countOfAnswer = Integer.parseInt(st.nextToken());
		
		prefixSum = new int[numsSize + 1][numsSize + 1];
		for (int rowIndex = 1; rowIndex <= numsSize; rowIndex++) {
			st = new StringTokenizer(br.readLine());
			for (int colIndex = 1; colIndex <= numsSize; colIndex++) {
				prefixSum[rowIndex][colIndex] = Integer.parseInt(st.nextToken()) +
                        + prefixSum[rowIndex - 1][colIndex] 
                        + prefixSum[rowIndex][colIndex - 1] 
                        - prefixSum[rowIndex - 1][colIndex - 1];
			}
		}
	}
	
	/**
	 * x1,y1,x2,y2에 따라 누적합을 활용해 구간합을 구한 후, 스트링빌더에 삽입하는 메서드.
	 * 
	 */
	private static void calculateAndSaveIntervalSum(int x1, int y1, int x2, int y2) {
		int intervalSum = prefixSum[x2][y2] 
                - prefixSum[x1 - 1][y2] 
                - prefixSum[x2][y1 - 1] 
                + prefixSum[x1 - 1][y1 - 1];
				
		sb.append(intervalSum).append('\n');
	}
}
