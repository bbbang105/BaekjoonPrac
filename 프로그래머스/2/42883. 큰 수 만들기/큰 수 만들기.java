class Solution {
    public String solution(String number, int k) {
        StringBuilder sb = new StringBuilder(number);
        int startIndex = 0;

        for (int i = 0; i < k; i++) {
            for (int j = startIndex; j < sb.length() - 1; j++) {
                if (sb.charAt(startIndex) == '9') {
                    startIndex++;
                    continue;
                }

                int n1 = sb.charAt(j) - '0';
                int n2 = sb.charAt(j + 1) - '0';

                if (n1 < n2) {
                    sb.deleteCharAt(j);
                    break;
                }
            }
        }
        
        String answer = sb.toString();
        
        if (answer.length() > number.length() - k) {
            answer = answer.substring(0, number.length() - k);
        }
        
        return answer;
    }
}