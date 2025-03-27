import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	
	private static BufferedReader br;
	private static StringTokenizer st;
	private static StringBuilder sb;
	
	private static int counfOfVertexs;
	private static int counfOfEdges;
	private static Edge[] edges;
	private static int[] parents;
	private static long result;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		int testCaseNum = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= testCaseNum; testCase++) {
			init();
			findResult();
			sb.append("#").append(testCase).append(" ").append(result).append('\n');
		}
		System.out.println(sb);
	}
	
	/**
	 * 결과를 도출하는 메서드.
	 */
	private static void findResult() {
		int from, to, weight, fromParent, toParent;
		for (Edge edge : edges) {
			from = edge.from;
			to = edge.to;
			weight = edge.weight;
			
			fromParent = find(from);
			toParent = find(to);
			if (fromParent != toParent) {
				result += weight;
				union(fromParent, toParent);
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
		edges = new Edge[counfOfEdges];
		int from, to, weight;
		for (int index = 0; index < counfOfEdges; index++) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			weight = Integer.parseInt(st.nextToken());
			edges[index] = new Edge(from, to, weight);
		}
		Arrays.sort(edges);
		
		parents = new int[counfOfVertexs + 1];
		for (int index = 1; index <= counfOfVertexs; index++) {
			parents[index] = index;
		}
	}
	
	/**
	 * Union 연산
	 */
	private static void union(int aParent, int bParent) {
		parents[bParent] = aParent;
	}
	
	/**
	 * Find 연산
	 */
	private static int find(int num) {
		if (parents[num] == num) {
			return num;
		}
		return parents[num] = find(parents[num]);
	}
	
	/**
	 * 연결 정보를 담는 클래스. 
	 */
	static class Edge implements Comparable<Edge> {
		int from;
		int to;
		int weight;
		
		Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}
}
