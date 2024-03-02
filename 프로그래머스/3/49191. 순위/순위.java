import java.util.*;

class Solution {
    private static final int INF = 1_000_000_000;
    
    public int solution(int n, int[][] results) {
        int answer = 0;
        int[][] graph = new int[n + 1][n + 1];
        for (int[] g : graph) Arrays.fill(g, INF);
        
        for (int[] result : results) {
            int a = result[0]; int b = result[1];
            graph[a][b] = 1;
        }
        
        for (int k = 1; k < n + 1; k++) {
            for (int i = 1; i < n + 1; i++) {
                for (int j = 1; j < n + 1; j++) {
                    if (i == j) {
                        graph[i][j] = 0; 
                        continue;
                    }
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }
        
        for (int i = 1; i < n + 1; i++) {
            boolean flag = true;
            for (int j = 1; j < n + 1; j++) {
                if (i != j && graph[i][j] == INF && graph[j][i] == INF) {
                    flag = false;
                    break;
                }
            }
            
            if (flag) answer++;
        }
        
        return answer;
    }
}