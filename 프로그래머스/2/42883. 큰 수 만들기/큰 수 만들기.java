import java.util.*;

class Solution {
    public String solution(String number, int k) {
        StringBuilder sb = new StringBuilder(number);
        int start = 0;
        
        for (int i = 0; i < k; i++) {
            for (int j = start; j < sb.length() - 1; j++) {
                if (sb.charAt(j) == '9') {
                    start++;
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
        
        // 동일한 숫자가 이어져, 마지막에 최소값들이 남았을 경우
        return sb.toString().substring(0, number.length() - k);
    }
}