import java.util.*;

class Solution {
    private static final int MOD = 1_000_000_007;
    
    public int solution(int m, int n, int[][] puddles) {
        int row = n; int col = m;
        int[][] dp = new int[row][col];
        
        for (int[] puddle : puddles) {
            dp[puddle[1] - 1][puddle[0] - 1] = -1;
        }
        
        dp[0][0] = 1;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i == 0 && j == 0) continue;
                if (dp[i][j] == -1) continue;
                
                int up = (i >= 1 && dp[i - 1][j] != -1 ? dp[i-1][j] : 0);
                int left = (j >= 1 && dp[i][j - 1] != -1 ? dp[i][j - 1] : 0);
                dp[i][j] = (up + left) % MOD;
            }
        }

        return dp[row - 1][col - 1];
    }
}