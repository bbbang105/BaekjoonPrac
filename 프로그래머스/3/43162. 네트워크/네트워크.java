class Solution {
    private int[] parent;
    
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
        
        int result = 0;
        for (int i = 0; i < n; i++) {
            // 본인이 부모 노드인 경우에는 그룹 +1
            if (parent[i] == i) result++;
        }
        
        return result;
    }
    
    private int find(int a) {
        if (parent[a] == a) return a;
        else return parent[a] = find(parent[a]);
    }
    
    private void union(int a, int b) {
        int A = find(a); int B = find(b);
        if (A != B) parent[B] = A;
    }
}