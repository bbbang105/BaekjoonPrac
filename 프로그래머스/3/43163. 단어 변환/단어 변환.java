import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    private String target;
    private int answer;
    private List<String> words;
    private Set<String> visited;
    public int solution(String begin, String t, String[] w) {
        answer = 10000000;
        target = t;
        words = new ArrayList<>(List.of(w));
        visited = new HashSet<>();
        if (!words.contains(target)) return 0;
        backTracking(begin, 0);

        return answer;
    }

    private void backTracking(String word, int cnt) {
        if (word.equals(target)) {
            answer = Math.min(answer, cnt);
            return;
        }
        for (int i = 0; i < word.length(); i++) {
            StringBuilder sb = new StringBuilder(word);
            char c = word.charAt(i);
            for (int k = 1; k < 27; k++) {
                int ascii = c + k;
                if (ascii > 122) ascii -= 26;
                sb.setCharAt(i, (char) ascii);
                String newWord = sb.toString();
                if (!visited.contains(newWord) && words.contains(newWord)) {
                    visited.add(newWord);
                    backTracking(newWord, cnt + 1);
                    visited.remove(newWord);
                }
            }
        }
    }
}