import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * [조건]
 * 1. 재료의 개수 N은 1이상 10이하이다.
 * 2. 모든 재료를 사용해도, 신맛 S와 쓴맛 B는 10억보다 적은 양의 정수이다.(= int형 가능)
 * 3. 신맛은 곱하고, 쓴맛은 더해서 구한다.
 * 4. 재료는 1개만 써도 되고, 모든 재료를 써도 된다.(= 부분 집합)
 * 
 * [풀이]
 * 1. 모든 부분 집합을 구하며, 신맛과 쓴맛을 구한다.
 * 2. 기저 조건에 걸리면, 둘의 차이를 절대값으로 구하며 기존보다 작은 숫자라면 갱신한다.
 * 3. 최종적인 값을 출력한다.
 *
 */
public class Main {
	
	private static int[][] ingredients;
	private static int ingredientCount;
	private static int minDiff;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ingredientCount = Integer.parseInt(br.readLine());
		ingredients = new int[ingredientCount][2];
		
		StringTokenizer st;
		for (int index = 0; index < ingredientCount; index++) {
			st = new StringTokenizer(br.readLine());
			ingredients[index][0] = Integer.parseInt(st.nextToken());
			ingredients[index][1] = Integer.parseInt(st.nextToken());
		}
		
		minDiff = 1_000_000_000; // 최대 차이가 10억보다 작기에 초기값을 10억으로 설정
		findMinDiff(0, 1, 0); // 곱해야 하므로 신맛의 초기값은 1부터 시작
		System.out.print(minDiff);
	}
	
	/**
	 * 부분 집합을 구하며, 최종적인 최소 차이를 도출하는 메서드.
	 * 
	 */
	private static void findMinDiff(int curIndex, int sumOfacidity, int sumOfacerbity) {
		if (sumOfacerbity != 0) {
			// 인덱스가 0이라면 차이가 0이므로 제외
			minDiff = Math.min(minDiff, Math.abs(sumOfacidity - sumOfacerbity));
		}
		
		if (curIndex == ingredientCount) {
			// 기저 조건
			return;
		}
		// 1) 본인을 선택한 경우
		findMinDiff(curIndex + 1, sumOfacidity * ingredients[curIndex][0], sumOfacerbity + ingredients[curIndex][1]);
		// 2) 본인을 선택하지 않는 경우
		findMinDiff(curIndex + 1, sumOfacidity, sumOfacerbity);
	}
}
