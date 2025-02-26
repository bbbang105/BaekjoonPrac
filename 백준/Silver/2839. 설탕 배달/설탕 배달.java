import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * [조건]
 * 1. N은 3이상 5,000이하이다.
 * 2. 봉지는 3, 5 킬로그램 두 종류이다.
 * 3. 정확히 N킬로그램을 맞추는 최소 봉지 개수를 구하라.
 * 
 * [풀이]
 * 1. 우선 5킬로그램으로 최대 몇 개 가져갈 수 있는지 값을 구한다.
 * 2. 나머지 값이 3으로 나누어떨어진다면, 나눈다.
 * 3. 만약 나눠지지 않는다면, 5킬로그램 봉지 개수를 하나 빼고 5를 더한다.
 * 4. 2 3 번을 반복하고, 만약 최종적으로 3킬로그램만으로도 구성이 불가하다면 -1을 출력한다.
 *
 */
public class Main {
	
	private static final int FIVE_KILLO_BAG = 5;
	private static final int THREE_KILLO_BAG = 3;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int result = -1;
		int fiveKilloBagsCount = N / 5; // 우선 최대 개수를 구함
		while (fiveKilloBagsCount >= 0) {
			if ((N - fiveKilloBagsCount * FIVE_KILLO_BAG) % THREE_KILLO_BAG == 0) {
				// 정확히 N킬로그램을 맞출 수 있는 경우
				int threeBagsCount = (N - fiveKilloBagsCount * FIVE_KILLO_BAG) / THREE_KILLO_BAG;
				result = fiveKilloBagsCount + threeBagsCount;
				break;
			}
			fiveKilloBagsCount--;
		}
		
		System.out.println(result);
	}
}
