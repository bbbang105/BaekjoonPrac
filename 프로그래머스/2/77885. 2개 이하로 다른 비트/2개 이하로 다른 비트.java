import java.util.*;

class Solution {
    public long[] solution(long[] numbers) {
        int len = numbers.length;
        long[] answer = new long[len];
        
        for (int i = 0; i < len; i++) {
            answer[i] = find(numbers[i]);
        }
        
        return answer;
    }
    
    private long find(long n) {
        String num = Long.toString(n, 2);
        if (!num.contains("0")) num = "0" + num;
        StringBuilder sb = new StringBuilder(num);
        
        for (int i = sb.length() - 1; i >= 0; i--) {
            // 처음으로 0을 발견한 순간
            if (sb.charAt(i) == '0') {
                // 맨 끝이 0인 경우
                if (i == sb.length() - 1) {
                    sb.setCharAt(i, '1');
                } else {
                    sb.setCharAt(i, '1');
                    sb.setCharAt(i + 1, '0');
                }
                break;
            }
        }
        
        return Long.parseLong(sb.toString(), 2);
    }
}