import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * [조건]
 * 1. N은 1이상 10,000이하이다.
 * 2. 사전 순으로 앞서는 순열은 오름차순으로 이루어진 순열이다.
 * 3. 입력으로 주어진 순열 다음에 오는 순열을 출력하라.
 * 4. 만약, 사전순으로 마지막에 오는 순열인 경우에는 -1을 출력하라.
 *
 * [풀이]
 * 1. Next Permuatation 공식을 활용한다.
 */
public class Main {
	private static int N;
	private static int[] nums;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		nums = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int index = 0; index < N; index++) {
			nums[index] = Integer.parseInt(st.nextToken());
		}
		
		StringBuilder sb = new StringBuilder();
		if (!nextPermutation()) {
			// 마지막에 오는 순열인 경우
			sb.append("-1");
		} else {
			for (int num : nums) {
				sb.append(num).append(" ");
			}
		}
		System.out.print(sb);
	}
	
	private static boolean nextPermutation() {
		// 1. 뒤에서부터 내려오며 가장 높은 (꼭대기) 수를 찾는다.
		int index = N - 1;
		while (index > 0 && nums[index - 1] >= nums[index]) {
			--index;
		}
		
		if (index == 0) {
			// * 마지막에 오는 순열인 경우 false를 반환한다.
			return false;
		}
		
		// 2. 꼭대기 바로 앞 수 보다 하나 큰 수를 찾는다 (j)
		int j = N - 1;
		while (nums[index - 1] >= nums[j]) {
			--j;
		}
		
		// 3. index -1과 j를 스왑한다.
		swap(index - 1, j);
		
		// 4. index 꼭대기 위치부터 맨 뒤까지 오름차순 정렬한다. (가장 작은 수로)
		int k = N - 1;
		while(index < k) {
			swap(index++, k--);
		}
		
		return true;
	}

	/**
	 * 스왑하는 메서드.
	 * 
	 */
	private static void swap(int index, int j) {
		int temp = nums[index];
		nums[index] = nums[j];
		nums[j] = temp;
	}
}
