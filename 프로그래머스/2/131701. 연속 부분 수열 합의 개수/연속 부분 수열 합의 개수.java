import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {

    public int solution(int[] elements) {
        Set<Integer> numsSet = new HashSet<>();
        int len  = elements.length;

        for (int i = 0; i < len; i++) {
            int num = elements[i];
            numsSet.add(num);
            for (int k = 1; k < len; k++) {
                int index = (i + k) % len;
                num += elements[index];
                numsSet.add(num);
            }
        }
        
        return numsSet.size();
    }
}