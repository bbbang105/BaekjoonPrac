import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        int len = A.length;
        int answer = 0;
        
        Arrays.sort(A); 
        Arrays.sort(B);
        
        int index = 0;
        for (int i = 0; i < len; i++) {
            while (index < len) {
                if (A[i] < B[index++]) {
                    answer++;
                    break;
                }
            }
        }
        
        return answer;
    }
}