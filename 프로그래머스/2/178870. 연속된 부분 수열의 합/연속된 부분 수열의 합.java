class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = new int[2];
        int intervalSum = 0; int end = 0; int len = 1_000_001;
        
        for (int start = 0; start < sequence.length; start++) {
            while (end < sequence.length && intervalSum < k) {
                intervalSum += sequence[end];
                end++;
            }
            
            if (intervalSum == k && end - start < len) {
                len = end - start;
                answer[0] = start; answer[1] = end - 1;
            }
            
            intervalSum -= sequence[start];
        }
        
        return answer;
    }
}