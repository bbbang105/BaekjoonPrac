import java.util.*;

public class Solution {
    public int solution(int k, int[] tangerine) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int t : tangerine) {
            map.put(t, map.getOrDefault(t,0) + 1);
        }

        List<Integer> numbers = new ArrayList<>(map.values());
        Collections.sort(numbers);
        Collections.reverse(numbers);
        
        int answer = 0;
        for (int n : numbers) {
            answer++;
            k -= n;
            if (k <= 0) break;
        }
        
        return answer;
    }
}