import java.util.*;

class Solution {
    private int answer;
    private int[][] graph;
    private int n, m;
    private boolean[][] visited;
    private final int[] dx = {-1, 1, 0, 0};
    private final int[] dy = {0, 0, -1, 1};
    
    public int solution(int[][] maps) {
        answer = -1;
        graph = maps;
        n = graph.length; m = graph[0].length;
        visited = new boolean[n][m];
        bfs();
        
        return answer;
    }
    
    private void bfs() {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(0, 0, 1));
        visited[0][0] = true;
        
        while(!q.isEmpty()) {
            Node node = q.poll();
            if (node.x == n - 1 && node.y == m - 1) {
                answer = node.cnt;
                break;
            }
            
            for (int i = 0; i < 4; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if (graph[nx][ny] == 1 && !visited[nx][ny]) {
                    q.offer(new Node(nx, ny, node.cnt + 1));
                    visited[nx][ny] = true;
                }
            }
        }
    }
    
    class Node {
        int x;
        int y;
        int cnt;
        
        Node(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
}