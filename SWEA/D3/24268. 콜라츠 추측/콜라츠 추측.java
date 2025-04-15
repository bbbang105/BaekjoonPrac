import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * [조건 및 풀이]
 * 1. 테스트케이스가 개수가 먼저 들어온다.
 * 2. 해당 테스트케이스 수 만큼 반복하며, 1이 될때 까지의 연산횟수를 함께 출력한다.
 * 3. 초기 숫자 N은 2이상 133333341이하의 정수이다.
 * 4. 연산은 아래와 같이 진행한다.
 * 	4-1. 숫자가 홀수일 경우, (N * 3 + 1)을 먼저 진행한다. (연산 횟수 + 1)
 * 	4-2. 숫자가 짝수인 경우, 2로 나누어준다. (연산 횟수 + 1)
 * 	4-3. 숫자가 1이라면 종료한다.
 */
public class Solution {

	private static BufferedReader br;
	
	private static int result;
	private static int num;
	
	/**
	 * 메인 메서드.
	 */
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		br = new BufferedReader(new InputStreamReader(System.in));
		int testCaseNum = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= testCaseNum; testCase++) {
			init();
			sb.append("#").append(testCase).append(" ").append(num).append(" ");
			findResult();
			sb.append(result).append('\n');
		}
		System.out.println(sb);
	}
	
	/**
	 * 결과를 도출하는 메서드.
	 */
	private static void findResult() {
		while (true) {
			if (num == 1) {
				break;
			}
			if (num % 2 != 0) {
				// 1. 홀수인 경우
				num = num * 3 + 1;
				result++;
			} else {
				// 2. 짝수인 경우
				num /= 2;
				result++;
			}
		}
	}
	
	/**
	 * 초기 세팅 메서드.
	 */
	private static void init() throws IOException {
		result = 0;
		num = Integer.parseInt(br.readLine());
	}
}
