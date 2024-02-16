class Solution {
    public int solution(String s) {
        int len = s.length();
        int answer = 1;

        for (int i = 0; i < len; i++) {
            for (int j = len - 1; j >= i + answer - 1; j--) { // 현재 팰린드롬 길이보다 작은 경우는 볼 필요 x
                int curLength = j - i + 1;
                if (s.charAt(i) == s.charAt(j)) {
                    boolean flag = true;
                    int leftIndex = i + 1; 
                    int rightIndex = j - 1; 
                    while (leftIndex < rightIndex) { 
                        if (s.charAt(leftIndex++) != s.charAt(rightIndex--)) {
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