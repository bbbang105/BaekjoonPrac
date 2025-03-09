import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [조건]
 * 1. 한 변의 길이가 N인 정사각형 지역에 디저트 카페가 배치되어 있음.
 * 2. (r,c)에서 출발해 대각선 방향으로 이동하며 사각형 모양을 그려야 함.
 * 3. 시작 지점으로 되돌아와야 하며, 경로상 같은 디저트를 다시 먹으면 안 됨.
 * 4. 경로를 구성하는 최소 방문 카페 수는 4개.
 * 5. 가능한 모든 경로 중 가장 많은 디저트를 먹을 수 있는 경로의 디저트 수를 출력.
 * 6. 투어가 불가능하면 -1을 출력.
 *
 * [풀이]
 * 1. 모든 가능한 시작 지점(i,j)에 대해 DFS를 수행.
 * 2. dfs(r,c, startR, startC, 현재방향, 현재 먹은 디저트 수):
 *    - 현재 위치 (r,c)의 카페 디저트를 방문 처리(visited + dessertVisited)
 *    - 다음 방향은 현재방향부터 4방향까지 시도(대각선 이동).
 *    - 이동할 위치가 출발점이면(그리고 최소 4개 방문) 결과 갱신.
 *    - 이동할 위치가 경계 밖/이미 방문/이미 먹은 디저트면 무시.
 *    - 재귀 호출 후에는 되돌아오기 위해 visited와 dessertVisited를 해제(백트래킹).
 * 3. 모든 (i,j)에 대해 DFS를 수행 후, 가능한 최댓값이 없으면 -1, 그렇지 않으면 그 최대값을 출력.
 */
public class Solution {

    static int[] dr = {1, 1, -1, -1}; // 행 이동
    static int[] dc = {1, -1, -1, 1}; // 열 이동

    static int N, answer;
    static int[][] cafe;          // 디저트 카페 정보
    static boolean[][] visited;   // 방문 체크
    static boolean[] dessertVisited; // 디저트 종류 방문 체크

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            cafe = new int[N][N];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    cafe[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            answer = -1; // 투어 불가 시 -1 출력 대비
            // 가능한 모든 시작점 탐색
            for (int i = 0; i < N - 2; i++) {
                for (int j = 1; j < N - 1; j++) {
                    visited = new boolean[N][N];
                    dessertVisited = new boolean[101];
                    dfs(i, j, i, j, 0, 1);
                }
            }

            System.out.println("#" + tc + " " + answer);
        }
    }

    /**
     * DFS를 통해 대각선 방향으로 카페 투어를 진행한다.
     */
    private static void dfs(int r, int c, int startR, int startC, int dir, int count) {
        // 현재 위치 방문 및 디저트 체크
        visited[r][c] = true;
        dessertVisited[cafe[r][c]] = true;

        for (int i = dir; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            // 출발점 도달, 최소 4카페 이상
            if (nr == startR && nc == startC && count >= 4) {
                answer = Math.max(answer, count);
                // 방문해제, 디저트해제는 return이후, 아래에서 처리
                visited[r][c] = false;
                dessertVisited[cafe[r][c]] = false;
                return;
            }

            // 경계체크, 방문체크, 디저트체크
            if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
            if (visited[nr][nc]) continue;
            if (dessertVisited[cafe[nr][nc]]) continue;

            dfs(nr, nc, startR, startC, i, count + 1);
        }

        // 백트래킹
        visited[r][c] = false;
        dessertVisited[cafe[r][c]] = false;
    }
}
