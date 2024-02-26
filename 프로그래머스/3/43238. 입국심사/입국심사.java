import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        long result = 0;
        int len = times.length;
        
        Arrays.sort(times);
        
        long minTime = 1;
        long maxTime = (long) times[len - 1] * n; // 최대로 걸리는 시간
        
        while (minTime <= maxTime) {
            long avgTime = (long) (minTime + maxTime) / 2;
            long possibleCnt = 0;
            
            for (int t : times) {
                possibleCnt += (avgTime / t); 
                
                if (possibleCnt >= n) break;
            }
            
            if (possibleCnt < n) {
                // 심사가 불가한 경우
                minTime = avgTime + 1;
            } else {
                // 심사가 가능한 경우, 최적 시간 갱신
                result = avgTime;
                maxTime = avgTime - 1;
            }
        }
        
        return result;
    }
}