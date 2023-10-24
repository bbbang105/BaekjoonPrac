import java.util.HashMap;
import java.util.Iterator;

class Solution {
    public static int solution(String[][] clothes) {
        HashMap<String, Integer> map = new HashMap<>();
        // 해시맵에 카테고리별 개수 저장
        for (String[] S : clothes) {
            map.put(S[1], map.getOrDefault(S[1], 0) + 1);
        }
        // Iterator 사용
        Iterator<Integer> it = map.values().iterator();
        int answer = 1;
        // 카테고리 별 가지수 + 1 (입지 않았을 경우)를 곱해줌
        while (it.hasNext()) {
            answer *= (it.next() + 1);
        }
        // 아무것도 입지 않은 경우가 포함되어 있으므로 -1
        return answer - 1;
    }
}