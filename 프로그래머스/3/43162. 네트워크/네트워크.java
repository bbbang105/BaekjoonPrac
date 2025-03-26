class Solution {
    
    private static int[] parents;
    
    public int solution(int n, int[][] computers) {
        parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && computers[i][j] == 1) {
                    if (find(i) != find(j)) {
		                    // 부모가 다른 경우
                        union(i, j);
                    }
                }
            }
        }
        
        int answer = 0;
        for (int i = 0; i < n; i++) {
            if (parents[i] == i) {
                answer++;
            }
        }
        
        return answer;
    }
    
    private void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        parents[pb] = pa; // 한 방향으로 통일해야 함
    }
    
    private int find(int n) {
        if (parents[n] == n) {
            return n;
        }
        else {
            return parents[n] = find(parents[n]);
        }
    }
}
