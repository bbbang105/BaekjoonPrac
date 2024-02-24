import java.util.*;

class Solution {
    private static List<List<int[]>> graph;
    private static int[] dist;
    private static final int START = 1;
    private static final int INF = Integer.MAX_VALUE;
    
    public int solution(int N, int[][] roads, int K) {
        
        graph = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) graph.add(new ArrayList<>());
        
        for (int[] road : roads) {
            int a = road[0]; int b = road[1]; int w = road[2];
            graph.get(a).add(new int[]{b, w});
            graph.get(b).add(new int[]{a, w});
        }
        
        dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[START] = 0;
        dijkstra();
        
        int result = 0;
        for (int cost : dist) {
            if (cost <= K) result++;
        }


        return result;
    }
    
    private void dijkstra() {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{START, 0});
        
        while (!q.isEmpty()) {
            int[] temp = q.poll();
            int cur = temp[0]; int curWeight = temp[1];
            
            for (int i = 0; i < graph.get(cur).size(); i++) {
                int[] t = graph.get(cur).get(i);
                int next = t[0]; int nextWeight = t[1];
                
                if (dist[cur] + nextWeight < dist[next]) {
                    dist[next] = dist[cur] + nextWeight;
                    q.offer(new int[]{next, nextWeight});
                }
            }
        }
    }
}