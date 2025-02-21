import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [조건]
 * 1. 테스트케이스는 10개로 고정이다.
 * 2. 숫자는 8개로 고정이다.
 * 3. 첫번째 인덱스부터 - 1 2 3 4 5 씩 하며, 5까지 빼고 난 후 다음 사이클로 넘어간다.
 * 4. 감소한 숫자는 맨 뒤로 이동한다.
 * 5. 감소할 때 숫자가 0이하가 되면, 종료하고 최종적인 암호를 출력한다.
 * 
 * [풀이]
 * 1. 실제로 원소를 이동하지는 않고, 고정된 배열에서 진행한다.
 * 2. 초기 인덱스 0부터 반복하며 차감해주고, 만약 기저 조건을 만나면 해당 인덱스 오른쪽부터 암호를 구성한다.
 *
 */
public class Solution {
	
	private static final int PWD_LEN_LIMIT = 8;
	
	private static int[] nums;
	private static BufferedReader br;
	private static StringTokenizer st;
	private static StringBuilder result;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		result = new StringBuilder();
		
		for (int testCase = 1; testCase <= 10; testCase++) {
			init();
			result.append("#").append(testCase).append(" ");
			findAndSaveFinalPwd();
		}
		System.out.print(result);
	}
	
	/**
	 * 입력 값을 받아 초기 세팅을 진행하는 메서드.
	 * 
	 */
	private static void init() throws IOException {
		int testCase = Integer.parseInt(br.readLine());
		nums = new int[PWD_LEN_LIMIT];
		
		st = new StringTokenizer(br.readLine());
		for (int index = 0; index < PWD_LEN_LIMIT; index++) {
			nums[index] = Integer.parseInt(st.nextToken());
		}
	}
	
	/**
	 * 최종적인 암호를 찾고 스트링빌더에 저장하는 메서드.
	 * 
	 */
	private static void findAndSaveFinalPwd() {
		int curIndex = 0;
		int lastIndex;
		int curMinus = 1;
		int curNum;
		
		while (true) {
			curNum = nums[curIndex];
			
			if (curNum - curMinus <= 0) {
				// 기저 조건
				nums[curIndex] = 0;
				lastIndex = curIndex;
				break;
			}
			// 값 갱신
			nums[curIndex] -= curMinus;
			curIndex = (curIndex + 1) % 8;
			curMinus = curMinus % 5 + 1;
		}
		
		StringBuilder sb = new StringBuilder();
		for (int index = 1; index <= PWD_LEN_LIMIT; index++) {
			sb.append(nums[(lastIndex + index) % PWD_LEN_LIMIT]).append(" ");
		}
		result.append(sb).append('\n');
	}
}
