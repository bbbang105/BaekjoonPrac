import java.io.*;
import java.util.*;
import java.util.stream.*;

/**
 * 
 * [조건]
 * 1. 카드는 정수 1부터 18까지 총 18장이고, 각각 9장씩 나눠 가진다. 겹치는 숫자는 없다.
 * 2. 9라운드로 진행되며, 카드를 순서대로 1장씩 낸다.
 * 3. 높은 숫자의 사람이 승리하며, 승리 시에는 두 카드의 합을 점수로 가진다. 패배 시에는 아무런 점수도 가질 수 없다.
 * 4. 9라운드가 끝나고 총점을 비교해서, 점수가 더 큰 경우에만 승리한다.
 * 5. 총점이 동일하다면 무승부이다.
 * 6. 규영이의 카드는 입력 받은 순서대로 고정시킨다.
 * 7. 인영이가 낼 수 있는 9!가지 경우에 대해서, 규영이가 게임을 이기는 경우와 지는 경우의 수를 출력하라.
 *
 * [풀이]
 * 1. 남은 9개의 카드로 모든 순열 조합을 만든다.
 * 2. 재귀로 카드를 하나씩 선택하며, 9개가 되는 순간 규영이와 비교하여 승무패를 결정한다.
 */
public class Solution {
	
	private static final int PER_CARD_LIMIT = 9;
	
	private static int[] kyCards = new int[PER_CARD_LIMIT]; // 규영이의 카드 목록
	private static int[] iyCards = new int[PER_CARD_LIMIT]; // 인영이의 카드 목록
	private static int[] iySelectedCards = new int[PER_CARD_LIMIT]; // 인영이가 선택한 카드 목록
	private static boolean[] isSelected = new boolean[PER_CARD_LIMIT]; // 방문 처리용
	private static int countOfKyWin; // 규영이가 이기는 횟수
	private static int countOfKyLose; // 규영이가 지는 횟수
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int countOftestCase = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		for (int testCaseNum = 1; testCaseNum <= countOftestCase; testCaseNum++) {
			st = new StringTokenizer(br.readLine());
			
			List<Integer> nums = IntStream.rangeClosed(1, 18)
                    .boxed()
                    .collect(Collectors.toList());
			
			for (int cardIndex = 0; cardIndex < 9; cardIndex++) {
				int cardNum = Integer.parseInt(st.nextToken());
				kyCards[cardIndex] = cardNum;
				nums.remove(Integer.valueOf(cardNum));
			}
			for (int cardIndex = 0; cardIndex < 9; cardIndex++) {
				iyCards[cardIndex] = nums.get(cardIndex);
			}
			
			countOfKyWin = 0;
			countOfKyLose = 0;
			
			createPermutationsAndJudgeWinner(0);
			
			String result = countOfKyWin + " " + countOfKyLose;
			sb.append("#").append(testCaseNum).append(" ").append(result).append('\n');
		}
		System.out.print(sb);
	}
	
	/**
	 * 모든 순열 조합을 만들고, 승무패를 판단하는 메서드.
	 * 
	 */
	private static void createPermutationsAndJudgeWinner(int selectIndex) {
		if (selectIndex == PER_CARD_LIMIT) {
			// 9개 조합을 완성한 경우
			judgeWinner();
			return;
		}
		for (int cardIndex = 0; cardIndex < PER_CARD_LIMIT; cardIndex++) {
			if (!isSelected[cardIndex]) {
				iySelectedCards[selectIndex] = iyCards[cardIndex];
				isSelected[cardIndex] = true;
				createPermutationsAndJudgeWinner(selectIndex + 1);
				isSelected[cardIndex] = false;
			}
		}
	}
	
	/**
	 * 승무패를 판단하여 승 or 패의 카운트를 더하는 메서드.
	 * 
	 */
	private static void judgeWinner() {
		int kySumOfScore = 0;
		int iySumOfScore = 0;
		
		for (int cardIndex = 0; cardIndex < PER_CARD_LIMIT; cardIndex++) {
			int kyCardNum = kyCards[cardIndex];
			int iyCardNum = iySelectedCards[cardIndex];
			int sum = kyCardNum + iyCardNum;
			
			if (kyCardNum > iyCardNum) {
				kySumOfScore += sum;
			} else {
				iySumOfScore += sum;
			}
		}
		
		if (kySumOfScore > iySumOfScore) {
			// 1. 규영이 승리한 경우
			countOfKyWin++;
		} else if (kySumOfScore < iySumOfScore) {
			// 2. 규영이 패배한 경우
			countOfKyLose++;
		} else {
			// 3. 무승부인 경우 아무 일도 X
			return;
		}
	}
}
