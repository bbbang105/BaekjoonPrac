import java.util.HashMap;

class Solution {
    public int solution(int[] nums) {
        int answer = 0;
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int n : nums) {
            if (!map.containsKey(n)) {
                map.put(n,1);
            }
        }
        int choice_num = nums.length / 2;
        int map_num = map.keySet().size();
        if (map_num >= choice_num) {
            answer = choice_num;
        } else {
            answer = map_num;
        }
        return answer;
    }
}