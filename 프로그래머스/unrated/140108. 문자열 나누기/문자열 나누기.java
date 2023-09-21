class Solution {
    public int solution(String s) {
        int answer = 0;
        boolean re = true;
        char x = s.charAt(0);
        int x_cnt = 0; int other_cnt = 0;
        for (int i = 0; i < s.length(); i++) {
            // x 재설정
            if (re) {
                x = s.charAt(i);
                x_cnt += 1;
                re = false;
                continue;
            }
            // 문자열 비교
            if (s.charAt(i) != x) {
                other_cnt += 1;
            } else {
                x_cnt += 1;
            }
            // 분리하는 경우
            if (x_cnt == other_cnt) {
                re = true;
                x_cnt = 0; other_cnt = 0;
                answer += 1;
            }
        }
        // 마지막으로 분리된 문자열 추가
        if (!re) {
            answer += 1;
        }
        return answer;
    }
}