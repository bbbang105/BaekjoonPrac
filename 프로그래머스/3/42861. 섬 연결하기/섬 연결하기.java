import java.util.*;
import java.io.*;

class Solution {
    
    private static int[] parents;
    
    public int solution(int n, int[][] costs) {
        Arrays.sort(costs, (o1, o2) -> o1[2] - o2[2]);
        int answer = 0;
        parents = new int[n];
        for (int index = 0; index < n; index++) {
            parents[index] = index;
        }
        
        int a, b, w;
        int aRoot, bRoot;
        for (int[] cost : costs) {
            a = cost[0];
            b = cost[1];
            w = cost[2];
            
            aRoot = find(a);
            bRoot = find(b);
            if (aRoot != bRoot) {
                union(aRoot, bRoot);
                answer += w;
            }
        }
        return answer;
    }
    
    private static void union(int aRoot, int bRoot) {
        parents[bRoot] = aRoot;
    }
    
    private static int find(int num) {
        if (num == parents[num]) {
            return num;
        }
        return parents[num] = find(parents[num]);
    }
}
