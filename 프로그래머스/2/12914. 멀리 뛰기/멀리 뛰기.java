class Solution {
    public long solution(int n) {
        long[] dp = new long[n + 2]; // n이 1일 수도 있으므로 +2
        dp[1] = 1L; dp[2] = 2L;
        
        if (n > 2) {
            for (int i = 3; i < n + 1; i++) {
                dp[i] = (dp[i - 2] + dp[i - 1]) % 1234567;
            }    
        }
        
        return dp[n];
    }
}