import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        Arrays.sort(people);
        
        int left = 0;
        int right = people.length - 1;
        int save = 0;
        
        while (left < right) {
            int weight = people[left] + people[right];
            
            if (weight <= limit) {
                left++;
                save++;
            }
            
            right--;
        }
        
        return people.length - save;
    }
}