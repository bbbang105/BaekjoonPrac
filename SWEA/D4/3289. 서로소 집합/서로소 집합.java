import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [조건]
 * 1. 초기에는 모두 자신만을 가진 집합으로 이루어져 있다.
 * 2. 합집합은 0 a b 의 형태로 입력이 들어온다.
 * 3. 같은 집합에 속해있는지 확인하는 입력은, 1 a b 로 들어온다.
 * 4. 원소의 개수 n은 1이상 1_000_000 이하이고, 연산의 개수 m은 1이상 100_000 이하이다.
 * 5. a와 b는 n 이하의 자연수이다.
 * 
 * [풀이]
 * 1. 합집합 연산이 들어오는 순간마다  union 해준다.
 * 2. 확인하는 입력이 들어오면, 확인한 후 결과 문자열에 합친다.
 * 3. 최종적으로 출력한다.
 */
public class Solution {
	
	private static int[] parents;
	private static int countOfElements;
	private static int countOfCmds;
	private static StringBuilder result;
	
	private static BufferedReader br;
	private static StringBuilder sb;
	private static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		sb = new StringBuilder();
		br = new BufferedReader(new InputStreamReader(System.in));
		int testCaseNum = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= testCaseNum; testCase++) {
			run();
			sb.append("#").append(testCase).append(" ").append(result).append('\n');
		}
		System.out.println(sb);
	}
	
	/**
	 * 초기 세팅을 한 후, 연산을 진행하며 결과를 도출하는 메서드.
	 */
	private static void run() throws IOException {
		result = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		countOfElements = Integer.parseInt(st.nextToken());
		countOfCmds = Integer.parseInt(st.nextToken());
		parents = new int[countOfElements + 1];
		for (int index = 1; index <= countOfElements; index++) {
			parents[index] = index;
		}
				
		int cmd, a, b;
		for (int cmdIndex = 0; cmdIndex < countOfCmds; cmdIndex++) {
			st = new StringTokenizer(br.readLine());
			cmd = Integer.parseInt(st.nextToken());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			switch (cmd) {
			
			case 0:
				union(a, b);
				break;
			case 1:
				if (find(a) == find(b)) {
					result.append(1);
				} else {
					result.append(0);
				}
				break;
			default:
				break;
			}
		}
	}
	
	/**
	 * 두 집합을 합하여 하나의 부모를 가리키게 하는 메서드.
	 */
	private static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if (aRoot != bRoot) {
			// 부모가 다른 경우에만 union
			parents[bRoot] = aRoot;
		}
	}
	
	/**
	 * 부모 원소를 찾는 메서드. 
	 */
	private static int find(int num) {
		if (parents[num] == num) {
			return num;
		}
		return parents[num] = find(parents[num]);
	}
}
