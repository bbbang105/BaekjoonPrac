import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * [조건]
 * 1. 벌통들의 크기 N은 3이상 10 이하의 정수이다.
 * 2. 선택할 수 있는 벌통 개수. M은 1이상 5 이하의 정수이다. 이는 N보다 작게 나온다.
 * 3. 채취할 수 있는 최대 양 C는 10이상 30 이하의 정수이다.
 * 4. 하나의 벌통에서 채취할 수 있는 꿀의 양은 1이상 9 이하의 정수이다.
 * 
 * [풀이]
 * 1. 두명이기 때문에, 우선 한 명이 채취할 수 있는 최대 벌꿀을 리스트에 저장해 둔다.
 * 2. 이는 모든 그래프를 탐색하며, (시작 인덱스, 종료 인덱스, 벌꿀 양) 을 저장한다.
 * 3. 벌꿀 양을 기준으로 내림차순 정렬한다.
 * 4. 나머지 한 명이 탐색을 하며, 해당 리스트에서 가능한 가장 높은 벌꿀 양을 선택한다.
 * 5. 여기서 가능한 것은, 겹치지 않아야 한다는 것이다.
 * 6. 최종적으로 두 벌꿀 양의 합산을 출력한다.
 */
public class Solution {

	private static int result;
	private static int[][] graph;
	private static int size;
	private static int limitOfSelection;
	private static int limitOfAmount;
	private static int maxIntervalSum;
	private static List<Integer> honeys;
	private static List<HoneySum> honeySums;

	private static BufferedReader br;
	private static StringTokenizer st;
	private static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		int testCaseNum = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= testCaseNum; testCase++) {
			init();
			createOneHoneySums();
			findResult();
			sb.append("#").append(testCase).append(" ").append(result).append('\n');
		}
		System.out.println(sb);
	}

	/**
	 * 결과를 도출하는 메서드.
	 */
	private static void findResult() {
		result = 0;
		HoneySum firstHoneySum, secondHoneySum;
		for (int first = 0; first < honeySums.size() - 1; first++) {
			for (int second = first + 1; second < honeySums.size(); second++) {
				firstHoneySum = honeySums.get(first);
				secondHoneySum = honeySums.get(second);
				if (firstHoneySum.rowIndex == secondHoneySum.rowIndex) {
					if (!(firstHoneySum.colEndIndex < secondHoneySum.colStartIndex
							|| secondHoneySum.colEndIndex < firstHoneySum.colStartIndex)) {
						continue;
					}
				}
				result = Math.max(result, firstHoneySum.sum + secondHoneySum.sum);
			}
		}
	}

	/**
	 * 먼저 한 명의 벌꿀 합들을 계산해 리스트에 저장하는 메서드.
	 */
	private static void createOneHoneySums() {
		for (int rowIndex = 0; rowIndex < size; rowIndex++) {
			for (int colIndex = 0; colIndex <= size - limitOfSelection; colIndex++) {
				honeys = new ArrayList<>();
				maxIntervalSum = 0;
				for (int move = 0; move < limitOfSelection; move++) {
					honeys.add(graph[rowIndex][colIndex + move]);
				}
				findMaxIntervalSum(0, 0, 0);
				honeySums.add(new HoneySum(rowIndex, colIndex, colIndex + limitOfSelection - 1, maxIntervalSum));
			}
		}
	}

	/**
	 * 선택한 벌꿀들 중에서 가능한 부분 집합들 중 최대 제곱합을 계산하는 메서드.
	 */
	private static void findMaxIntervalSum(int index, int curSum, int curPowSum) {
		if (curSum > limitOfAmount) return;
		maxIntervalSum = Math.max(maxIntervalSum, curPowSum);
		if (index == honeys.size()) return;

		// 1. 현재 선택
		findMaxIntervalSum(index + 1, curSum + honeys.get(index), curPowSum + honeys.get(index) * honeys.get(index));
		// 2. 현재 미선택
		findMaxIntervalSum(index + 1, curSum, curPowSum);
	}

	/**
	 * 초기 세팅을 진행하는 메서드.
	 */
	private static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		size = Integer.parseInt(st.nextToken());
		limitOfSelection = Integer.parseInt(st.nextToken());
		limitOfAmount = Integer.parseInt(st.nextToken());

		graph = new int[size][size];
		for (int rowIndex = 0; rowIndex < size; rowIndex++) {
			st = new StringTokenizer(br.readLine());
			for (int colIndex = 0; colIndex < size; colIndex++) {
				graph[rowIndex][colIndex] = Integer.parseInt(st.nextToken());
			}
		}

		result = 0;
		honeySums = new ArrayList<>();
	}

	/**
	 * 가능한 벌꿀 합을 저장하는 클래스.
	 */
	static class HoneySum {
		int rowIndex;
		int colStartIndex;
		int colEndIndex;
		int sum;

		HoneySum(int rowIndex, int colStartIndex, int colEndIndex, int sum) {
			this.rowIndex = rowIndex;
			this.colStartIndex = colStartIndex;
			this.colEndIndex = colEndIndex;
			this.sum = sum;
		}
	}
}
