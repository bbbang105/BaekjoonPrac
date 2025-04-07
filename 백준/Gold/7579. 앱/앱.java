import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [조건]
 * 1. 현재 활성화되어 있는 앱의 개수 N은 1이상 100이하이다.
 * 2. 확보해야 하는 메모리의 최소값 M은 1이상 10,000,000이하이다.
 * 3. 각 앱에는 비활성화 시 얻을 수 있는 메모리와 비용이 주어진다.
 * 4. M 이상의 메모리를 확보하면서, 구할 수 있는 최소 비용을 출력하라.
 * 
 * [풀이]
 * 1. 냅색 알고리즘을 활용한다.
 * 2. 담을 수 있는 메모리(무게)가 아닌, 비용을 기준으로 고려한다.
 * 3. 중복 선택을 방지하기 위해 뒤에서부터 1차원 dp 배열을 갱신한다.
 * 4. 확보 가능한 메모리가 M 이상인 경우 중 최소 비용을 찾는다.
 */
public class Main {
	
	private static int countOfApps;
	private static int atLeast;
	private static int[][] apps;
	private static int[] dp;
	private static int result;
	private static int sum;
	
	private static BufferedReader br;
	private static StringTokenizer st;
	
	/**
	 * 메인 메서드.
	 */
	public static void main(String[] args) throws IOException {
		init();
		knapsack();
		System.out.println(result);
	}
	
	/**
	 * 냅색 알고리즘 메서드.
	 */
	private static void knapsack() {
		int memory, cost;
		for (int index = 1; index <= countOfApps; index++) {
			memory = apps[index][0];
			cost = apps[index][1];

			// 뒤에서부터 갱신하여 중복 선택 방지
			for (int availableCost = sum; availableCost >= cost; availableCost--) {
				dp[availableCost] = Math.max(dp[availableCost], dp[availableCost - cost] + memory);
			}
		}

		// 확보 가능한 메모리가 M 이상인 경우 중 최소 비용을 찾는다.
		for (int availableCost = 0; availableCost <= sum; availableCost++) {
			if (dp[availableCost] >= atLeast) {
				result = Math.min(result, availableCost);
			}
		}
	}
	
	/**
	 * 초기 세팅 메서드.
	 */
	private static void init() throws IOException {
		result = Integer.MAX_VALUE;
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		countOfApps = Integer.parseInt(st.nextToken());
		atLeast = Integer.parseInt(st.nextToken());
		apps = new int[countOfApps + 1][2];
		
		st = new StringTokenizer(br.readLine());
		for (int index = 1; index <= countOfApps; index++) {
			apps[index][0] = Integer.parseInt(st.nextToken()); // 메모리
		}
		
		sum = 0;
		st = new StringTokenizer(br.readLine());
		for (int index = 1; index <= countOfApps; index++) {
			int curCost = Integer.parseInt(st.nextToken());
			sum += curCost;
			apps[index][1] = curCost;
		}
		dp = new int[sum + 1]; // 비용에 따른 최대 메모리 확보값
	}
}
