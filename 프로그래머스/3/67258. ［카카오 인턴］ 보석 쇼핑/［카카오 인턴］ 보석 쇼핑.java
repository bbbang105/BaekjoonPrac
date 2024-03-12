import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        int[] answer = new int[2];
        Set<String> set = new HashSet<>(Arrays.asList(gems)); 
        int limit = set.size(); // 모든 종류 개수
        
        Set<String> cur = new HashSet<>(); // 보석 종류
        Map<String, Integer> map = new HashMap<>(); // 종류별 개수
        int len = gems.length;
        
        int left = 0; int right = 0;
        int range = 100_000;
        
        while (right < len) {
            if (cur.size() != limit) {
                cur.add(gems[right]);
                map.put(gems[right], map.getOrDefault(gems[right], 0) + 1);
                right++;
            } 
                
            while (cur.size() == limit) {
                if (right - left < range) {
                    answer[0] = left + 1;
                    answer[1] = right;
                    range = right - left;   
                }  
                
                map.put(gems[left], map.get(gems[left]) - 1);
                if (map.get(gems[left]) == 0) cur.remove(gems[left]); 
                    
                left++;
            }
        }
        
        return answer;
    }
}