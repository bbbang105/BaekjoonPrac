class Solution {
    private static int[] parent;
    
    public int solution(int n, int[][] computers) {
        parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && computers[i][j] == 1) {
                    if (find(i) != find(j)) union(i, j);
                }
            }
        }
        
        int answer = 0;
        for (int i = 0; i < n; i++) {
            if (parent[i] == i) answer++;
        }
        
        return answer;
    }
    
    private void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        
        if (pa != pb) parent[pb] = pa;
    }
    
    private int find(int n) {
        if (parent[n] == n) return n;
        else return parent[n] = find(parent[n]);
    }
}