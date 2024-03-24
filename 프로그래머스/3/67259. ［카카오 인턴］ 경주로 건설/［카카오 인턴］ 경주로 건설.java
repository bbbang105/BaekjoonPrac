import java.util.*;

class Solution {
    private static final int[] dx = {-1, 1, 0, 0};
    private static final int[] dy = {0, 0, -1, 1};
    private static int[][] graph;
    private static int answer;
    private static int row;
    private static int col;
    
    public int solution(int[][] board) {
        graph = board;
        answer = 10000000;
        row = board.length;
        col = board[0].length;
        
        bfs();
        
        return answer;
    }
    
    private void bfs() {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(0, 0, -1, 0));
        boolean[][][] visited = new boolean[row][col][4]; 
        
        while (!q.isEmpty()) {
            Node node = q.poll();
            int x = node.x; int y = node.y;
            int direction = node.direction; 
            
            if (x == row - 1 && y == col - 1) {
                // 최소 비용으로 갱신
                answer = Math.min(answer, node.cost);
                continue;
            }
            
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if (nx < 0 || nx >= row || ny < 0 || ny >= col || graph[nx][ny] == 1) continue;
                
                int cost = node.cost;
                if (direction == -1 || direction == i) {
                    // 직진
                    cost += 100;
                } else {
                    // 커브
                    cost += 600;
                }
                
                if (!visited[nx][ny][i] || graph[nx][ny] >= cost) {
                    // 방문하지 않았거나, 방문했지만 더 저렴하게 갱신 가능한 경우
                    q.offer(new Node(nx, ny, i, cost));
                    graph[nx][ny] = cost;  
                    visited[nx][ny][i] = true;
                }
            }
        }
    }
    
    class Node {
        int x;
        int y;
        int direction;
        int cost;
        
        Node(int x, int y, int direction, int cost) {
            this.x = x;
            this.y = y;
            this.direction = direction;
            this.cost = cost;
        }
    }
}