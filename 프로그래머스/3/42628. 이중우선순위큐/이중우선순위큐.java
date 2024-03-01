import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        int[] answer = new int[2];
        Queue<Integer> min = new PriorityQueue<>();
        Queue<Integer> max = new PriorityQueue<>(Collections.reverseOrder());
        
        for (String operation : operations) {
            String[] temp = operation.split(" ");
            String cmd = temp[0];
            String num = temp[1];
            
            if (cmd.equals("I")) {
                min.offer(Integer.parseInt(num));
                max.offer(Integer.parseInt(num));
            } else if (!min.isEmpty() && num.equals("1")) {
                int n = max.poll();
                min.remove(n);
            } else if (!min.isEmpty() && num.equals("-1")){
                int n = min.poll();
                max.remove(n);  
            }
        }
        
        if (!max.isEmpty()) answer[0] = max.poll(); 
        if (!max.isEmpty()) answer[1] = min.poll();
        
        return answer;
    }
}