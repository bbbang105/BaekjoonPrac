import java.util.*;

class Solution {
    private static List<List<Node>> graph;
    private static int[] dist;
    private static final int START = 1;
    private static final int INF = Integer.MAX_VALUE;
    
    public int solution(int N, int[][] roads, int K) {
        
        graph = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) graph.add(new ArrayList<>());
        
        for (int[] road : roads) {
            int a = road[0]; int b = road[1]; int w = road[2];
            graph.get(a).add(new Node(b, w));
            graph.get(b).add(new Node(a, w));
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
        Queue<Integer> q = new LinkedList<>();
        q.add(START);
        
        while (!q.isEmpty()) {
            int cur = q.poll();
            
            for (int i = 0; i < graph.get(cur).size(); i++) {
                Node node = graph.get(cur).get(i);
                int next = node.vertex; int nextWeight = node.weight;
                
                if (dist[cur] + nextWeight < dist[next]) {
                    dist[next] = dist[cur] + nextWeight;
                    q.offer(next);
                }
            }
        }
    }
    
    class Node {
        int vertex;
        int weight;
        
        Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }
}