import java.io.*;
import java.util.*;

public class Main {
    private static final int INF = Integer.MAX_VALUE;
    private static int[] dist;
    private static List<List<Node>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(br.readLine());

        graph = new ArrayList<>();
        for (int i = 0; i <= V; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Node(b, w));
        }

        dist = new int[V + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;
        dijkstra(start);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= V; i++) {
            int d = dist[i];
            if (d == INF) {
                sb.append("INF").append('\n');
            } else {
                sb.append(d).append('\n');
            }
        }

        System.out.println(sb);
    }

    private static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node curNode = pq.poll();
            int cur = curNode.vertex;
            int curDist = curNode.weight;

            if (curDist > dist[cur]) continue;

            for (Node node : graph.get(cur)) {
                int next = node.vertex;
                int weight = node.weight;

                if (dist[cur] + weight < dist[next]) {
                    dist[next] = dist[cur] + weight;
                    pq.offer(new Node(next, dist[next]));
                }
            }
        }
    }

    static class Node implements Comparable<Node> {
        int vertex;
        int weight;

        Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node n) {
            return Integer.compare(this.weight, n.weight);
        }
    }
}