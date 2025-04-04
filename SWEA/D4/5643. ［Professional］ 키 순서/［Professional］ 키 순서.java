import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [조건]
 * 1. 번호는 1번부터 N번까지 붙여져 있다.
 * 2. 학생들의 수 N은 2이상 500이하이다.
 * 3. 키를 비교한 횟수(간선) M은 0이상 N*(N-1)/2 이다.
 * 4. a -> b라면 a가 b학생보다 작음을 의미한다.
 * 
 * [풀이]
 * 1. 간선 정보를 입력 받으며 저장한다.
 * 2. 플로이드 워셜 알고리즘을 활용하여 연결한다.
 * 3. 본인보다 큰 노드의 개수와 작은 노드의 개수를 구한다.
 * 4. 큰 노드의 개수 + 작은 노드의 개수 == 전체 노드 개수 - 1 이라면 -> 본인의 순서를 알고 있는 것이다.
 */
public class Solution {
	
	private static BufferedReader br;
	private static StringTokenizer st;
	
	private static int result;
	private static int[][] graph;
	private static int countOfVertexs;
	private static int countOfEdges;
	
	/**
	 * 메인 메서드.
	 */
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		br = new BufferedReader(new InputStreamReader(System.in));
		int testCaseNum = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= testCaseNum; testCase++) {
			init();
			floydWarshall();
			findResult();
			sb.append("#").append(testCase).append(" ").append(result).append('\n');
		}
		System.out.println(sb);
	}
	
	/**
	 * 결과를 도출하는 메서드.
	 */
	private static void findResult() {
		int biggerThanMe, smallerThanMe;
		for (int from = 1; from <= countOfVertexs; from++) {
			biggerThanMe = 0;
			smallerThanMe = 0;
			for (int to = 1; to <= countOfVertexs; to++ ) {
				biggerThanMe += graph[from][to];
				smallerThanMe += graph[to][from];
			}
			if (biggerThanMe + smallerThanMe == countOfVertexs - 1) {
				// 큰 노드의 개수 + 작은 노드의 개수 == 전체 노드 개수 - 1 이라면 result++
				result++;
			}
		}
	}
	
	/**
	 * 플로이드-워셜 알고리즘
	 */
	private static void floydWarshall() {
		for (int from = 1; from <= countOfVertexs; from++) {
			for (int to = 1; to <= countOfVertexs; to++) {
				for (int mid = 1; mid <= countOfVertexs; mid++) {
					if (graph[from][mid] == 1 && graph[mid][to] == 1) {
						graph[from][to] = 1;
					}
				}
			}
		}
	}
	
	/**
	 * 초기 세팅 메서드.
	 */
	private static void init() throws IOException {
		result = 0;
		countOfVertexs = Integer.parseInt(br.readLine());
		countOfEdges = Integer.parseInt(br.readLine());
		graph = new int[countOfVertexs + 1][countOfVertexs + 1];
		
		int from, to;
		for (int index = 0; index < countOfEdges; index++) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			graph[from][to] = 1;
		}
	}
}
