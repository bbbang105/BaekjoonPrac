import java.util.ArrayList;
import java.util.List;

class Solution {
    final static String[] vowels = {"A", "E", "I", "O", "U"};
    static List<String> words = new ArrayList<>();
    static int answer = 0;
    static boolean flag = false;

    public static int solution(String word) {
        findWord(word, "");
        return answer;
    }

    private static void findWord(String target, String curWord) {
        if (curWord.equals(target)) {
            flag = true;

            return;
        }

        if (!flag) {
            if (!words.contains(curWord)) {
                words.add(curWord);
                answer++;
            }

            for (String vowel : vowels) {
                String newWord = curWord + vowel;

                if (newWord.length() <= 5) {
                    findWord(target, newWord);
                }
            }
        }
    }
}