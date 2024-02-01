import java.util.HashMap;
import java.util.Map;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        Map<String, Integer> map = new HashMap<>();
        int answer = 0;

        for (int i = 0; i < want.length; i++) {
            map.put(want[i], number[i]);
        }

        for (int j = 0; j <= discount.length - 10; j++) {
            Map<String, Integer> copyMap = new HashMap<>(map);
            for (int k = 0; k < 10; k++) {
                int index = j + k;
                String thing = discount[index];
                copyMap.put(thing, copyMap.getOrDefault(thing, 0) - 1);
            }

            boolean flag = true;
            for (int n : copyMap.values()) {
                if (n != 0) {
                    flag = false;
                    break;
                }
            }

            if (flag) answer++;
        }

        return answer;
    }
}