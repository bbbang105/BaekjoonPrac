import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * [조건]
 * 1. 모든 섬을 연결해야 한다. (크루스칼 or 프림)
 * 2. 환경 부담금 == 환경 부담 세율(E)와 해저터널 길이(L)의 제곱의 곱만큼 지불 / E * L ^ 2
 * 3. 총 환경 부담금을 최소로 지불, N개의 모든 섬을 연결할 수 있는 교통 시스템을 설계하라.
 * 4. 결과는 소수 첫째 자리에서 반올림하여 정수 형태로 출력하라.
 * 5. 섬의 개수 N은 1이상 1,000이하이다.
 * 6. X, Y 좌표는 0이상 1_000_000이하이다.
 * 7. 환경 부담 세율 실수 E는 0이상 1이하이다.
 *
 * [풀이]
 * 1. 간선이 따로 주어지지 않기 때문에, 모든 섬과 섬을 우선 연결하여 저장한다.
 * 2. 해당 값을 통해서 크루스칼 알고리즘을 적용한다.
 * 
 */
public class Solution {
	
	private static int countOfIsland;
	private static double E;
	private static int[] parents;
	private static int[] rows;
	private static int[] cols;
	private static double result;
	private static List<Island> islands;
	
	private static BufferedReader br;
	private static StringTokenizer st;
	private static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		sb = new StringBuilder();
		br = new BufferedReader(new InputStreamReader(System.in));
		int testCaseNum = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= testCaseNum; testCase++) {
			init();
			findResult();
			sb.append("#").append(testCase).append(" ").append((long) Math.round(result)).append('\n');
		}
		System.out.println(sb);
	}
	
	/**
	 * 결과를 도출하는 메서드.
	 */
	private static void findResult() {
		int from, to, fromParent, toParent;
		double weight;
		for (Island island : islands) {
			from = island.from;
			to = island.to;
			weight = island.weight;
			
			fromParent = find(from);
			toParent = find(to);
			if (fromParent != toParent) {
				union(fromParent, toParent);
				result += E * Math.pow(weight, 2);
			}
		}
	}
	
	/**
	 * 초기 세팅을 진행하는 메서드.
	 */
	private static void init() throws IOException {
		countOfIsland = Integer.parseInt(br.readLine());
		parents = new int[countOfIsland];
		rows = new int[countOfIsland];
		cols = new int[countOfIsland];
		result = 0.0;
		islands = new ArrayList<>();
		
		st = new StringTokenizer(br.readLine());
		for (int colIndex = 0; colIndex < countOfIsland; colIndex++) {
			cols[colIndex] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int rowIndex = 0; rowIndex < countOfIsland; rowIndex++) {
			rows[rowIndex] = Integer.parseInt(st.nextToken());
		}
		
		E = Double.parseDouble(br.readLine());
		
		for (int index = 0; index < parents.length; index++) {
			parents[index] = index;
		}
		
		double weight;
		for (int from = 0; from < countOfIsland - 1; from++) {
			for (int to = from + 1; to < countOfIsland; to++) {
				weight = calculateEuclideanDistance(rows[from], cols[from], rows[to], cols[to]);
				islands.add(new Island(from, to, weight));
			}
		}
		Collections.sort(islands);
	}
	
	/**
	 * 유클리디안 거리를 계산하여 반환하는 메서드.
	 */
	private static double calculateEuclideanDistance(int x1, int y1, int x2, int y2) {
	    return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
	}
	
	private static void union(int aRoot, int bRoot) {
		parents[bRoot] = aRoot; 
	}
	
	private static int find(int num) {
		if (parents[num] == num) {
			return num;
		}
		return parents[num] = find(parents[num]);
	}

	static class Island implements Comparable<Island> {
		int from;
		int to;
		double weight;
		
		Island(int from, int to, double weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Island o) {
			return Double.compare(this.weight, o.weight);
		}
	}
}
