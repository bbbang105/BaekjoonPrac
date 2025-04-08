import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * [경제]
 * 1. 빈 칸(.)은 언제나 이동 가능하다.
 * 2. 벽(#)은 절대 이동 불가능하다.
 * 3. 열쇠(알파벳 소문자)는 언제나 이동 가능하며, 첫 방문 시 열쇠를 줍는다.
 * 4. 문(알파벳 대문자)은 대응하는 열쇠를 소지하고 있을 시 이동가능하다.
 * 5. 민식이의 초기 위치는 0이다.
 * 6. 출구는 1이며, 해당 위치로 이동해야 한다. 1은 최소 1개 이상 있다.
 */
public class Main {

	private static final int[] DX = {-1, 1, 0, 0};
	private static final int[] DY = {0, 0, -1, 1};

	private static int R;
	private static int C;
	private static char[][] graph;
	private static boolean[][][] visited;
	private static Queue<Minsik> q;
	private static int result;

	/**
	 * 메인 메서드.
	 */
	public static void main(String[] args) throws IOException {
		init();
		findResult();
		System.out.println(result);
	}
	
	/**
	 * 탐색을 진행하며 결과를 도출하는 메서드.
	 */
	private static void findResult() {
		while (!q.isEmpty()) {
			Minsik cur = q.poll();

			for (int dir = 0; dir < 4; dir++) {
				int nx = cur.x + DX[dir];
				int ny = cur.y + DY[dir];
				int nKey = cur.keyMask;
				int nCnt = cur.moveCount + 1;

				// 범위를 넘어감
				if (nx < 0 || ny < 0 || nx >= R || ny >= C) {
					continue;
				}
				
				char ch = graph[nx][ny];
				// 벽인 경우
				if (ch == '#') {
					continue;
				}

				// 열쇠 줍기
				if ('a' <= ch && ch <= 'f') {
					nKey |= (1 << (ch - 'a'));
				}

				// 해당 문을 열 열쇠가 없는 경우
				if ('A' <= ch && ch <= 'F') {
					if ((nKey & (1 << (ch - 'A'))) == 0) {
						continue;
					}
				}

				// 이미 방문한 경우
				if (visited[nx][ny][nKey]) {
					continue;
				}
				
				// 도착한 경우
				if (ch == '1') {
					result = Math.min(result, nCnt);
					return;
				}

				visited[nx][ny][nKey] = true;
				q.offer(new Minsik(nx, ny, nKey, nCnt));
			}
		}
		result = -1; // 마지막에도 찾을 수 없을 경우
	}
	
	/**
	 * 초기 세팅 메서드.
	 */
	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		graph = new char[R][C];
		visited = new boolean[R][C][64]; // 2^6 개 열쇠 조합
		q = new LinkedList<>();
		result = Integer.MAX_VALUE;

		for (int i = 0; i < R; i++) {
			String row = br.readLine();
			for (int j = 0; j < C; j++) {
				graph[i][j] = row.charAt(j);
				if (graph[i][j] == '0') {
					q.offer(new Minsik(i, j, 0, 0));
					visited[i][j][0] = true;
					graph[i][j] = '.'; // 초기 위치는 빈 칸으로 변경
				}
			}
		}
	}

	/**
	 * 민식이의 위치, 열쇠 조합, 이동 횟수를 담은 클래스
	 */
	static class Minsik {
		int x; 
		int y;
		int keyMask;
		int moveCount;
		
		Minsik(int x, int y, int keyMask, int moveCount) {
			this.x = x;
			this.y = y;
			this.keyMask = keyMask;
			this.moveCount = moveCount;
		}
	}
}
