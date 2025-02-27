import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [조건]
 * 1. 농장의 크기 N은 1이상 49이하의 홀수이다.
 * 2. 농작물의 가치는 0이상 5이하이다.
 * 3. 수확은 항상 농장의 크기에 딱 맞는 정사각형 마름모 형태로만 가능하다.
 * 
 * [풀이]
 * 1. 크기를 받은 후, 중간 인덱스를 구한다. (N / 2)
 * 2. 중간 인덱스로부터, 좌우로 뻗어나갈 수 있는 정도를 +1 한다.
 * 3. 만약 행이 중간 인덱스까지 도달(=좌우로 뻗어나가는 정도가 끝까지 일 때)하면 상태를 변경한다.
 * 4. 변경된 상태부터는, 좌우로 뻗어나갈 수 있는 정도를 -1 한다.
 * 5. 마지막 행까지 탐색하며 합산하고, 결과를 출력한다.
 */
public class Solution {
	
	private static BufferedReader br;
	private static int N;
	private static int midIndex;
	private static int result;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int testCaseNum = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= testCaseNum; testCase++) {
			result = 0;
			N = Integer.parseInt(br.readLine()); // 농장 사이즈
			midIndex = N / 2; // 중간 인덱스
			
			boolean isIncrease = true; // 좌우로 뻗어나가는 정도가 늘어나는지 or 줄어드는지의 여부
			int increaseAmount = 0;    // 늘어나는 정도
			for (int index = 0; index < N; index++) {
				String row = br.readLine();
				result += row.charAt(midIndex) - '0'; // 중간 인덱스는 무조건 포함
				
				for (int amount = 1; amount <= increaseAmount; amount++) {
					// 좌우를 더해줌
					result += row.charAt(midIndex - amount) - '0'; // 좌
					result += row.charAt(midIndex + amount) - '0'; // 우
				}
				
				
				if (index == midIndex) {
					isIncrease = false;
				}	
				increaseAmount = isIncrease ? increaseAmount + 1 : increaseAmount - 1;
			}
			sb.append("#").append(testCase).append(" ").append(result).append('\n');
		}
		System.out.print(sb);
	}
}
