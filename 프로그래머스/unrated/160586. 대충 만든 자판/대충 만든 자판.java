import java.util.HashMap;

class Solution {
    public static int[] solution(String[] keymap, String[] targets) {
        int[] answer = new int[targets.length];
        HashMap<String,Integer> map = new HashMap<>();
        // 맵에 최소 버튼 횟수를 넣어줌
        for (String k : keymap) {
            for (int i = 0; i < k.length(); i++) {
                String s = "" + k.charAt(i);
                if (!map.containsKey(s)) {
                    map.put(s,i+1);
                } else {
                    int n = map.get(s);
                    if (n > i+1) {
                        map.put(s,i+1);
                    }
                }
            }
        }
        // 횟수 세어주기
        for (int i = 0; i < targets.length; i++) {
            String t = targets[i];
            boolean impossible = false;
            int cnt = 0;
            for (int j = 0; j < t.length(); j++) {
                String s = "" + t.charAt(j);
                if (map.containsKey(s)) {
                    cnt += map.get(s);
                } else {
                    answer[i] = -1;
                    impossible = true;
                    break;
                }
            }
            if (!impossible) answer[i] = cnt;
        }
        return answer;
    }
}