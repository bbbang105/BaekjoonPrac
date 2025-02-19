import java.io.*;
import java.util.*;

/**
 * [조건]
 * 1. 자연수 N과 M이 주어진다. 두 수는 1이상 8이하이다.
 * 2. 1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열을 모두 구해 출력하라.
 * 3. 중복되는 수열을 여러번 출력하면 안된다.
 * 4. 각 수열은 공백으로 구분해서 출력하며, 수열은 사전 순으로 증가하는 순서로 출력한다.
 *
 * [풀이]
 * 1. 모든 수열 조합을 구한다.
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
		
		createPermutationsAndSave("");
		
		System.out.println(sbForResult);
	}
	
	/**
	 * 모든 순열 조합을 만들고, 순열 문자열을 스트링빌더에 저장하는 메서드.
	 * 
	 */
	private static void createPermutationsAndSave(String permutation) {
		if (permutation.length() / 2 == lengthLimit) {
			// 순열을 완성한 경우
			sbForResult.append(permutation).append('\n');
			return;
		}
		for (int number = 1; number <= numberLimit; number++) {
			if (!isSelected[number]) {
				isSelected[number] = true;
				createPermutationsAndSave(permutation + number + " ");
				isSelected[number] = false;
			}
		}
	}
}
