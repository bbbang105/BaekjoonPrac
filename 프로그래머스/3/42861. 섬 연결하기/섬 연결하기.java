import java.util.*;

class Solution {
    
    private static int[] parents;
    
    public int solution(int n, int[][] costs) {
        int answer = 0;
        parents = new int[n];
        for (int index = 0; index < n; index++) {
            parents[index] = index;
        }
        
        Arrays.sort(costs, (o1, o2) -> o1[2] - o2[2]);
        
        int a, b, w;
        for (int[] cost : costs) {
            a = cost[0];
            b = cost[1];
            w = cost[2];
            
            int aParent = find(a);
            int bParent = find(b);
            if (aParent != bParent) {
                answer += w;
                parents[bParent] = aParent;
            }
        }
        
        return answer;
    }
    
    /*
    private static void union(int a, int b) {
        int aParent = find(a);
        int bParent = find(b);
        if (aParent != bParent) {
            parents[bParent] = aParent;
        }
    }*/
    
    private static int find(int num) {
        if (parents[num] == num) {
            return num;
        }
        return parents[num] = find(parents[num]);
    }
}