import java.util.*;

class Solution {
    public List solution(int[] num_lst) {
        List<Integer> answer = new ArrayList<>();
        
        for (int i = 0; i < num_lst.length; i++) {
            answer.add(num_lst[i]);
        }
        if (num_lst[num_lst.length-1] > num_lst[num_lst.length-2]) {
            answer.add(num_lst[num_lst.length-1] - num_lst[num_lst.length-2]);
        } else {
            answer.add(num_lst[num_lst.length-1]*2);
        }
        
        return answer;
    }
}