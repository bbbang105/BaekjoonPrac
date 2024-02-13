import java.util.*;

class Solution {
    public int solution(int[] topping) {
        int answer = 0;
        Set<Integer> left = new HashSet<>();
        Set<Integer> right = new HashSet<>();
        Map<Integer, Integer> map = new HashMap<>();
        
        left.add(topping[0]);
        for (int i = 1; i < topping.length; i++) {
            int tmp = topping[i];
            right.add(tmp);
            map.put(tmp, map.getOrDefault(tmp, 0) + 1);
        }
        
        int index = 1;
        while (index < topping.length) {
            int num = topping[index];
            left.add(num);
            map.put(num, map.get(num) - 1);
            if (map.get(num) == 0) right.remove(num);
            if (left.size() == right.size()) answer++;
            
            index++;
        }
        
        return answer;
    }
}