import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * [조건]
 * 1. 연산은 아래 3가지가 있다.
 * 	1-1. X가 3으로 나누어 떨어지면, 3으로 나눈다.
 * 	1-2. X가 2로 나누어 떨어지면, 2로 나눈다.
 * 	1-3. 1을 뺀다.
 * 2. 정수 N은 1이상 1_000_000이하이다.
 * 3. 1을 만들기 위한 최소 연산 횟수를 구하라.
 * 
 * [풀이]
 * 1. Top-Down 방식으로 구한다.
 * 2. N부터 시작하고, 만약 현재 숫자에서 최소 횟수를 갱신한 경우에만 탐색용 큐에 추가한다.
 * 3. 큐가 빌때까지 반복한다.
 * 4. 최종적으로 인덱스 1의 값을 출력한다.
 */
public class Main {
	
	private static int start;
	private static int[] dp;

	/**
	 * 메인 메서드.
	 */
	public static void main(String[] args) throws IOException {
		init();
		findResult();
		System.out.println(dp[1]);
	}
	
	/**
	 * DP를 활용해 결과값을 도출하는 메서드.
	 */
	private static void findResult() {
		if (start == 1) {
			// 시작이 1이라면 답이 무조건 0
			dp[1] = 0;
			return;
		}
		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(start, 0));
		
		Node cur;
		int num, countOfAction, nextNum;
		while (!q.isEmpty()) {
			cur = q.poll();
			num = cur.num;
			countOfAction = cur.countOfAction;
			
			// 1. 3으로 나누어 떨어지는 경우
			if (num % 3 == 0) {
				nextNum = num / 3;
				if (dp[nextNum] > countOfAction + 1) {
					dp[nextNum] = countOfAction + 1;
					q.offer(new Node(nextNum, countOfAction + 1));
				}
			}
			// 2. 2로 나누어 떨어지는 경우
			if (num % 2 == 0) {
				nextNum = num / 2;
				if (dp[nextNum] > countOfAction + 1) {
					dp[nextNum] = countOfAction + 1;
					q.offer(new Node(nextNum, countOfAction + 1));
				}
			}
			// 3. -1로는 무조건 이동
			nextNum = num - 1;
			if (nextNum < 1) {
				continue;
			}
			if (dp[nextNum] > countOfAction + 1) {
				dp[nextNum] = countOfAction + 1;
				q.offer(new Node(nextNum, countOfAction + 1));
			}
		}
	}
	
	/**
	 * 초기 세팅 메서드.
	 */
	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		start = Integer.parseInt(br.readLine());
		dp = new int[start + 1];
		Arrays.fill(dp, start + 1); // 최대 횟수로 채워줌
	}
	
	/**
	 * 현재 정보를 담은 클래스.
	 */
	static class Node {
		int num;
		int countOfAction;
		
		Node(int num, int countOfAction) {
			this.num = num;
			this.countOfAction= countOfAction;
		}
	}
}
