import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * [조건]
 * 1. 모든 컴퓨터를 연결하고, 연결하는 비용을 최소로 하라. (=크루스칼 or 프림)
 * 2. 컴퓨터의 수 N은 1이상 1_000이하이다.
 * 3. 연결 선의 수 M은 1이상 100_000이하이다.
 * 4. 비용 c는 1이상 10_000이하이다.
 * 5. a와 b가 동일한 경우도 존재한다. (자기 자신)
 * 
 * [풀이]
 * 1. 크루스칼 알고리즘을 적용하여 최소 비용을 계산한다.
 */
public class Main {
	
	private static int countOfComputers;
	private static int countOfEdges;
	private static int[] parents;
	private static int[][] computers;
	private static int result;
	
	private static BufferedReader br;
	private static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		init();
		findResult();
		System.out.println(result);
	}
	
	/**
	 * 결과를 도출하는 메서드.
	 */
	private static void findResult() {
		int from, to, weight, fromParent, toParent;
		for (int[] computer : computers) {
			from = computer[0];
			to = computer[1];
			weight = computer[2];
			
			fromParent = find(from);
			toParent = find(to);
			if (fromParent != toParent) {
				union(fromParent, toParent);
				result += weight;
			}
		}
	}
 	
	/**
	 * 초기 세팅을 진행하는 메서드.
	 */
	private static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		countOfComputers = Integer.parseInt(br.readLine());
		countOfEdges = Integer.parseInt(br.readLine());
		parents = new int[countOfComputers + 1];
		for (int index = 0; index < countOfComputers; index++) {
			parents[index] = index;
		}
		computers = new int[countOfEdges][3]; // from, to, weight
		
		for (int index = 0; index < countOfEdges; index++) {
			st = new StringTokenizer(br.readLine());
			computers[index][0] = Integer.parseInt(st.nextToken());
			computers[index][1] = Integer.parseInt(st.nextToken());
			computers[index][2] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(computers, (o1, o2) -> o1[2] - o2[2]);
	}
	
	private static void union(int aParent, int bParent) {
		parents[bParent] = aParent;
	}
	
	private static int find(int num) {
		if (parents[num] == num) {
			return num;
		}
		return parents[num] = find(parents[num]);
	}
}
