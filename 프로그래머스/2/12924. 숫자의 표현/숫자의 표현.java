class Solution {
    public int solution(int n) {
        int[] numbers = new int[n + 1];
        for (int i = 1; i < n + 1; i++) numbers[i] = i;
        
        int answer = 0; int intervalSum = 0; int end = 1;
        for (int start = 1; start < n + 1; start++) {
            while (end < n + 1 && intervalSum < n) {
                intervalSum += numbers[end];
                end++;
            }
            
            if (intervalSum == n) answer++;
            
            intervalSum -= numbers[start];
        }
        
        return answer;
    }
}