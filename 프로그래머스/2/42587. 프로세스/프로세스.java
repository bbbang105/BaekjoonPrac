import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        Queue<Integer> q = new LinkedList<>();
        for (int priority : priorities) q.offer(priority);
        
        int max = Collections.max(q);
        int order = 1;
        while (!q.isEmpty()) {
            int cur = q.poll();
            
            if (cur == max) {
                if (location == 0) break;
                max = Collections.max(q);
                order++;
            } else {
                q.offer(cur);
            }
            
            location--;
            
            if (location < 0) location = q.size() - 1;
        }
        
        return order;
    }
}