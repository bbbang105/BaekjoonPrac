import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [조건]
 * 1. 집의 수는 2이상 1,000이하이다.
 * 2. 집은 빨강, 초록, 파랑 중 하나로 칠해야 한다.
 * 3. N번 집과 N-1번 집의 색은 달라야 한다.
 * 4. 비용은 1,000보다 이하의 자연수이다.
 * 5. 모든 집을 칠하는 비용의 최솟값을 구하라.
 * 
 * [풀이]
 * 1. DP를 활용한다.
 * 2. 1번 인덱스부터 시작해서 이전 배열을 보고, 가능한 색상 중 최소값을 택한다.
 * 3. 최종적으로 마지막 인덱스에서 최소값을 출력한다.
 */
public class Main {
	
	private static int result;
	private static int[][] colors;
	private static int countOfHouses;
	
	/**
	 * 메인 메서드.
	 */
	public static void main(String[] args) throws IOException {
		init();
		findResult();
		System.out.println(result);
	}
	
	/**
	 * DP를 활용하여 결과를 도출하는 메서드.
	 */
	private static void findResult() {
		for (int index = 1; index < countOfHouses; index++) {
			colors[index][0] += Math.min(colors[index - 1][1], colors[index - 1][2]); // R
			colors[index][1] += Math.min(colors[index - 1][0], colors[index - 1][2]); // G
			colors[index][2] += Math.min(colors[index - 1][0], colors[index - 1][1]); // B
		}
		
		for (int lastNum : colors[countOfHouses - 1]) {
			result = Math.min(result, lastNum); // 최소값으로 갱신
		}
	}

	/**
	 * 초기 세팅을 진행하는 메서드.
	 */
	private static void init() throws IOException {
		result = 1_000_001;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		countOfHouses = Integer.parseInt(br.readLine());
		colors = new int[countOfHouses][3]; // 색상이 3개이므로
		
		StringTokenizer st;
		for (int index = 0; index < countOfHouses; index++) {
			st = new StringTokenizer(br.readLine());
			colors[index][0] = Integer.parseInt(st.nextToken());
			colors[index][1] = Integer.parseInt(st.nextToken());
			colors[index][2] = Integer.parseInt(st.nextToken());
		}
	}
}
