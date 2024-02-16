class Solution {
    public int solution(String s) {
        int len = s.length();
        int answer = 1;

        for (int i = 0; i < len; i++) {
            for (int j = len - 1; j >= 0; j--) {
                int curLength = j - i + 1;
                if (answer >= curLength) break;
                if (s.charAt(i) == s.charAt(j)) {
                    boolean flag = true;
                    int rightIndex = j;
                    for (int k = i; k < j + 1 ; k++) {
                        if (s.charAt(k) != s.charAt(rightIndex--)) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) answer = Math.max(answer, curLength);
                }
            }
        }

        return answer;
    }
}