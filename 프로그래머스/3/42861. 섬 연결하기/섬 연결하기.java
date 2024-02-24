import java.util.*;

class Solution {
    private int[] parent;
    
    public int solution(int n, int[][] costs) {
        parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;
        
        Arrays.sort(costs, (o1, o2) -> o1[2] - o2[2]);
        
        int result = 0;
        for (int[] cost : costs) {
            int a = cost[0]; int b = cost[1]; int w = cost[2]; 
            if (find(a) != find(b)) {
                union(a, b);
                result += w;
            }
        }
        
        return result;
    }

    private int find(int a) {
        if (parent[a] == a) return a;
        else return parent[a] = find(parent[a]); 
    }
    
    private void union(int a, int b) {
        int ap = find(a);
        int bp = find(b);
        if (ap != bp) {
            parent[bp] = ap;
        }
    }
}