import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * [조건]
 * 1. 암호는 서로 다른 L개의 알파벳 소문자로 구성된다.
 * 2. 또한 최소 1개의 모음과 2개의 자음으로 구성된다.
 * 3. 암호는 알파벳이 정렬된 상태로 나온다.
 * 4. 암호의 길이와 주어지는 문자열들이 있을 때 가능성 있는 암호들을 모두 구해 출력하라.
 * 5. 암호의 길이와 주어지는 문자열의 수는 각각 3이상 15이하이다.
 * 
 * [풀이]
 * 1. 부분집합을 활용한다.
 * 2. 현재 문자열이 모음이라면, 모음 개수를 +1하고 재귀호출한다.
 * 3. 현재 문자열을 선택하는 경우와 선택하지 않는 경우로 나뉜다.
 * 4. 최종적으로 비밀번호 길이 등 조건에 맞다면 스트링 빌더에 넣어준다.
 *
 */
public class Main {
	
	// 입출력 관련
	private static BufferedReader br;
	private static StringTokenizer st;
	private static StringBuilder result;
	// 
	private static int pwdLimit;
	private static int countOfAlphas;
	private static String[] alphas;
	
	public static void main(String[] args) throws IOException {
		init();
		createPwd("", 0, 0);
		System.out.println(result);
	}
	
	/**
	 * 재귀 호출하며 가능한 비밀번호를 만들고 저장하는 메서드.
	 * 
	 */
	private static void createPwd(String pwd, int index, int countOfVowel) {
		if (pwd.length() == pwdLimit) {
			// 기저 조건
			if (countOfVowel >= 1 && pwdLimit - countOfVowel >= 2) {
				// 모음을 최소 1개 && 자음을 최소 2개 포함한 경우에만
				result.append(pwd).append('\n');
			}
			return;
		}
		if (index == countOfAlphas) {
			return;
		}
		
		String cur = alphas[index];
		if (isVowel(cur)) {
			// 1. 현재 선택하는 경우
			// 1-1. 모음인 경우
			createPwd(pwd + cur, index + 1, countOfVowel + 1);
		} else {
			// 1-2. 자음인 경우
			createPwd(pwd + cur, index + 1, countOfVowel);
		}
		// 2. 현재 선택하지 않는 경우
		createPwd(pwd, index + 1, countOfVowel);
	}
	
	/**
	 * 모음인지 여부를 반환하는 메서드.
	 * 
	 */
	private static boolean isVowel(String alpha) {
		return alpha.equals("a") || alpha.equals("e") || alpha.equals("i") || alpha.equals("o") || alpha.equals("u");
	}
	
	/**
	 * 초기 세팅을 진행하는 메서드.
	 * 
	 */
	private static void init() throws IOException {
		result = new StringBuilder();
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		pwdLimit = Integer.parseInt(st.nextToken());
		countOfAlphas = Integer.parseInt(st.nextToken());
		alphas = new String[countOfAlphas];
		st = new StringTokenizer(br.readLine());
		for (int index = 0; index < countOfAlphas; index++) {
			alphas[index] = st.nextToken();
		}
		Arrays.sort(alphas);
	}
}
