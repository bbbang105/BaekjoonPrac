import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> minQ = new PriorityQueue<>();
        for (int s : scoville) minQ.offer(s);
        
        while(minQ.peek() < K) {
            if (minQ.size() == 1) {
                // 불가능한 경우
                answer = -1;
                break;
            }
            minQ.offer(minQ.poll() + minQ.poll() * 2);
            answer++;
        }
        
        return answer;
    }
}