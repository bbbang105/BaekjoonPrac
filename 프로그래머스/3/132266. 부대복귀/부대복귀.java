import java.util.*;

class Solution {
    
    private static Map<Integer, List<Integer>> graph; // 간선 정보
    private static int[] dist; // 최단 거리
    private static int start; // 시작점
    private static final int INF = Integer.MAX_VALUE;
    
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] result = new int[sources.length];
        dist = new int[n + 1];
        start = destination;
        
        graph = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            graph.put(i, new ArrayList<>());
        }
        int a, b;
        for (int[] road : roads) {
            a = road[0];
            b = road[1];
            // 양방향
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        
        Arrays.fill(dist, INF);
        dist[start] = 0;
        dijkstra();
        
        for (int i = 0; i < sources.length; i++) {
            if (dist[sources[i]] < INF) {
                result[i] = dist[sources[i]];
            } else {
                result[i] = -1;
            }
        }
        
        return result;
    }
    
    private static void dijkstra() {
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        
        while(!q.isEmpty()) {
            int cur = q.poll();
            
            for (int next : graph.get(cur)) {
                if (dist[cur] + 1 < dist[next]) {
                    dist[next] = dist[cur] + 1;
                    q.offer(next);
                }
            }
        }
    }
}