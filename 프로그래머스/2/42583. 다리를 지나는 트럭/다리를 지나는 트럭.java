import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int curWeight = 0;
        int index = 0;
        int time = 0;
        Queue<Integer> q = new LinkedList<>();
        q.offer(0);
        
        while (true) {
            if (index < truck_weights.length) {
                // 다리에서 트럭이 내리는 경우
                if (q.size() == bridge_length) {
                    curWeight -= q.poll();
                }
                if (weight >= curWeight + truck_weights[index]) {
                    // 1. 새로운 트럭을 올릴 수 있는 경우
                    curWeight += truck_weights[index];
                    q.offer(truck_weights[index]);
                    index++;
                } else {
                    // 2. 무게가 부족해 트럭을 올릴 수 없는 경우
                    q.offer(0);
                }
            } else {
                // 마지막 트럭인 경우
                time += bridge_length;
                break;
            }
            time++;
        }
        
        return time;
    }
}