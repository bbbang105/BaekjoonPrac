import java.util.ArrayList;

class Solution {
    public static int[] solution(int n, String[] words) {
        int[] answer = new int[2];
        String last = "";
        boolean fail = false;
        ArrayList<String> list = new ArrayList<>();

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            // 다른 단어 or 한 글자 단어
            if ((i > 0 && !word.substring(0,1).equals(last)) || word.length() == 1) {
                fail = true;
            } else if (list.contains(word)) { // 중복 단어
                fail = true;
            } else { // 갱신
                list.add(word);
                last = word.substring(word.length()-1,word.length());
            }
            if (fail) { // 탈락
                answer[0] = (i % n) + 1; // 번호
                answer[1] = (i / n) + 1; // 차례
                break;
            }
        }
        return answer;
    }
    // 테스트 코드
    public static void main(String args[]) {
        solution(2, new String[]{"hello", "one", "even", "never", "now", "world", "draw"});
    }
}