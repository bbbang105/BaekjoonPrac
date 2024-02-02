import java.util.*;

class Solution {
    public int[] solution(String s) {
        Map<Integer, Integer> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                // 숫자인 경우
                sb.append(c);
            } else if (!sb.toString().isEmpty()) {
                // 숫자가 끝난 경우
                int n = Integer.parseInt(sb.toString());
                map.put(n, map.getOrDefault(n, 0) + 1);
                // StringBuilder 초기화
                sb.setLength(0);
            }
        }

        List<Integer> keys = new ArrayList<>(map.keySet());
        keys.sort((o1, o2) -> map.get(o2).compareTo(map.get(o1)));

        return keys.stream().mapToInt(Integer::intValue).toArray();
    }
}