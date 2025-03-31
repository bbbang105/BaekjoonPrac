import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * [조건]
 * 1. 컴퓨터의 수 N은 1이상 1,000이하이다.
 * 2. 연결할 수 있는 선의 수 1이상 100,000이하이다.
 * 
 * [풀이]
 * 1. 프림 알고리즘을 활용한다.
 */
public class Main {
	
	private static int result;
	private static int countOfComputers;
	private static int countOfEdges;
	private static List<Edge>[] graph;
	private static boolean[] isVisited;
	
	public static void main(String[] args) throws IOException {
		init();
		prim();
		System.out.println(result);
	}
	
	/**
	 * 프림 알고리즘 메서드. 
	 */
	private static void prim() {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.offer(new Edge(1, 0)); // 시작 정점
		
		int vertex, weight;
		while (!pq.isEmpty()) {
			Edge edge = pq.poll();
			vertex = edge.vertex;
			weight = edge.weight;
			
			if (isVisited[vertex]) {
				continue;
			}
			
			isVisited[vertex] = true;
			result += weight;
			
			for (Edge next : graph[vertex]) {
				if (!isVisited[next.vertex]) {
					pq.offer(next);
				}
			}
		}
	}
	
	/**
	 * 초기 세팅을 진행하는 메서드.
	 */
	private static void init() throws IOException {
		result = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		countOfComputers = Integer.parseInt(br.readLine());
		countOfEdges = Integer.parseInt(br.readLine());
		graph = new ArrayList[countOfComputers + 1];
		for (int index = 1; index <= countOfComputers; index++) {
			graph[index] = new ArrayList<>();
		}
		isVisited = new boolean[countOfComputers + 1];
		
		StringTokenizer st;
		int cur, next, weight;
		for (int index = 0; index < countOfEdges; index++) {
			st = new StringTokenizer(br.readLine());
			cur = Integer.parseInt(st.nextToken());
			next = Integer.parseInt(st.nextToken());
			weight = Integer.parseInt(st.nextToken());
			graph[cur].add(new Edge(next, weight));
			graph[next].add(new Edge(cur, weight));
		}
	}
	
	/**
	 * 간선 정보를 담은 클래스.
	 * 가중치를 기준으로 오름차순 정렬한다.
	 */
	static class Edge implements Comparable<Edge> {
		int vertex;
		int weight;
		
		Edge(int vertex, int weight) {
			this.vertex = vertex;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}
}
