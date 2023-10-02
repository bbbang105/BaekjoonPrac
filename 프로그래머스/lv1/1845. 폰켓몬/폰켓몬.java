import java.util.HashMap;

class Solution {
    public int solution(int[] nums) {
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int n : nums) {
            map.put(n,1);
        }
        return Math.min(map.size(), nums.length / 2);
    }
}