import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[] solution(int[] arr) {
        List<Integer> nums =  new ArrayList<>();

        int previousNum = -1;
        for (int n : arr) {
            if (previousNum != n) {
                nums.add(n);
                previousNum = n;
            }
        }

        return nums.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }
}
