import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
* [조건]
* 1. 마을의 개수 N은 1이상 50 이하이다.
* 2. road의 길이는 1이상 2,000 이하이다.
* 3. road는 길이가 3인 2차원 배열로, (from, to, weight)로 구성된다.
* 4. 가중치는 1이상 10,000 이하이다.
* 5. 두 마을을 연결하는 도로는 여러 개가 주어질 수 있다.
* 6. 음식 배달이 가능한 시간 K는 1이상 500,000 이하이다.
* 7. 1번 마을을 시작점으로, K 이하 시간에 배달 가능한 마을의 개수를 반환하라.
*
* [풀이]
* 1. 다익스트라 알고리즘을 활용하여, 1번부터 모든 점으로 이동하는 최단거리를 구한다.
* 2. K 이하의 거리로 갈 수 있는 마을이 몇 개인지 세어 출력한다.
*/

class Solution {
    
    private static final int INF = Integer.MAX_VALUE;
    private static final int START_POINT = 1;
    private static int countOfVillages;
    private static int distLimit;
    private static List<Edge>[] graph;
    private static int[] dist;
    private static int result;
    
    public int solution(int N, int[][] road, int K) {
        countOfVillages = N;
        distLimit = K;
        
        init(road);
        dijkstra();
        countResult();

        return result;
    }
    
    /**
    * 다익스트라 알고리즘.
    */
    private static void dijkstra() {
        PriorityQueue<Edge> q = new PriorityQueue<>();
        q.offer(new Edge(START_POINT, 0));
        
        int cur;
        while (!q.isEmpty()) {
        	cur = q.poll().vertex;
            int next, weight;
            for (Edge edge : graph[cur]) {
                next = edge.vertex;
                weight = edge.weight;
                if (dist[next] > dist[cur] + weight) {
                    dist[next] = dist[cur] + weight;
                    q.offer(new Edge(next, dist[next])); 
                }
            }
        }
    }
    
    /**
    * 최종적으로 도달 가능한 마을 개수를 세어주는 메서드.
    */
    private static void countResult() {
        for (int d : dist) {
            if (d <= distLimit) {
                result++;
            }
        }
    }
    
    /**
    * 초기 세팅을 진행하는 메서드.
    */
    private static void init(int[][] roads) {
        graph = new ArrayList[countOfVillages + 1];
        for (int index = 1; index <= countOfVillages; index++) {
            graph[index] = new ArrayList<>();
        }
        dist = new int[countOfVillages + 1];
        Arrays.fill(dist, INF);
        dist[START_POINT] = 0;
        
        int from, to, weight;
        for (int[] road : roads) {
            from = road[0];
            to = road[1];
            weight = road[2];
            
            graph[from].add(new Edge(to, weight));
            graph[to].add(new Edge(from, weight));
        }
        
        result = 0;
    }
    
    /**
    * 간선 정보를 담은 클래스.
    */
    static class Edge implements Comparable<Edge> {
        int vertex;
        int weight;
        
        Edge (int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
    }
}
