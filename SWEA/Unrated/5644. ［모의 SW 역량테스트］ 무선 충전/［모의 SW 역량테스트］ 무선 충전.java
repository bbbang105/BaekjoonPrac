import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/** 
 * [조건]
 * 1. 지도의 가로 세로 크기는 10으로 고정이다.
 * 2. 사용자는 2명이고, A사용자는 가장 좌측상단에서, B사용자는 가장 우측하단에서 시작한다.
 * 3. 총 이동 시간 M은 20이상 100이하의 정수이다.
 * 4. BC의 개수 A는 1이상 8이하의 정수이다.
 * 5. BC의 성능  P는 10이상 500이하의 짝수이다. (2로 나눌 수 있어야 하기 때문)
 * 6. 사용자의 초기 위치 0초부터 충전이 가능하다! (시작부터 고려해야 함)
 * 7. 같은 위치에 2개 이상의  BC가 설치되지는 않는다. 허나 두 사용자가 같은 위치로 이동할 수는 있다.
 * 8. 범위 내에 있는  BC 중에서 선택해서 충전할 수 있다. 만약 1개의  BC에 두 사용자가 접속하면 2로 나눠서 충전한다.
 * 9. 모든 사용자의 충전량 합의 최대 값을 구하라.
 * 10. * 주어지는 BC의 좌표는 (x, y)기준이다. 즉, 행렬을 반대로 생각해야 한다!!
 *
 */
public class Solution {
	// {이동X, 상, 우, 하, 좌}
	private static final int[] DX = {0, -1, 0, 1, 0};
	private static final int[] DY = {0, 0, 1, 0, -1};
	private static final int SIZE = 10; // 10으로 고정
	
	private static BufferedReader br;
	private static StringTokenizer st;
	private static StringBuilder sb;
	// 사용자 이동 정보
	private static int[][] moveInfosOfplayer;
	private static int XofPlayerA, YofPlayerA;
	private static int XofPlayerB, YofPlayerB;
	// 이동시간, BC의 개수
	private static int timeOfMoving;
	private static int countOfBCs;
	// BC 정보 배열
	private static BC[] bcs;
	// 충전량 합의 최대 값
	private static int maximumSum;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		int testCaseNum = Integer.parseInt(br.readLine());
		
		sb = new StringBuilder();
		for (int testCase = 1; testCase <= testCaseNum; testCase++) {
			init();
			findMaximumSum();
			sb.append("#").append(testCase).append(" ").append(maximumSum).append('\n');
		}
		System.out.println(sb);
	}
	
	/**
	 * 모든 사용자의 충전량 합의 최대 값을 구하는 메서드.
	 * 
	 */
	private static void findMaximumSum() {
		int curTime = 0; // 0부터 충전이 가능하기 때문에 고려함
		List<ChargingInfo> possibleBCsOfA;
		List<ChargingInfo> possibleBCsOfB;
		
		while (true) {
			// 1. 현재 위치에서 접속 시도
			// 현재  A 플레이어 위치에서 접속 가능한  BC들
			possibleBCsOfA = new ArrayList<>();
			findPossibleBCs(possibleBCsOfA, XofPlayerA, YofPlayerA);
			// 현재  B 플레이어 위치에서 접속 가능한  BC들
			possibleBCsOfB = new ArrayList<>();
			findPossibleBCs(possibleBCsOfB, XofPlayerB, YofPlayerB);
			maximumSum += calculateMaxAmount(possibleBCsOfA, possibleBCsOfB);
			
			if (curTime == timeOfMoving) {
				// 모두 이동했으므로 종료
				break;
			}
			
			// 2. 주어진 이동 정보대로 이동
			movePlayer(0, curTime); // A 플레이어 이동 (인덱스 0)
			movePlayer(1, curTime); // B 플레이어 이동 (인덱스 1)
			
			curTime++;
		}
	}
	
	/**
	 * 접속 가능한 BC의 충전량을 저장하여 반환하는 메서드.
	 * 
	 */
	private static void findPossibleBCs(List<ChargingInfo> possibleBCs, int x, int y) {
		int xOfBC, yOfBC, range, amountOfCharging;
		for (int bcIndex = 0; bcIndex < countOfBCs; bcIndex++) {
			xOfBC = bcs[bcIndex].x;
			yOfBC = bcs[bcIndex].y;
			range = bcs[bcIndex].range;
			amountOfCharging = bcs[bcIndex].amountOfCharging;
			
			if (isPossibleToConnect(x, xOfBC, y, yOfBC, range)) {
				// 접속 가능한 경우
				possibleBCs.add(new ChargingInfo(bcIndex, amountOfCharging));
			}
		}
	}
	
	/**
	 * 맨해튼 거리로 계산한 값이 range 내에 들어오는지 여부를 반환하는 메서드.
	 * 
	 */
	private static boolean isPossibleToConnect(int x1, int x2, int y1, int y2, int range) {
		return range >= Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}
	
	/**
	 * A와  B의 접속 가능한 BC들 중에서 최대의 합을 가지는 조합을 구하는 메서드.
	 * 
	 */
	private static int calculateMaxAmount(List<ChargingInfo> possibleBCsOfA, List<ChargingInfo> possibleBCsOfB) {
		Collections.sort(possibleBCsOfA);
		Collections.sort(possibleBCsOfB);
		
		if (possibleBCsOfA.size() == 0 && possibleBCsOfB.size() == 0) {
			return 0;
		} else if (possibleBCsOfA.size() == 0) {
			return possibleBCsOfB.get(0).amountOfCharging;
		} else if (possibleBCsOfB.size() == 0) {
			return possibleBCsOfA.get(0).amountOfCharging;
		}
		
		int maxAmount = 0;
		int curSum;
		for (int aIndex = 0; aIndex < possibleBCsOfA.size(); aIndex++) {
			for (int bIndex = 0; bIndex < possibleBCsOfB.size(); bIndex++) {
				ChargingInfo aInfo = possibleBCsOfA.get(aIndex);
				ChargingInfo bInfo = possibleBCsOfB.get(bIndex);
				if (aInfo.bcIndex == bInfo.bcIndex) {
					// 같은 BC인 경우 나눠 가짐
					curSum = aInfo.amountOfCharging; // 함께 가져가는 것이므로 나누기 2 할 필요 X
				} else {
					// 같은 BC인 경우 둘이 더함
					curSum = aInfo.amountOfCharging + bInfo.amountOfCharging;
				}
				maxAmount = Math.max(maxAmount, curSum);
			}
		}
		return maxAmount;
	}
	
	/**
	 * 정해진 이동 정보에 따라 플레이어를 이동시키는 메서드. 
	 *
	 */
	private static void movePlayer(int playerIndex, int curTime) {
		int directionIndex;
		int nx, ny;
		
		if (playerIndex == 0) {
			// A 플레이어 이동
			directionIndex = moveInfosOfplayer[curTime][playerIndex];
			nx = XofPlayerA + DX[directionIndex];
			ny = YofPlayerA + DY[directionIndex];
			if (isPossibleToMove(nx, ny)) {
				XofPlayerA = nx;
				YofPlayerA = ny;
			}
		} else if (playerIndex == 1) {
			// B 플레이어 이동
			directionIndex = moveInfosOfplayer[curTime][playerIndex];
			nx = XofPlayerB + DX[directionIndex];
			ny = YofPlayerB + DY[directionIndex];
			if (isPossibleToMove(nx, ny)) {
				XofPlayerB = nx;
				YofPlayerB = ny;
			}
		}
	}
	
	/**
	 * 다음 좌표로 이동 가능한지 여부를 반환하는 메서드.
	 * 
	 */
	private static boolean isPossibleToMove(int nx, int ny) {
		if (nx > 0 || nx <= SIZE || ny > 0 || ny <= SIZE) {
			return true;
		}
		return false;
	}
	
	/**
	 * 초기 세팅을 진행하는 메서드.
	 * 
	 */
	private static void init() throws IOException {
		maximumSum = 0;
		XofPlayerA = YofPlayerA = 1;
		XofPlayerB = YofPlayerB = 10;
		st = new StringTokenizer(br.readLine());
		timeOfMoving = Integer.parseInt(st.nextToken());
		moveInfosOfplayer = new int[timeOfMoving][2]; // [N][0]은 A 플레이어, [N][1]은 B 플레이어
		countOfBCs = Integer.parseInt(st.nextToken());
		bcs = new BC[countOfBCs];
		// 플레이어 A,B 이동 정보 입력
		StringTokenizer AInfos = new StringTokenizer(br.readLine());
		StringTokenizer BInfos = new StringTokenizer(br.readLine());
		for (int index = 0; index < timeOfMoving; index++) {
			moveInfosOfplayer[index][0] = Integer.parseInt(AInfos.nextToken());
			moveInfosOfplayer[index][1] = Integer.parseInt(BInfos.nextToken());
		}
		// BC 정보 입력
		int x, y, range, amountOfCharging;
		for (int index = 0; index < countOfBCs; index++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			range = Integer.parseInt(st.nextToken()); 
			amountOfCharging = Integer.parseInt(st.nextToken());
			bcs[index] = new BC(y, x, range, amountOfCharging); // 행렬순서로 바꿔서 넣어줌
		}
	}

	/**
	 * BC의 정보를 담은 클래스.
	 * 
	 */
	static class BC {
		int x;
		int y;
		int range;
		int amountOfCharging;
		
		BC(int x, int y, int range, int amountOfCharging) {
			this.x = x;
			this.y = y;
			this.range = range;
			this.amountOfCharging = amountOfCharging;
		}
	}

	/**
	 * 접속 가능한 BC들의 정보를 담은 클래스.
	 * 
	 */
	static class ChargingInfo implements Comparable<ChargingInfo> {
		int bcIndex;
		int amountOfCharging;
		
		ChargingInfo(int bcIndex, int amountOfCharging) {
			this.bcIndex = bcIndex;
			this.amountOfCharging = amountOfCharging;
		}

		@Override
		public int compareTo(ChargingInfo o) {
			return o.amountOfCharging - this.amountOfCharging; // 충전량을 기준으로 내림차순 정렬
		}
	}
}
