import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [조건]
 * 1. 자연수 N과 M이 주어진다. 두 수는 1이상 8이하이다.
 * 2. 1부터 N까지 자연수 중에서 중복 없이 M개를 고른다.(중복 X)
 * 3. 고른 수열은 오름차순이어야 한다.(순서 고정 == 순서에 의미가  X == '조합')
 * 4. 중복되는 수열을 여러번 출력하면 안된다.
 * 5. 각 수열은 공백으로 구분해서 출력하며, 수열은 사전 순으로 증가하는 순서로 출력한다.
 *
 * [풀이]
 * 1. 모든 조합을 구한다.
 * 2. 최적화를 위해 문자열을 파라미터로 넘긴 후, 사이즈가 맞으면 추가한다.
 */
public class Main {
	
	private static boolean[] isSelected; // 방문 처리용
	private static StringBuilder sbForResult = new StringBuilder(); // 최종적으로 출력한 스트링빌더
	private static int numberLimit;
	private static int lengthLimit;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		numberLimit = Integer.parseInt(st.nextToken());
		lengthLimit = Integer.parseInt(st.nextToken());
		
		isSelected = new boolean[numberLimit + 1];
		
		createCombinationsAndSave("", 1);
		
		System.out.print(sbForResult);
	}
	
	/**
	 * 모든 조합을 만들고, 순열 문자열을 스트링빌더에 저장하는 메서드.
	 * 
	 */
	private static void createCombinationsAndSave(String combination, int startNumber) {
		if (combination.length() / 2 == lengthLimit) {
			// 순열을 완성한 경우
			sbForResult.append(combination).append('\n');
			return;
		}
		for (int number = startNumber; number <= numberLimit; number++) {
			createCombinationsAndSave(combination + number + " ", number + 1);
		}
	}
}