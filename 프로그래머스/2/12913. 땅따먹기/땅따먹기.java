class Solution {
    int solution(int[][] land) {
        int answer = 0;
        int[][] dp = new int[land.length][4];
        for (int t = 0; t < 4; t++) dp[0][t] = land[0][t]; // 초기 세팅
        
        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    if (j == k) continue;
                    dp[i][k] = Math.max(dp[i][k], dp[i-1][j] + land[i][k]);
                }
            }
        }
        
        for (int l = 0; l < 4; l++) answer = Math.max(answer, dp[dp.length - 1][l]);
        
        return answer;
    }
}