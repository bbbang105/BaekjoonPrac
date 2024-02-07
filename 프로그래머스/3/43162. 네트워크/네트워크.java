import java.util.LinkedList;
import java.util.Queue;

class Solution {
    int[][] graph;
    boolean[] visited;
    int network;
    int len;
    public int solution(int n, int[][] computers) {
        graph = computers;
        visited = new boolean[n];
        network = 0;
        len = n;
        for (int i = 0; i < len; i++) {
            if (!visited[i]) {
                bfs(i);
                network++;
            }
        }

        return network;
    }

    private void bfs(int num) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(num);
        visited[num] = true;
        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int next = 0; next < len; next++) {
                if (cur == next) continue;
                if (graph[cur][next] == 1 && !visited[next]) {
                    visited[next] = true;
                    q.offer(next);
                }
            }
        }
    }
}