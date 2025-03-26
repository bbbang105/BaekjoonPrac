import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * [조건]
 * 1. 각 원은 개개인을 의미하고, 숫자는 사람의 번호이다.
 * 2. 숫자는 1부터 100번까지 있다.
 * 3. 연락을 시작하는 당번부터 비상 연락을 시작한다.
 * 4. 방향은 단방향이고, 한 번 퍼질 때 동시에 다 퍼진다.
 * 5. 연락은 1번만 받아도 된다.(방문처리)
 * 6. 마지막에 동시에 연락을 받은 사람들 중, 가장 큰 번호를 출력하라.
 * 
 * [풀이]
 * 1. 연락망 정보를 모두 저장한다.
 * 2. 초기 시작점부터 탐색을 시작하며, 미방문 번호만 추가하고 방문 처리를 한다.
 * 3. 한 번에 퍼지는 사이클마다 최대 값을 구해야 하기 때문에, result를 매번 0으로 갱신해준다.
 */
public class Solution {
	
	private static BufferedReader br;
	private static StringBuilder sb;
	private static StringTokenizer st;
	
	private static Map<Integer, List<Integer>> graph;
	private static boolean[] isVisited;
	private static int countOfEdges;
	private static int startPoint;
	private static int result;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		for (int testCase = 1; testCase <= 10; testCase++) {
			init();
			findResult();
			sb.append("#").append(testCase).append(" ").append(result).append('\n');
		}
		System.out.println(sb);
	}
	
	/**
	 * 결과 값을 찾는 메서드.
	 */
	private static void findResult() throws IOException {
		List<Integer> curNums = new ArrayList<>();
		curNums.add(startPoint);
		List<Integer> nextNums;
		
		while (curNums.size() > 0) {
			nextNums = new ArrayList<>();
			result = 0; // 매번 갱신해줘야 함 -> 마지막에서 가장 큰 수이므로
			
			for (int cur : curNums) {
				result = Math.max(result, cur); // 최대 값 갱신
				if (graph.get(cur) == null) {
					continue;
				}
				for (int next : graph.get(cur)) {
					if (!isVisited[next]) {
						isVisited[next] = true;
						nextNums.add(next);
					}
				}
			}
			curNums = nextNums;
		}
	}
	
	/**
	 * 초기 세팅을 진행하는 메서드.
	 */
	private static void init() throws IOException {
		result = 0;
		graph = new HashMap<>();
		isVisited = new boolean[101];
		st = new StringTokenizer(br.readLine());
		countOfEdges = Integer.parseInt(st.nextToken());
		startPoint = Integer.parseInt(st.nextToken());
		
		int a, b;
		st = new StringTokenizer(br.readLine());
		for (int index = 0; index < countOfEdges / 2; index++) {
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			// 단방향이므로
			graph.putIfAbsent(a, new ArrayList<>());
			graph.get(a).add(b);
		}
	}
}
