import java.util.Arrays;

class Solution {
    public int solution(int[] people, int limit) {
        Arrays.sort(people);
        int left = 0;
        int right = people.length - 1;
        
        int saveCount = 0;
        while (left < right) {
            if (people[left] + people[right] <= limit) {
                saveCount++;
                left++;
                right--;
            } else {
                right--;
            }
        }
        
        return people.length - saveCount;
    }
}