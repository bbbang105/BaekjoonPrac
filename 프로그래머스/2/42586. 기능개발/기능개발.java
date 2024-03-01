import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        Stack<Task> stk = new Stack<>();
        List<Integer> answer = new ArrayList<>();
        
        for (int i = progresses.length - 1; i >= 0; i--) {
            stk.push(new Task(progresses[i], speeds[i]));
        }
        
        int day = 0;
        while (!stk.isEmpty()) {
            Task task = stk.pop();
            int progress = task.progress; int speed = task.speed;
            
            int remain = (100 - (progress + day * speed)) / speed;
            if ((100 - (progress + day * speed)) % speed != 0) remain++;
            day += remain;
                
            int cnt = 1;
            while (!stk.isEmpty()) {
                if (stk.peek().progress + (stk.peek().speed * day) < 100) break;
                stk.pop();
                cnt++;
            }
            
            answer.add(cnt);
        }
        
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
    
    class Task {
        int progress;
        int speed;
        
        Task(int progress, int speed) {
            this.progress = progress;
            this.speed = speed;
        }
    }
}