import java.util.*;

class Solution {
    Map<String, Integer> map;

    public int[] solution(String msg) {
        createMap();
        List<Integer> answer = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        int cursor = 0;
        int lastIndex = 0; // 현재 색인 번호

        while (cursor < msg.length()) {
            while (cursor < msg.length()) {
                sb.append(msg.charAt(cursor));

                if (!map.containsKey(sb.toString())) {
                    map.put(sb.toString(), map.size() + 1);
                    sb.setLength(0);
                    break;
                }

                lastIndex = map.get(sb.toString());
                cursor++;
            }

            answer.add(lastIndex);
        }

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

    private void createMap() {
        map = new HashMap<>();
        for (int i = 65; i < 91; i++) {
            map.put(String.valueOf(((char) i)), i - 64);
        }
    }
}