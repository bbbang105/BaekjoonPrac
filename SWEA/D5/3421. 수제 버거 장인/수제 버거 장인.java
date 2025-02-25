import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * [조건]
 * 1. 재료의 개수 N은 1이상 20이하이다.
 * 2. 궁합의 정보  M은 0이상 400이하이다.
 * 3. 재료는 1번부터 N번까지 존재한다.
 * 4. a b가 주어지면, a와 b는 동시에 같은 버거에 들어가면 안 된다는 뜻이다.
 * 5. 중복 쌍이 존재할 수 있다.
 * 6. 제약조건을 만족시키며 만들 수 있는 버거의 가짓수를 출력하라.
 * 
 * [풀이]
 * 1. 정보를 입력 받으며, 간선 그래프를 구성한다.
 * 2. 조합이므로 순서가 상관 x -> 재료 숫자가 1부터 커지는 방향으로 나열
 * 3. 만약 3 1 이 들어왔더라도, 1 -> 3으로 그래프를 추가한다. 
 * 4. 모든 부분 집합을 구해주는데, Set에 있는 값들은 제외하고 구한다.
 * 5. 재귀 호출이 된 것은 새로운 부분 집합이 생긴 것이므로, 호출 시마다 result++ 한다.
 *
 */
public class Solution {

    private static BufferedReader br;
    private static StringTokenizer st;
    private static StringBuilder sb;
    private static Map<Integer, Set<Integer>> map;
    private static int countOfIngredients;
    private static int countOfInfos;
    private static int result;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        int testCaseNum = Integer.parseInt(br.readLine().trim());
        
        sb = new StringBuilder();
        for (int testCase = 1; testCase <= testCaseNum; testCase++) {
            init();  // 입력 및 초기화
            result = 0;
            findMaxKindOfBurger(1, new HashSet<Integer>());
            sb.append("#").append(testCase).append(" ").append(result).append('\n');
        }
        System.out.print(sb);
    }
    
    /**
     * 입력을 받고, 재료 간 궁합 정보를 Map에 저장한다.
     * a와 b가 궁합이 맞지 않으면, 항상 a < b가 되도록 저장 (중복 제거를 위해 Set 사용)
     */
    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        countOfIngredients = Integer.parseInt(st.nextToken());
        countOfInfos = Integer.parseInt(st.nextToken());
        map = new HashMap<>();
        for (int index = 1; index <= countOfIngredients; index++) {
            map.put(index, new HashSet<>());
        }
        for (int index = 0; index < countOfInfos; index++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            // a와 b의 순서를 항상 a < b가 되도록 수정
            if (a > b) {
                int temp = a;
                a = b;
                b = temp;
            }
            map.get(a).add(b);
        }
    }
    
    /**
     * index: 현재 고려할 재료 번호
     * currentSet: 지금까지 선택한 재료들의 집합
     * 
     * 각 재료에 대해 선택(포함) 또는 배제(미포함)의 두 경우를 모두 고려하여,
     * 지금까지의 재료들과 궁합이 맞는 경우에만 추가한다.
     * 기저 조건(index > countOfIngredients)이 되면, 하나의 유효한 버거 구성이 완성된 것으로 result를 증가시킨다.
     */
    private static void findMaxKindOfBurger(int index, Set<Integer> currentSet) {
        if (index > countOfIngredients) {
            result++;
            return;
        }
        
        // 재료 index를 선택하지 않는 경우
        findMaxKindOfBurger(index + 1, currentSet);
        
        // 재료 index를 선택하는 경우 (현재까지 선택한 모든 재료와 호환되는지 확인)
        boolean canInclude = true;
        for (int ingr : currentSet) {
            int small = Math.min(ingr, index);
            int large = Math.max(ingr, index);
            if (map.get(small).contains(large)) {
                canInclude = false;
                break;
            }
        }
        if (canInclude) {
            currentSet.add(index);
            findMaxKindOfBurger(index + 1, currentSet);
            currentSet.remove(index);
        }
    }
}
