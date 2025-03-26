import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * [조건]
 * 1. N명의 사람이 있고, 1번부터 N번까지 구성된다.
 * 2. 사람의 수 N은 1이상 100이하이다.
 * 3. 관계의 수 M은 0이상 N(N-1)/2 이다.
 * 
 * [풀이]
 * 1. 유니온 파인드를 통해서 집합을 만들어준다.
 * 2. 최종적으로 고유의 부모 개수만 세어서 출력한다.
 */
public class Solution {
	
	private static BufferedReader br;
	private static StringBuilder sb;
	private static StringTokenizer st;
	
	private static int countOfPeople;
	private static int countOfEdges;
	private static int result;
	private static int[] parents;
	
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
	 * 고유 부모 개수를 찾아 저장하는 메서드.
	 */
	private static void findResult() {
		Set<Integer> uniqueParents = new HashSet<>();
		for (int index = 1; index <= countOfPeople; index++) {
			uniqueParents.add(find(index));
		}
		result = uniqueParents.size();
	}
	
	/**
	 * 초기 세팅을 진행하는 메서드. 
	 */
	private static void init() throws IOException {
		result = 0;
		st = new StringTokenizer(br.readLine());
		countOfPeople = Integer.parseInt(st.nextToken());
		parents = new int[countOfPeople + 1];
		for (int index = 1; index <= countOfPeople; index++) {
			parents[index] = index;
		}
		
		countOfEdges = Integer.parseInt(st.nextToken());
		int a, b;
		for (int index = 0; index < countOfEdges; index++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			union(a, b);
		}
	}
	
	private static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if (aRoot != bRoot) {
			if (aRoot > bRoot) {
				parents[aRoot] = bRoot;
			} else {
				parents[bRoot] = aRoot;
			}
		}
	}

	private static int find(int num) {
		if (parents[num] == num) {
			return num;
		}
		return parents[num] = find(parents[num]);
	}
}
