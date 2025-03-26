import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * [조건]
 * 1. 사무실은 N x M 크기이며, 최대 8개의 CCTV가 있다.
 * 2. CCTV는 종류(1~5)에 따라 감시할 수 있는 방향이 다르다.
 * 3. CCTV는 회전 가능하며, 벽(6)은 통과할 수 없다.
 * 4. CCTV는 CCTV를 통과할 수 있다.
 * 5. 사각지대(0)는 감시되지 않은 영역이며, 이 크기를 최소화해야 한다.
 * 
 * [풀이]
 * 1. CCTV의 종류별로 가능한 감시 방향 조합을 정의한다.
 * 2. 모든 CCTV의 방향 조합을 DFS로 완전 탐색하며, 감시 구역을 계산한다.
 * 3. 감시한 결과에서 사각지대(0)의 개수를 세고, 최소값을 갱신한다.
 */
public class Main {

    // 사무실 크기 및 결과값
    static int N, M, result = Integer.MAX_VALUE;
    static int[][] board;

    // CCTV 정보 리스트
    static List<CCTV> cctvs = new ArrayList<>();

    // 방향벡터 : 상 우 하 좌
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    // CCTV 번호별 가능한 방향 조합
    static int[][][] directions = {
        {}, // 0번 없음
        {{0}, {1}, {2}, {3}},             // 1번 CCTV (1방향)
        {{0, 2}, {1, 3}},                 // 2번 CCTV (양방향)
        {{0, 1}, {1, 2}, {2, 3}, {3, 0}}, // 3번 CCTV (직각방향)
        {{0, 1, 2}, {1, 2, 3}, {2, 3, 0}, {3, 0, 1}}, // 4번 (3방향)
        {{0, 1, 2, 3}}                    // 5번 (4방향)
    };

    public static void main(String[] args) throws IOException {
        // 초기 세팅
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];

        int cur;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                cur = Integer.parseInt(st.nextToken());
                board[i][j] = cur;
                if (cur >= 1 && cur <= 5) {
                    cctvs.add(new CCTV(cur, i, j)); // CCTV 정보 저장
                }
            }
        }

        // DFS 탐색 시작
        dfs(0, board);
        System.out.println(result);
    }

    /**
     * DFS로 모든 CCTV의 방향 조합을 탐색하는 메서드.
     */
    static void dfs(int depth, int[][] map) {
        if (depth == cctvs.size()) {
            result = Math.min(result, countBlind(map));
            return;
        }

        CCTV cctv = cctvs.get(depth);
        for (int[] dirs : directions[cctv.type]) {
            int[][] copiedMap = copy(map);
            for (int d : dirs) {
                watch(copiedMap, cctv.x, cctv.y, d);
            }
            dfs(depth + 1, copiedMap);
        }
    }

    /**
     * 지정된 방향으로 감시 구역을 표시하는 메서드.
     */
    static void watch(int[][] map, int x, int y, int dir) {
        int nx = x + dx[dir];
        int ny = y + dy[dir];
        while (0 <= nx && nx < N && 0 <= ny && ny < M && map[nx][ny] != 6) {
            if (map[nx][ny] == 0) map[nx][ny] = -1; // 감시 표시
            nx += dx[dir];
            ny += dy[dir];
        }
    }

    /**
     * 사각지대의 개수를 세어 반환하는 메서드.
     */
    static int countBlind(int[][] map) {
        int cnt = 0;
        for (int[] row : map) {
            for (int val : row) {
                if (val == 0) cnt++;
            }
        }
        return cnt;
    }

    /**
     * 2차원 배열 깊은 복사 메서드.
     */
    static int[][] copy(int[][] map) {
        int[][] newMap = new int[N][M];
        for (int i = 0; i < N; i++) newMap[i] = map[i].clone();
        return newMap;
    }

    /**
     * CCTV 정보를 담는 클래스.
     */
    static class CCTV {
        int type, x, y;
        CCTV(int type, int x, int y) {
            this.type = type;
            this.x = x;
            this.y = y;
        }
    }
}
