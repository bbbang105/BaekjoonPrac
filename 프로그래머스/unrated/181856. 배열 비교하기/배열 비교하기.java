import java.util.Arrays;

class Solution {
    public int solution(int[] arr1, int[] arr2) {
        int answer = 0;
        if (arr1.length == arr2.length) {
            int sum1 = Arrays.stream(arr1).sum();
            int sum2 = Arrays.stream(arr2).sum();
            if (sum1 != sum2) {
                if (sum1 > sum2) {
                    answer = 1;
                } else {
                    answer = -1;
                }
            }
        } else {
            int len1 = arr1.length;
            int len2 = arr2.length;
            if (len1 > len2) {
                answer = 1;
            } else {
                answer = -1;
            }
        }
        return answer;
    }
}