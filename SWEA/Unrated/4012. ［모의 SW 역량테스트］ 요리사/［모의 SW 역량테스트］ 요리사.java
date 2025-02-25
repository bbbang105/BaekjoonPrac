import java.util.*;
import java.io.*;

/**
 * [조건]
 * 1. 식재료는 N개로, 짝수이다.
 * 2. 두 명의 사람이 각각 식재료를 N/2 개씩 나누어 요리를 받게 된다.
 * 3. 식재료 시너지의 정보는 N*N이다.
 * 4. A 음식과 B 음식 간의 최소 차이를 출력하라.
 * 
 * [풀이]
 * 1. 초기 값들을 입력 받는다.
 * 2. 나올 수 있는 모든 음식 조합을 구해 계산 메서드를 호출한다. 
 * 3. 이는  N개에서 N/2개를 뽑는 경우이다. (ex. 6개에서 3개를 뽑는 경우)
 * 4. 우선적으로 뽑힌 음식에 대해서 계산을 진행하고, 뽑히지 않은 음식들에 대해서도 계산을 진행해준다.
 * 5. 둘의 차이 (절대값)을 구하고, 최소가 되도록 갱신한다.
 * 6. 최종 결과를 출력한다.
 *
 */

public class Solution {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static int minDiff;
    private static int size;
    private static int[][] synergy;
    private static boolean[] isSelected;
    private static int ingredientCountLimit;

    public static void main(String[] args) throws IOException {
        int testCaseNum = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int testCase = 1; testCase <= testCaseNum; testCase++) {
            init();
            minDiff = Integer.MAX_VALUE;
            createIngredientSetsAndFindResult(0, 0);
            sb.append("#").append(testCase).append(" ").append(minDiff).append('\n');
        }
        System.out.print(sb);
    }

    /**
     * 입력 값을 받아 초기 세팅을 진행하는 메서드.
     */
    private static void init() throws IOException {
        size = Integer.parseInt(br.readLine());
        ingredientCountLimit = size / 2;
        synergy = new int[size][size];
        isSelected = new boolean[size];

        for (int rowIndex = 0; rowIndex < size; rowIndex++) {
            st = new StringTokenizer(br.readLine());
            for (int colIndex = 0; colIndex < size; colIndex++) {
                synergy[rowIndex][colIndex] = Integer.parseInt(st.nextToken());
            }
        }
    }

    /**
     * 가능한 식재료 조합을 만들고, 계산하여 최종 값을 갱신하는 메서드.
     */
    private static void createIngredientSetsAndFindResult(int curIndex, int ingredientCount) {
        if (ingredientCount == ingredientCountLimit) {
            int firstCookScore = calculateScore(true);
            int secondCookScore = calculateScore(false);
            minDiff = Math.min(minDiff, Math.abs(firstCookScore - secondCookScore));
            return;
        }
        if (curIndex >= size) return;

        // 현재 재료를 선택하는 경우
        isSelected[curIndex] = true;
        createIngredientSetsAndFindResult(curIndex + 1, ingredientCount + 1);

        // 현재 재료를 선택하지 않는 경우
        isSelected[curIndex] = false;
        createIngredientSetsAndFindResult(curIndex + 1, ingredientCount);
    }

    /**
     * 선택된 재료 또는 선택되지 않은 재료의 시너지 스코어를 계산하는 메서드.
     * 
     */
    private static int calculateScore(boolean isFirstCook) {
        List<Integer> ingredients = new ArrayList<>();
        for (int ingredientIndex = 0; ingredientIndex < size; ingredientIndex++) {
            if (isSelected[ingredientIndex] == isFirstCook) {
                ingredients.add(ingredientIndex);
            }
        }

        int sum = 0;
        int len = ingredients.size();
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                int a = ingredients.get(i);
                int b = ingredients.get(j);
                sum += synergy[a][b] + synergy[b][a];
            }
        }
        return sum;
    }
}
