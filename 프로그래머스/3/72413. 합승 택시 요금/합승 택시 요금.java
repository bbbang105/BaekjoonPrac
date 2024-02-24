import java.util.*;

class Solution {
    private static List<List<Node>> graph;
    private static final int MAX_VALUE = Integer.MAX_VALUE;
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        
        graph = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) graph.add(new ArrayList<>());
        
        for (int[] fare : fares) {
            int v = fare[0]; int e = fare[1]; int w = fare[2];
            graph.get(v).add(new Node(e, w));
            graph.get(e).add(new Node(v, w));
        }
        
        int[] sDist = dijkstra(n, s);
        int[] aDist = dijkstra(n, a);
        int[] bDist = dijkstra(n, b);
        
        int answer = MAX_VALUE;
        for (int i = 1; i < n + 1; i++) {
            answer = Math.min(answer, sDist[i] + aDist[i] + bDist[i]);
        }
        
        return answer;
    }
    
    private int[] dijkstra(int n, int s) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, MAX_VALUE);
        dist[s] = 0;
        Queue<Integer> q = new LinkedList<>();
        q.offer(s);
        
        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int i = 0; i < graph.get(cur).size(); i++) {
                Node node = graph.get(cur).get(i);
                int next = node.vertex;
                int weight = node.weight;
                
                if (dist[cur] + weight < dist[next]) {
                    dist[next] = dist[cur] + weight;
                    q.offer(next);
                }
            }
        }
        
        return dist;
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