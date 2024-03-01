class Solution {
    private static boolean[] visited;
    private static int[][] board;
    private static int answer;

    public int solution(int k, int[][] dungeons) {
        visited = new boolean[dungeons.length];
        board = dungeons;
        answer = 0;
        adventure(k, 0);
        
        return answer;
    }
    
    private void adventure(int health, int cnt) {
        answer = Math.max(answer, cnt);
        
        for (int i = 0; i < board.length; i++) {
            if (!visited[i]) {
                int need = board[i][0];
                int consume = board[i][1];
                
                if (health >= need) {
                    visited[i] = true;
                    adventure(health - consume, cnt + 1);
                    visited[i] = false;
                }
            }
        }
    }
}