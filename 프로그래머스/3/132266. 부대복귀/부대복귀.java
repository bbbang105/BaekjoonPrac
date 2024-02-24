import java.util.*;

class Solution {
    private static List<List<Integer>> graph; // 간선 정보
    private static int[] dist; // 최단 거리
    private static int start; // 시작점
    private static final int MAX_VALUE = Integer.MAX_VALUE;
    
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] result = new int[sources.length];
        dist = new int[n + 1];
        start = destination;
        
        graph = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) graph.add(new ArrayList<>());
        
        for (int[] road : roads) {
            // 양방향
            graph.get(road[0]).add(road[1]);
            graph.get(road[1]).add(road[0]);
        }
        
        // 시작 설정 후 다익스트라
        Arrays.fill(dist, MAX_VALUE);
        dist[start] = 0;
        dijkstra();
        
        for (int i = 0; i < sources.length; i++) {
            if (dist[sources[i]] < MAX_VALUE) {
                result[i] = dist[sources[i]];
            } else result[i] = -1;
        } 
        
        return result;
    }
    
    private void dijkstra() {
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        
        while (!q.isEmpty()) {
            int n = q.poll();
            
            for (int i = 0; i < graph.get(n).size(); i++) {
                int next = graph.get(n).get(i);
                // start -> n -> next가 start -> next 보다 더 빠른 경우, 경로 압축
                if (dist[n] + 1 < dist[next]) {
                    dist[next] = dist[n] + 1;
                    q.offer(next);
                }
            }
        }
    }
}