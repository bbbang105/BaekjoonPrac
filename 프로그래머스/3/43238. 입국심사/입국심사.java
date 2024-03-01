import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        Arrays.sort(times);
        
        long left = 1;
        long right = (long) times[times.length - 1] * n; // 최대로 소요되는 시간
        
        while (left <= right) {
            long mid = (long) (left + right) / 2;
            long cnt = 0;
            
            for (int t : times) {
                cnt += mid / t;
                
                if (cnt >= n) break;
            }
            
            if (cnt >= n) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        return answer;
    }
}