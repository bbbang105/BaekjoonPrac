import java.util.*;

class Solution {
    public int[] solution(int n, int s) {
        if (n > s) return new int[]{-1};
        
        int[] answer = new int[n];
        
        for (int i = n; i > 0; i--) {
            int cur = (s / i);
            answer[n - i] = cur;
            s -= cur;
        }
        
        return answer;
    }
}
