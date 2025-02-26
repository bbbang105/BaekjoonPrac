import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [조건]
 * 1. 점원의 수 N은 1이상 20이하이다.
 * 2. 점원들 키의 합은 S이며, 탑의 높이  B는 1이상 S이하이다.
 * 3. 각 점원의 키 H는 1이상 10,000이하이다.
 * 
 * [풀이]
 * 1. 조합을 구한다.
 * 2. 만약 탑의 높이가  B이상이 되었다면, 최소값을 비교한 후 리턴한다. 
 * 3. 최종적인 최소 차이 값을 출력한다.
 *
 */
public class Solution {
	
	private static BufferedReader br;
	private static StringTokenizer st;
	private static int[] people;
	private static int N;
	private static int B;
	private static int result;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int testCaseNum = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= testCaseNum; testCase++) {
			init();
			findMinimumDistance(0, 0);
			sb.append("#").append(testCase).append(" ").append(result).append('\n');
		}
		System.out.println(sb);
	}
	
	/**
	 * 초기 세팅을 진행하는 메서드.
	 * 
	 */
	private static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 점원의 수
		B = Integer.parseInt(st.nextToken()); // 선반의 높이
		people = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int index = 0; index < N; index++) {
			people[index] = Integer.parseInt(st.nextToken());
		}
		result = Integer.MAX_VALUE;
	}
	
	/**
	 * 최소 차이값을 갱신하며 찾는 메서드.
	 * 
	 */
	private static void findMinimumDistance(int curIndex, int topHeight) {
		if (topHeight >= B) {
			// B 이상이 된 경우 갱신하고 리턴 -> 더 큰 경우는 볼 필요가 없으므로
			result = Math.min(result, topHeight - B);
			return;
		}
		if (curIndex == N) {
			// 기저 조건
			return;
		}
		// 1. 현재 인덱스를 선택한 경우
		findMinimumDistance(curIndex + 1, topHeight + people[curIndex]);
		// 2. 현재 인덱스를 선택하지 않은 경우
		findMinimumDistance(curIndex + 1, topHeight);
	}
}
