import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * [조건 및 풀이]
 * 1. 맥주는 최대 20개 까지만 소지 가능하다 (1,000 초과의 거리는 이동할 수 없다.)
 * 2. 편의점에 방문하면 맥주를 다시 채울 수 있다. (20개를 모두 구입하는 것으로 한다.)
 * 3. 거리는 맨해튼 거리로 계산한다.
 * 4. 상근이네 집(시작점) / 편의점 위치들 / 페스티벌 위치(도착점) 좌표가 주어진다.
 * 5. 도착할 수 있는지에 따라 결과를 출력하라.
 * 6. 도착지도 편의점 배열 마지막에 넣어줌으로서 방문할 수 있게 만든다.
 */
public class Main {
	
	private static final int LIMIT = 1_000; // 1_000 초과 거리는 이동 불가능
	
	private static String result;
	private static Pos[] stores;
	private static boolean[] isVisited;
	private static Queue<Pos> q;
	private static int endX;
	private static int endY;
	
	private static BufferedReader br;
	private static StringTokenizer st;
	
	/**
	 * 메인 메서드.
	 */
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		br = new BufferedReader(new InputStreamReader(System.in));
		int testCaseNum = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= testCaseNum; testCase++) {
			init();
			findResult();
			sb.append(result).append('\n');
		}
		System.out.println(sb);
	}
	
	/**
	 * 결과를 도출하는 메서드.
	 */
	private static void findResult() {
		Pos cur;
		int x, y;
		while (!q.isEmpty()) {
			cur = q.poll();
			x = cur.x;
			y = cur.y;
			if (x == endX && y == endY) {
				result = "happy";
				break;
			}
			
			Pos store;
			for (int index = 0; index < stores.length; index++) {
				store = stores[index];
				if (canMove(x, store.x, y, store.y, index)) {
					isVisited[index] = true;
					q.offer(store);
				}
			}
		}
	}
	
	/**
	 * 맨해튼 거리를 기반으로, 이동 가능 여부를 반환하는 메서드.
	 */
	private static boolean canMove(int x, int nx, int y, int ny, int index) {
		return Math.abs(nx - x) + Math.abs(ny - y) <= LIMIT && !isVisited[index];
	}
	
	/**
	 * 초기 세팅 메서드.
	 */
	private static void init() throws IOException {
		result = "sad";
		int counfOfStores = Integer.parseInt(br.readLine());
		stores = new Pos[counfOfStores + 1];
		isVisited = new boolean[counfOfStores + 1];
		q = new LinkedList<>();
		
		st = new StringTokenizer(br.readLine());
		int startX = Integer.parseInt(st.nextToken());
		int startY = Integer.parseInt(st.nextToken());
		q.offer(new Pos(startX, startY));
		
		for (int index = 0; index < counfOfStores; index++) {
			st = new StringTokenizer(br.readLine());
			int storeX = Integer.parseInt(st.nextToken());
			int storeY = Integer.parseInt(st.nextToken());
			stores[index] = new Pos(storeX, storeY);
		}
		
		st = new StringTokenizer(br.readLine());
		endX = Integer.parseInt(st.nextToken());
		endY = Integer.parseInt(st.nextToken());
		stores[counfOfStores] = new Pos(endX, endY); // 도착지도 함께 넣어줌
	}
	
	/**
	 * 좌표의 정보를 담은 클래스.
	 */
	static class Pos {
		int x;
		int y;
		
		Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
