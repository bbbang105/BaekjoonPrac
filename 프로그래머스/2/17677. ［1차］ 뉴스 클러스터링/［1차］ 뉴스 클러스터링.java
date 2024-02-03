import java.util.*;

class Solution {
    private Map<String, Integer> map1;
    private Map<String, Integer> map2;
    private int totalSize;

    public int solution(String str1, String str2) {
        map1 = createMap(str1);
        map2 = createMap(str2);

        return calculateAnswer();
    }

    private Map<String, Integer> createMap(String str) {
        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < str.length() - 1; i++) {
            char cur = str.charAt(i);
            char next = str.charAt(i + 1);

            if (Character.isAlphabetic(cur) && Character.isAlphabetic(next)) {
                String twoWords = ("" + cur + next);
                String smallWords = twoWords.toLowerCase();
                map.put(smallWords, map.getOrDefault(smallWords, 0) + 1);
                totalSize++;
            }
        }

        return map;
    }

    private int calculateAnswer() {
        List<String> keys1 = new ArrayList<>(map1.keySet());
        List<String> keys2 = new ArrayList<>(map2.keySet());
        int cnt = 0;
        float answer;

        if (keys1.isEmpty() && keys2.isEmpty()) {
            answer = 1;
        } else {
            for (String s : keys1) {
                if (keys2.contains(s)) {
                    cnt += Math.min(map1.get(s), map2.get(s));
                }
            }

            answer = ((float) cnt / (totalSize - cnt));
        }

        return (int) (answer * 65536);
    }
}