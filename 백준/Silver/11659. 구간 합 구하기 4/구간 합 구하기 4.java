import java.util.*;
import java.io.*;

/**
 * 
 * [조건]
 * 1. 수의 개수 N은 1이상 100,000이하이다.
 * 2. 합을 구해야 하는 개수 M은 1이상 100,000이하이다.
 * 3. 수는 1,000보다 작거나 같은 자연수이다.
 * 4. M개 만큼의 구간 합을 구해서 줄바꿈으로 출력하라.
 * 
 * [풀이]
 * 1. 구간합은 누적합을 활용해서 풀 수 있다.
 * 2. 1차원 배열이므로, 입력과 동시에 누적합 배열을 구성해준다.
 * 3. 해당 배열을 활용하여 startIndex, endIndex에 따른 구간값을 구해 스트링빌더에 삽입한다.
 *
 */
public class Main {
	
	private static BufferedReader br;
	private static StringTokenizer st;
	private static StringBuilder sb;
	private static int[] prefixSum;   // 누적합 배열
	private static int countOfNum; 	  // 수의 개수
	private static int countOfAnswer; // 합을 구해야 하는 개수
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		init();
		
		sb = new StringBuilder();
		for (int answerIndex = 0; answerIndex < countOfAnswer; answerIndex++) {
			st = new StringTokenizer(br.readLine());
			int startIndex = Integer.parseInt(st.nextToken());
			int endIndex = Integer.parseInt(st.nextToken());
			calculateAndSaveIntervalSum(startIndex - 1, endIndex);
		}
		System.out.print(sb);
	}
	
	/**
	 * 입력을 받고 누적합을 계산하여 배열을 구성하는 메서드.
	 * 
	 */
	private static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		countOfNum = Integer.parseInt(st.nextToken());
		countOfAnswer = Integer.parseInt(st.nextToken());
		
		prefixSum = new int[countOfNum + 1];
		st = new StringTokenizer(br.readLine());
		for (int numIndex = 1; numIndex <= countOfNum; numIndex++) {
			// 입력 받는 동시에 누적합 계산
			prefixSum[numIndex] = prefixSum[numIndex - 1] + Integer.parseInt(st.nextToken());
		}
	}
	
	/**
	 * 시작, 종료 인덱스에 따라 누적합을 활용해 구간합을 구한 후 스트링빌더에 삽입하는 메서드.
	 * 
	 */
	private static void calculateAndSaveIntervalSum(int startIndex, int endIndex) {
		sb.append(prefixSum[endIndex] - prefixSum[startIndex]).append('\n');
	}
}
