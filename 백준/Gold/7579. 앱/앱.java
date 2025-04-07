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
 */
public class Main {
	
	private static int countOfApps;
	private static int atLeast;
	private static int[][] apps;
	private static int[][] dp;
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
			
			for (int availableCost = 0; availableCost <= sum; availableCost++) {
				if (availableCost < cost) {
					// 1. 현재 사용 가능한 cost로 현재 앱을 비활성화 할 수 없을 때
					// 이전 최적해를 그대로 가져옴
					dp[index][availableCost] = dp[index - 1][availableCost];
				} else {
					// 2. 현재 사용 가능한 cost로 현재 앱을 비활성화 할 수 있을 때
					// 2-1. 현재 앱을 비활성화 하지 않는 경우 vs
					// 2-2. 현재 앱을 비활성화 하는 경우 (현재 확보 가능한 memory + 남은 cost로 얻을 수 있는 최적해)
					dp[index][availableCost] = Math.max(dp[index - 1][availableCost], memory + dp[index - 1][availableCost - cost]);
				}
				
				if (dp[index][availableCost] >= atLeast) {
					// 메모리를 확보한 경우, 최소값으로 갱신
					result = Math.min(result, availableCost);
				}
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
		int curNum;
		for (int index = 1; index <= countOfApps; index++) {
			apps[index][0] = Integer.parseInt(st.nextToken());
		}
		
		sum = 0;
		st = new StringTokenizer(br.readLine());
		for (int index = 1; index <= countOfApps; index++) {
			curNum = Integer.parseInt(st.nextToken());
			sum += curNum;
			apps[index][1] = curNum;
		}
		dp = new int[countOfApps + 1][sum + 1]; // 모든 앱을 꺼야하는 경우까지 고려
	}
}
