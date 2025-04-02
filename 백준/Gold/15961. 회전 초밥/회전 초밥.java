import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * [조건]
 * 1. 접시의 수 N은 2이상 3,000,000이하이다.
 * 2. 초밥의 가짓수 d와 연속해서 먹는 접시의 수 k는 2이상 3,000이하이다.
 * 3. 먹을 수 있는 초밥 가짓수의 최대값을 구하라.
 * 
 * [풀이]
 * 1. 슬라이딩 윈도우를 활용한다.
 * 2. 길이를 k로 지정하여 값을 업데이트한다.
 * 3. k는 N이하이므로 무조건 연속으로 먹어 이벤트에 참여할 수 있다.
 * 	3-1. 즉, 쿠폰 번호의 초밥은 무조건 먹을 수 있으므로 포함해준다.
 * 4. 접시 하나씩 이동하며, 최대 가짓수를 갱신한다.
 */
public class Main {

	private static int result;
	private static int curMaxcountOfSushis;
	private static int countOfPlates;		// 접시 개수
	private static int countOfSushis;		// 초밥 가짓수
	private static int countOfContinuous;	// 연속해서 먹는 접시의 수
	private static int numberOfCoupon;		// 쿠폰 번호
	private static Map<Integer, Integer> curSushiCounts; // 현재 먹을 수 있는 초밥의 종류와 개수
	private static int[] sushis; // 초밥 배열

	/**
	 * 메인 메서드.
	 */
	public static void main(String[] args) throws IOException {
		init();
		findMaxCountOfSushi();
		System.out.println(result);
	}

	/**
	 * 최대 가짓수를 구하는 메서드.
	 */
	private static void findMaxCountOfSushi() {
		for (int startIndex = 1; startIndex < countOfPlates; startIndex++) {
			result = Math.max(result, curMaxcountOfSushis); // 최대값 갱신

			int endIndex = (startIndex + countOfContinuous - 1) % countOfPlates;
			int removedSushi = sushis[startIndex - 1];
			int addedSushi = sushis[endIndex];

			// 1. 가장 앞에 있던 초밥 빼기
			int removedCount = curSushiCounts.get(removedSushi) - 1;
			curSushiCounts.put(removedSushi, removedCount);
			if (removedCount == 0) {
				curSushiCounts.remove(removedSushi);
				curMaxcountOfSushis--;
			}

			// 2. 가장 뒤에 있는 초밥 추가
			int addedCount = curSushiCounts.getOrDefault(addedSushi, 0);
			if (addedCount == 0) curMaxcountOfSushis++;
			curSushiCounts.put(addedSushi, addedCount + 1);
		}

		// 마지막 윈도우도 체크
		result = Math.max(result, curMaxcountOfSushis);
	}

	/**
	 * 초기 세팅을 진행하는 메서드.
	 */
	private static void init() throws IOException {
		result = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		countOfPlates = Integer.parseInt(st.nextToken());
		countOfSushis = Integer.parseInt(st.nextToken());
		countOfContinuous = Integer.parseInt(st.nextToken());
		numberOfCoupon = Integer.parseInt(st.nextToken());
		curSushiCounts = new HashMap<>();

		sushis = new int[countOfPlates];
		for (int index = 0; index < countOfPlates; index++) {
			sushis[index] = Integer.parseInt(br.readLine());
		}

		// 초기 슬라이딩 윈도우 세팅
		for (int i = 0; i < countOfContinuous; i++) {
			int sushi = sushis[i];
			curSushiCounts.put(sushi, curSushiCounts.getOrDefault(sushi, 0) + 1);
		}

		// 쿠폰 번호 초밥은 무조건 넣어줌
		if (!curSushiCounts.containsKey(numberOfCoupon)) {
			curSushiCounts.put(numberOfCoupon, 1);
		} else {
			curSushiCounts.put(numberOfCoupon, curSushiCounts.get(numberOfCoupon) + 1);
		}

		curMaxcountOfSushis = curSushiCounts.size();
	}
}
