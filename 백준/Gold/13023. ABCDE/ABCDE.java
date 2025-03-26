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
 * 1. 사람의 수 N은 5이상 2,000이하이다.
 * 2. 친구 관계의 수 M은 1이상 2,000이하이다.
 * 3. 주어지는 정수  a와 b는 둘이 친구라는 뜻이며, 같은 친구 관계는 1번만 주어진다.(양방향)
 * 
 * [풀이]
 * 1. 친구 관계가 A->B->C->D->E 가 있는지를 찾기 위해서 DFS를 활용한다.
 * 2. 5명이 채워진다면 즉시 리턴하여 종료한다.
 */
public class Main {
	
	private static BufferedReader br;
	private static StringBuilder sb;
	private static StringTokenizer st;
	
	private static Map<Integer, List<Integer>> graph;
	private static boolean[] isVisited;
	private static int countOfPeople;
	private static int countOfEdges;
	private static int result;
	
	public static void main(String[] args) throws IOException {
		init();
		
		for (int index = 0; index < countOfPeople; index++) {
			isVisited[index] = true;
			findABCDE(index, 1);
			if (result == 1) {
				break;
			}
			isVisited[index] = false;
		}
		System.out.println(result);
	}
	
	/**
	 * ABCDE 관계가 있는지 여부를 반환하는 메서드.
	 */
	private static void findABCDE(int cur, int depth) {
		if (depth == 5) {
			result = 1;
			return;
		}
		
		for (int next : graph.get(cur)) {
			if (!isVisited[next]) {
				isVisited[next] = true;
				findABCDE(next, depth + 1);
				isVisited[next] = false;
			}
		}
	}
	
	/**
	 * 초기 세팅을 진행하는 메서드.
	 */
	private static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		countOfPeople = Integer.parseInt(st.nextToken());
		countOfEdges = Integer.parseInt(st.nextToken());
		graph = new HashMap<>();
		isVisited = new boolean[countOfPeople];
		result = 0;
		
		for (int index = 0; index < countOfPeople; index++) {
			graph.put(index, new ArrayList<>());
		}
		
		int a, b;
		for (int index = 0; index < countOfEdges; index++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			graph.get(a).add(b);
			graph.get(b).add(a);
		}
	}
}
