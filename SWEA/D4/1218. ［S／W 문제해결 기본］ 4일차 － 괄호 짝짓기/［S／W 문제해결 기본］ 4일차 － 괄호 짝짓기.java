import java.io.*;
import java.util.*;

/**
 * [조건]
 * 1. 괄호문자는 총 4종류이다.
 * 2. 테스트케이스는 10개로 고정이다.
 * 3. 입력은 테스트케이스 길이와 문자열이 줄바꿈으로 주어진다.
 * 4. 문자열에 있는 괄호들의 짝이 모두 맞는지 판별해야 한다.
 * 5. 짝이 맞다면 1, 짝이 맞지 않다면 0을 출력한다.
 * 
 * [풀이]
 * 1. 전형적인 스택 문제로 스택을 활용한다.
 * 2. 문자열을 하나씩 탐색하며, 여는 괄호라면 스택에 넣는다.
 * 3. 닫는 괄호라면 스택의 peek를 확인해서 동일한 문자라면 pop한다.
 * 4. 만약, 다른 문자라면 짝을 맞출 수 없기 때문에 바로 false를 리턴한다.
 * 5. 끝까지 탐색을 한 경우, 스택의 사이즈가 0이 아니라면 이 또한 false를 리턴한다. (여는 괄호가 더 많을 수 있기 때문)
 * 6. true를 받았다면 1, false를 받았다면 0을 스트링빌더에 삽입한다.
 *
 */
public class Solution {
	
	private static Stack<Character> stack;
	private static BufferedReader br;
	private static int length;
	private static String str;
	private static int result;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder sb = new StringBuilder();
		for (int testCaseNum = 1; testCaseNum <= 10; testCaseNum++) {
			init();
			
			if (isValidString()) {
				result = 1;
			} else {
				result = 0;
			}
			sb.append("#").append(testCaseNum).append(" ").append(result).append('\n');
		}
		System.out.print(sb);
	}
	
	/**
	 * 입력값을 받아 할당하는 메서드.
	 * 
	 */
	private static void init() throws IOException {
		length = Integer.parseInt(br.readLine());
		str = br.readLine();
		stack = new Stack<>();
	}
	
	/**
	 * 유효한 문자열인지 여부를 반환하는 메서드.
	 * 
	 */
	private static boolean isValidString() {
		for (int index = 0; index < length; index++) {
			char curParentheses = str.charAt(index);
			
			if (isOpenParentheses(curParentheses)) {
				// 1. 여는 괄호인 경우, 스택에 삽입
				stack.push(curParentheses);
			} else {
				// 2. 닫는 괄호인 경우, 짝을 맞춰 봄
				if (stack.size() == 0) {
					// 2-1. 빈 스택인 경우 (= 처음부터 닫는 괄호가 들어온 경우), false 리턴
					return false;
				} 
				if (!isMatch(stack.peek(), curParentheses)) {
					// 2-2. 짝이 맞지 않는 경우, false 리턴
					return false;
				} 
				// 2-3. 짝이 맞으므로, pop()
				stack.pop();
			}
		}
		return stack.size() == 0; // 최종적인 사이즈가 0이 아니라면 false
	}
	
	/**
	 * 여는 괄호인지 여부를 반환하는 메서드.
	 * 
	 */
	private static boolean isOpenParentheses(char parentheses) {
		return parentheses == '(' || parentheses == '[' || parentheses == '{' || parentheses == '<';
	}
	
	/**
	 * 맞는 짝인지의 여부를 반환하는 메서드.
	 * 
	 */
	private static boolean isMatch(char openedParentheses, char closedParentheses) {
		return (openedParentheses == '(' && closedParentheses == ')') 
				|| (openedParentheses == '[' && closedParentheses == ']') 
				|| (openedParentheses == '{' && closedParentheses == '}') 
				|| (openedParentheses == '<' && closedParentheses == '>')
				;
	}
}
