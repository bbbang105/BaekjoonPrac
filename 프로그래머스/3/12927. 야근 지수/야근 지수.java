import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        PriorityQueue<Integer> maxQ = new PriorityQueue<>(Collections.reverseOrder());
        
        for (int w : works) maxQ.offer(w);
        for (int i = 0; i < n; i++) {
            int maxNum = maxQ.poll();
            if (maxNum == 0) return 0;
            maxQ.offer(maxNum - 1);
        }
        
        while(!maxQ.isEmpty()) {
            answer += Math.pow(maxQ.poll(), 2);
        }
        
        return answer;
    }
}