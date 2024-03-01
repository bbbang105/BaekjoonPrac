import java.util.*;

class Solution {
    private static int[] parent;
    
    public int solution(int n, int[][] costs) {
        parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;
        int answer = 0;
        
        Arrays.sort(costs, (o1, o2) -> o1[2] - o2[2]);
        
        for (int[] cost : costs) {
            int v = cost[0];
            int e = cost[1];
            int w = cost[2];
            
            if (find(v) != find(e)) {
                union(v, e);
                answer += w;
            }
        }
    
        return answer;
        
    }
    
    private int find(int n) {
        if (parent[n] == n) return n;
        else return parent[n] = find(parent[n]);
    }
        
    private void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        
        if (pa != pb) {
            parent[pb] = pa;
        }
    }
}
