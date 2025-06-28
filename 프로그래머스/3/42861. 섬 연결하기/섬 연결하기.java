import java.util.*;

class Solution {
    
    private static int[] parents;
    
    public int solution(int n, int[][] costs) {
        Arrays.sort(costs, (o1, o2) -> o1[2] - o2[2]);
        int result = 0;
        parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }
        
        int a, b, w;
        int aParent, bParent;
        for (int[] cost : costs) {
            a = cost[0];
            b = cost[1];
            w = cost[2];
            
            aParent = find(a);
            bParent = find(b);
            if (aParent != bParent) {
                union(aParent, bParent);
                result += w;
            }
        }
        
        return result;
    }
    
    private static void union(int aParent, int bParent) {
        parents[bParent] = aParent;
    }
    
    private static int find(int num) {
        if (num == parents[num]) {
            return num;
        }
        return parents[num] = find(parents[num]);
    }
}