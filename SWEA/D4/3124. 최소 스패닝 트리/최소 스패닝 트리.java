import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * [조건]
 * 1. 정점의 개수 V는 1이상 100,000 이하이다.
 * 2. 간선의 개수 E는 1이상 200,000 이하이다.
 * 3. 가중치 C는 음수일 수 있으며, 절대값이 1,000,000을 넘지 않는다.
 * 
 * [풀이]
 * 1. 프림 알고리즘을 활용한다.
 */
public class Solution {
	
	private static BufferedReader br;
	private static StringTokenizer st;
	private static StringBuilder sb;
	
	private static int counfOfVertexs;
	private static int counfOfEdges;
	private static boolean[] isVisited;
	private static List<Edge>[] graph;
	private static long result;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		int testCaseNum = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= testCaseNum; testCase++) {
			init();
			prim();
			sb.append("#").append(testCase).append(" ").append(result).append('\n');
		}
		System.out.println(sb);
	}
	
	/**
	 * 프림 알고리즘을 통해 결과를 도출하는 메서드.
	 */
	private static void prim() {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.offer(new Edge(1, 0));
		
		Edge edge;
		int cur, weight;
		while (!pq.isEmpty()) {
			edge = pq.poll();
			cur = edge.vertex;
			weight = edge.weight;
			
			if (isVisited[cur]) {
				continue;
			}
			
			isVisited[cur] = true;
			result += weight;
			
			for (Edge next : graph[cur]) {
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
		result = 0L;
		st = new StringTokenizer(br.readLine());
		counfOfVertexs = Integer.parseInt(st.nextToken());
		counfOfEdges = Integer.parseInt(st.nextToken());
		graph = new ArrayList[counfOfVertexs + 1];
		for (int index = 1; index <= counfOfVertexs; index++) {
			graph[index] = new ArrayList<>();
		}
		isVisited = new boolean[counfOfVertexs + 1];
		int from, to, weight;
		for (int index = 0; index < counfOfEdges; index++) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			weight = Integer.parseInt(st.nextToken());
			graph[from].add(new Edge(to, weight));
			graph[to].add(new Edge(from, weight));
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
