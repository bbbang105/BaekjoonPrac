import java.util.*;

class Solution {
    private static final int[] DX = {-1, 1, 0, 0};
    private static final int[] DY = {0, 0, -1, 1};
    private static int answer;
    
    public int solution(int[][] maps) {
        answer = -1;
        bfs(maps, new Node(0, 0, 1));
        
        return answer;
    }
    
    private void bfs(int[][] graph, Node node) {
        int row = graph.length;
        int col = graph[0].length;
        
        boolean[][] visited = new boolean[row][col];
        visited[node.x][node.y] = true;
        Queue<Node> q = new LinkedList<>();
        q.offer(node);
        
        while(!q.isEmpty()) {
            Node cur = q.poll();
            
            if (cur.x == row - 1 && cur.y == col - 1) {
                answer = cur.cnt;
                break;
            }
            
            for (int i = 0; i < 4; i++) {
                int x = cur.x + DX[i];
                int y = cur.y + DY[i];
                
                if (x < 0 || x >= row || y < 0 || y >= col) continue;
                if (!visited[x][y] && graph[x][y] != 0) {
                    visited[x][y] = true;
                    q.offer(new Node(x, y, cur.cnt + 1));
                }
            }
        }
    }
    
    class Node {
        int x;
        int y;
        int cnt;
        
        Node (int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
}