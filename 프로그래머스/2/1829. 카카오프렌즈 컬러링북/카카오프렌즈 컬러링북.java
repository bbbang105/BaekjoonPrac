import java.util.LinkedList;
import java.util.Queue;

class Solution {
    private final int[] dx = {1,-1,0,0};
    private final int[] dy = {0,0,-1,1};
    private int w, h, numberOfArea, maxSize;
    private int[][] graph;
    private boolean[][] visited;

    public int[] solution(int m, int n, int[][] picture) {
        w = m; h = n; graph = picture;
        numberOfArea = maxSize = 0;
        visited = new boolean[w + 1][h + 1];

        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                if (!visited[i][j] && graph[i][j] != 0) {
                    maxSize = Math.max(maxSize, bfs(i, j));
                    numberOfArea++;
                }
            }
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSize;

        return answer;
    }

    private int bfs(int i, int j) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{i, j});
        visited[i][j] = true;
        int groupNumber = graph[i][j]; // 해당 그룹의 색상
        int size = 1; // 해당 그룹의 크기

        while (!q.isEmpty()) {
            int[] arr = q.poll();
            int x = arr[0]; int y = arr[1];

            for (int k = 0; k < dx.length; k++) {
                int nx = x + dx[k]; int ny = y + dy[k];
                
                if (nx < 0 || nx >= w || ny < 0 || ny >= h) continue;

                if (!visited[nx][ny] && graph[nx][ny] == groupNumber) {
                    visited[nx][ny] = true;
                    size++;
                    q.offer(new int[]{nx,ny});
                }
            }
        }

        return size;
    }
}