import java.util.*;

class Solution {
    private static List<String> words = new ArrayList<>();
    private static String[] vowels = {"A", "E", "I", "O", "U"};
    private static String goal;
    private static int answer = 0;
    private static boolean flag = false;
    
    public int solution(String w) {
        goal = w;
        backTracking("");
        
        return answer;
    }
    
    private void backTracking(String word) {
        if (word.equals(goal)) {
            flag = true;
            
            return;
        }
        
        if (!flag) {
            if (!words.contains(word)) {
                words.add(word);
                answer++;
            }
            
            for (String vowel : vowels) {
                String newWord = word + vowel;
                
                if (newWord.length() <= 5) {
                    backTracking(newWord);
                }
            }
        }
    }
}