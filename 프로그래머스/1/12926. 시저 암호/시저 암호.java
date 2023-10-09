class Solution {
    public static String solution(String s, int n) {
        String answer = "";

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            boolean small = false;
            if (c == ' ') {
                answer += " ";
              // ASCII: 65 ~ 90 (A ~ Z), 97 ~ 122 (a ~ z)
            } else {
                int num = (int) c;
                // 소문자인 경우
                if (num >= 97) {
                    num += n;
                    if (num > 122) {
                        num -= 26;
                    }
                    answer += (char) num;
                }
                // 대문자인 경우
                else {
                    num += n;
                    if (num > 90) {
                        num -= 26;
                    }
                    answer += (char) num;
                }

            }
        }
        return answer;
    }
}