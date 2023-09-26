import java.util.ArrayList;

class Solution {
    public static int solution(String dartResult) {
        int answer = 0;
        int chance = -1; // 몇 번째 기회인지
        boolean ten = false; // true == 전 숫자가 10임을 나타냄
        ArrayList<Integer> score_list = new ArrayList<>();
        for (int i = 0; i < dartResult.length(); i++) {
            char d = dartResult.charAt(i); // 각 문자
            // 숫자가 10인 경우, 인덱스 하나 넘어감
            if (ten) {
                ten = false;
                continue;
            }
            // 숫자인 경우
            if (Character.isDigit(d)) {
                chance += 1;
                String num = "";
                char next_d = dartResult.charAt(i+1); // 숫자가 10인 경우를 고려
                // 10인 경우
                if (Character.isDigit(next_d)) {
                    ten = true;
                    num = "" + d + next_d;
                }
                // 1~9인 경우
                else {
                    num = "" + d;
                }
                // 숫자를 추가해 줌
                score_list.add(Integer.parseInt(num));
            }
            // 알파벳 (보너스)인 경우
            else if (Character.isAlphabetic(d)) {
                // 더블인 경우 (싱글은 고려x)
                if (d == 'D') {
                    score_list.set(chance, (int) Math.pow(score_list.get(chance),2));
                }
                // 트리플인 경우
                else if (d == 'T') {
                    score_list.set(chance, (int) Math.pow(score_list.get(chance),3));
                }
            }
            // 특수문자 (옵션)인 경우
            else {
                // 스타상인 경우
                if (d == '*') {
                    // 두 번째 기회부터 적용
                    if (chance > 0) {
                        score_list.set(chance-1, score_list.get(chance-1)*2);
                    }
                    score_list.set(chance, score_list.get(chance)*2);
                }
                // 아차상인 경우
                else {
                    score_list.set(chance, score_list.get(chance)*(-1));
                }
            }
        }
        // 점수 합산
        for (int s : score_list) {
            answer += s;
        }
        return answer;
    }
    public static void main(String args[]) {
        System.out.println(solution("10D4S10D"));
    }
}