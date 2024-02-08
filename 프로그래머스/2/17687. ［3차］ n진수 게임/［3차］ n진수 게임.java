class Solution {
    public String solution(int n, int t, int m, int p) {
        StringBuilder answer = new StringBuilder();
        int number = 0; // 초기 숫자
        int index = 1;  // 현재 차례
        boolean stop = false;
        
        while (!stop) {
            // n진수 변환
            String convertedNumber = Integer.toString(number, n).toUpperCase();
            for (int i = 0; i < convertedNumber.length(); i++) {
                if (index == p) {
                    answer.append(convertedNumber.charAt(i)); // 말할 숫자 추가
                    p += m; // 다음 차례
                }
                if (answer.length() == t) {
                    // 다 구한 경우
                    stop = true;
                    break;
                }
                index++;
            }
            number++;
        }
        
        return answer.toString();
    }
}