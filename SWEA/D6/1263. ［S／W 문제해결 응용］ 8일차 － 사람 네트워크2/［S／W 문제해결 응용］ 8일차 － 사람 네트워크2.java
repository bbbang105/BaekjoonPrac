import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [조건]
 * 1. 연결된 노드들은 인접 행렬로 들어온다.
 * 2. 하나의 노드 -> 다른 모든 노드로 도달하는 데 걸리는 최소 거리를 계산 -> 그 중에서 가장 작은 값을 구하라.
 * 	2-1. 즉, 다른 모든 노드들과 가장 인접해 있는 노드를 찾아야 한다.
 * 
 * [풀이]
 * 1. 플로이드-워셜 알고리즘을 활용한다.
 * 2. 하나의 노드에서 다른 모든 노드로 도달하는 최소 거리를 구해 두고, 그 중 최솟값을 출력한다.
 */
public class Solution {
	
	private static final int INF = 1_001;
	
	private static BufferedReader br;
	private static StringTokenizer st;
	
	private static int countOfNodes;
	private static int[][] dp;
	private static int result;
	
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
		int rowSum;
		for (int[] row : dp) {
			rowSum = 0;
			for (int num : row) {
				rowSum += num;
			}
			result = Math.min(result, rowSum);
		}
	}
	
	/**
	 * 플로이드-워셜 알고리즘.
	 */
	private static void floydWarshall() {
		for (int from = 0; from < countOfNodes; from++) {
			for (int to = 0; to < countOfNodes; to++) {
				for (int mid = 0; mid < countOfNodes; mid++) {
					dp[from][to] = Math.min(dp[from][to], dp[from][mid] + dp[mid][to]);
				}
			}
		}
	}
	
	/**
	 * 초기 세팅 메서드.
	 */
	private static void init() throws IOException {
		result = 1_000_001;
		st = new StringTokenizer(br.readLine());
		countOfNodes = Integer.parseInt(st.nextToken());
		dp = new int[countOfNodes][countOfNodes];
		
		int cur;
		for (int rowIndex = 0; rowIndex < countOfNodes; rowIndex++) {
			for (int colIndex = 0; colIndex < countOfNodes; colIndex++) {
				cur = Integer.parseInt(st.nextToken());
				if (rowIndex == colIndex) {
					dp[rowIndex][colIndex] = 0;
				} else if (cur == 0) {
					dp[rowIndex][colIndex] = INF;
				} else {
					dp[rowIndex][colIndex] = 1;
				}
			}
		}
	}
}
