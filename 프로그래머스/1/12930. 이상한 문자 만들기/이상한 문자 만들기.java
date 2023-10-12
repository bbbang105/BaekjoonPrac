class Solution {
    public String solution(String s) {
        StringBuilder answer = new StringBuilder();
        int index = 0;
        // ASCII: 대문자 65 ~ 90, 소문자 97 ~ 122
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // 문자인 경우
            if (c != ' ') {
                int n = (int)c; // ASCII Num
                // 짝수인 경우
                if (index % 2 == 0) {
                    if (n >= 97) {
                        n -= 32;
                    }
                }
                // 홀수인 경우
                else {
                    if ((int)c <= 90) {
                        n += 32;
                    }
                }
                answer.append((char)n);
                index += 1; // 공백을 만나기 전까지는 계속 +1
            }
            // 공백인 경우
            else {
                answer.append(" ");
                index = 0; // 공백을 만났으므로 초기화
            }
        }
        return answer.toString();
    }
}