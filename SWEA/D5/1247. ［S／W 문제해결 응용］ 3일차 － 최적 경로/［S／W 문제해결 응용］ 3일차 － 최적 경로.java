import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [조건]
 * 1. 회사, 집, 고객의 위치는 이차원 정수 좌표 (x, y)로 주어진다.
 * 2. 각 좌표 값은 0이상 100이하의 정수이다.
 * 3. 위치 사이의 거리는 맨해튼 거리 공식을 사용한다.
 * 4. 회사 -> 모든 고객 방문 -> 집 으로 돌아오는 최단 경로를 찾아야 한다.
 * 5. 고객 수  N은 2이상 10이하의 정수이다.
 *
 * [풀이]
 * 1. 입력을 받으며 회사, 집 위치를 할당해 둔다.
 * 2. 각 고객의 위치는 Location 객체로 만들어 배열에 저장한다.
 * 3. 모든 경우에 대해서 탐색한다. 단, 이미 최소 거리가 되지 못 하는 경우에는 제외한다.
 * 4. 만약 모든 고객을 방문한 경우라면, 최종적으로 해당 위치 -> 집으로 돌아가는 거리까지 합한 후 갱신해준다.
 * 5. 최종적인 최소 이동 거리를 출력한다.
 */
public class Solution {
	
	private static BufferedReader br;
	private static StringTokenizer st;
	private static StringBuilder sb;
	private static int minimumDistance;
	// 회사 좌표
	private static int startX; 
	private static int startY;
	// 집 좌표
	private static int endX;
	private static int endY;
	// 고객 관련 변수
	private static int countOfCustomers;
	private static Location[] customers;
	private static boolean[] isVisited;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		int testCaseNum = Integer.parseInt(br.readLine());
		
		sb = new StringBuilder();
		for (int testCase = 1; testCase <= testCaseNum; testCase++) {
			init();
			findMinimumDistance(new Location(startX, startY), 0, 0); // 현재 좌표, 현재 이동 거리, 현재까지 방문한 고객 수
			sb.append("#").append(testCase).append(" ").append(minimumDistance).append('\n');
		}
		System.out.println(sb);
	}
	
	/**
	 * 최단 경로 이동거리를 찾는 메서드.
	 * 
	 */
	private static void findMinimumDistance(Location curLocation, int curSum, int countOfVisitedCustomers) {
		int curX = curLocation.x;
		int curY = curLocation.y;
		
		if (countOfVisitedCustomers == countOfCustomers) {
			// 모두 방문한 경우, 집으로 돌아가는 거리까지 더해서 비교 후 갱신
			minimumDistance = Math.min(minimumDistance, curSum + Math.abs(endX - curX) + Math.abs(endY - curY));
			return;
		}

		int nextDistance; // 다음 좌표로 이동하는 거리
		for (int nextIndex = 0; nextIndex < countOfCustomers; nextIndex++) {
			if (!isVisited[nextIndex]) {
				nextDistance = Math.abs(customers[nextIndex].x - curX) + Math.abs(customers[nextIndex].y - curY);
				if (curSum + nextDistance > minimumDistance) {
					// 이미 최소 거리가 되지 못 하는 경우
					continue;
				}
				isVisited[nextIndex] = true;
				findMinimumDistance(customers[nextIndex], curSum + nextDistance, countOfVisitedCustomers + 1);
				isVisited[nextIndex] = false;
			}
		}
	}
	
	/**
	 * 초기 세팅을 진행하는 메서드.
	 * 
	 */
	private static void init() throws IOException {
		minimumDistance = Integer.MAX_VALUE;
		countOfCustomers = Integer.parseInt(br.readLine());
		customers = new Location[countOfCustomers];
		isVisited = new boolean[countOfCustomers];
				
		st = new StringTokenizer(br.readLine());
		startX = Integer.parseInt(st.nextToken());
		startY = Integer.parseInt(st.nextToken());
		endX = Integer.parseInt(st.nextToken());
		endY = Integer.parseInt(st.nextToken());
		
		int x, y;
		for (int index = 0; index < countOfCustomers; index++) {
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			customers[index] = new Location(x, y);
		}
	}
	
	/**
	 * 좌표 위치를 저장하는 클래스.
	 *
	 */
	static class Location {
		int x;
		int y;
		
		Location(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
