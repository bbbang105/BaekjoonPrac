import java.io.*;
import java.util.*;

/**
 * [조건]
 * 1. 재료의 수 N은 1이상 20이하이다.
 * 2. 제한 칼로리 L은 1이상 10^4 이하이다.
 * 3. 맛에 대한 점수와 칼로리는 1이상 10^3이하이다.
 * 4. 같은 재료를 여러 번 사용할 수 없다.(중복 x)
 * 5. 햄버거의 조합의 제한은 칼로리를 제외하고는 없다. 즉, 제한 칼로리 내에서는 어떠한 재료들의 조합도 괜찮다.(부분 집합)
 * 6. 제한 칼로리 내에서 가장 높은 선호도를 가질 수 있는 조합을 만들고, 해당 조합의 맛 점수의 합을 출력하라.
 *
 * [풀이]
 * 1. 순서를 고려하지 않고 중복을 허용하지 않으므로 부분 집합으로 구할 수 있다.
 * 2. (선택 재료의 개수는 1개부터 N개까지 모두 설정하여 탐색한다.) -> *수정* 제한 칼로리라는 기저 조건이 있으므로, 따로 개수 제한을 할 필요 X
 * 3. 재귀 호출을 하며, 선택한 재료의 점수와 칼로리를 더한다.
 * 4. 현재까지의 칼로리 합이 제한 칼로리 내에 있다면, 최대 재료 점수를 비교하여 갱신한다.
 * 5. 모든 경우에 대하여 탐색한 후, 최종적으로 할당된 최대 재료 점수를 스트링빌더에 삽입한다.
 */
public class Solution {
	
	private static BufferedReader br;
	private static StringTokenizer st;
	private static int[][] ingredients;
	private static int ingredientCounts;
	private static int caloryLimit;
	private static int maxSumOfScore;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		int testCaseNum = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int testCase = 1; testCase <= testCaseNum; testCase++) {
			init();
			findCombinationsAndMaxScore(0, 0, 0);
			sb.append("#").append(testCase).append(" ").append(maxSumOfScore).append('\n');
		}
		System.out.println(sb);
	}
	
	/**
	 * 값들을 입력 받고 배열을 구성하는 메서드.
	 * 
	 */
	private static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		ingredientCounts = Integer.parseInt(st.nextToken());
		ingredients = new int[ingredientCounts][2]; // 재료들의 점수, 칼로리
		caloryLimit = Integer.parseInt(st.nextToken());
		maxSumOfScore = 0;
		
		for (int index = 0; index < ingredientCounts; index++) {
			st = new StringTokenizer(br.readLine());
			ingredients[index][0] = Integer.parseInt(st.nextToken());
			ingredients[index][1] = Integer.parseInt(st.nextToken());
		}
	}
	
	/**
	 * 부분 집합들을 만들고, 최대 재료 점수를 구하는 메서드.
	 * 
	 */
	private static void findCombinationsAndMaxScore(int startIndex, int sumOfScore, int sumOfCalory) {
		// 선택 개수 제한 x, 오로지 아래 조건에 따라 종료됨
		if (sumOfCalory > caloryLimit) {
			// 제한 칼로리보다 합산 칼로리가 높은 경우 (기저 조건)
			return;
		}
		// 제한 칼로리 내에 있다면 비교 후 갱신
		maxSumOfScore = Math.max(maxSumOfScore, sumOfScore);
		
		for (int index = startIndex; index < ingredientCounts; index++) {
			int curIngredientScore = ingredients[index][0];
			int curIngredientCalory = ingredients[index][1];
			findCombinationsAndMaxScore(index + 1, sumOfScore + curIngredientScore, sumOfCalory + curIngredientCalory);
		}
	}
}
