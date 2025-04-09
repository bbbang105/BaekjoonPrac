import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [조건]
 * 1. 사건의 개수 N은 1이상 400이하이다.
 * 2. 전후 관계 개수 K는 1이상 50,000이하이다.
 * 3. 알고 싶은 사건 쌍의 수 s는 1이상 50,000이하이다.
 * 4. 사건 번호는 1부터 N까지 구성되어 있다.
 * 
 * [풀이]
 * 1. 플로이드-워셜 알고리즘을 활용한다.
 * 2. 아래와 같이 결과를 도출한다.
 * 	2-1. 최종적으로 from -> to가 1이라면 앞에 있는 사건이 먼저 일어난 것이므로 -1이다.
 * 	2-2. 최종적으로 to -> from이 1이라면 뒤에 있는 사건이 먼저 일어난 것이므로 1이다.
 * 	2-3. 두 경우 모두 아니라면 전후관계를 알 수 없으므로 0이다.
 */
public class Main {
	
	private static int countOfNodes;
	private static int countOfEdges;
	private static int[][] dp;
	
	private static BufferedReader br;
	private static StringTokenizer st;
	private static StringBuilder result;
	
	/**
	 * 메인 메서드.
	 */
	public static void main(String[] args) throws IOException {
		init();
		floydWarshall();
		findResult();
		System.out.println(result);
	}
	
	/**
	 * 결과를 도출하는 메서드.
	 */
	private static void findResult() throws IOException {
		int countOfQuestions = Integer.parseInt(br.readLine());
		int from, to, answer;
		for (int index = 0; index < countOfQuestions; index++) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			
			answer = 0;
			if (dp[from][to] == 1) {
				answer = -1;
			} else if (dp[to][from] == 1) { 
				answer = 1;
			}
			result.append(answer).append('\n');
		}
	}
	
	/**
	 * 플로이드-워셜 알고리즘
	 */
	private static void floydWarshall() {
		for (int mid = 1; mid <= countOfNodes; mid++) {
			for (int from = 1; from <= countOfNodes; from++) {
				for (int to = 1; to <= countOfNodes; to++) {
					if (dp[from][mid] == 1 && dp[mid][to] == 1) {
						dp[from][to] = 1;
					}
				}
			}
		}
	}

	/**
	 * 초기 세팅 메서드.
	 */
	private static void init() throws IOException {
		result = new StringBuilder();
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		countOfNodes = Integer.parseInt(st.nextToken());
		countOfEdges = Integer.parseInt(st.nextToken());
		dp = new int[countOfNodes + 1][countOfNodes + 1];
		
		int from, to;
		for (int index = 0; index < countOfEdges; index++) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			dp[from][to] = 1;
		}
	}
}
