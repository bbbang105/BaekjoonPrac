class Solution {
    private final static int MOD = 1000000007;
    
    public int solution(int m, int n, int[][] puddles) {
        int[][] dp = new int[n + 1][m + 1];
        dp[1][1] = 1; // 시작점
        
        for (int[] puddle : puddles) {
            dp[puddle[1]][puddle[0]] = -1;
        }
        
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                // 시작점이거나 웅덩이인 경우 건너뜀
                if ((i == 1 && j == 1) || dp[i][j] == -1) continue;
                
                // 위쪽에서 오는 경로와 왼쪽에서 오는 경로의 합을 MOD로 나눈 나머지로 설정
                int up = (i > 1 && dp[i-1][j] != -1 ? dp[i-1][j] : 0);
                int left = (j > 1 && dp[i][j-1] != -1 ? dp[i][j-1] : 0);
                dp[i][j] = (up + left) % MOD;
            }
        }
        
        return dp[n][m];
    }
}