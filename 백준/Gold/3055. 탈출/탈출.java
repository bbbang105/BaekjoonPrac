import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * [조건 및 풀이]
 * 1. 물(*)과 고슴도치(S)는 각각 BFS로 확산 및 이동한다.
 * 2. 물이 먼저 퍼지고 난 뒤, 고슴도치가 이동할 수 있는 곳으로 탐색한다.
 * 3. 비버의 굴(D)에 도달하면 시간을 출력하고 종료한다.
 * 4. 물은 '.'에만 퍼지고, D나 X로는 퍼질 수 없다.
 * 5. 고슴도치는 '.', 'D'로 이동할 수 있다. '*'나 'X'는 이동 불가.
 * 6. 물과 고슴도치의 방문 배열은 분리해서 처리해야 한다.
 */
public class Main {

    private static final int[] DX = {-1, 1, 0, 0};
    private static final int[] DY = {0, 0, -1, 1};

    private static int R, C, result = -1;
    private static char[][] graph;
    private static boolean[][] beaverVisited;
    private static Queue<Pos> waterQ;
    private static Queue<Pos> beaverQ;
    private static int endX, endY;

    public static void main(String[] args) throws IOException {
        init();
        bfs();
        System.out.println(result == -1 ? "KAKTUS" : result);
    }

    private static void bfs() {
        int time = 0;
        while (!beaverQ.isEmpty()) {
            int waterSize = waterQ.size();
            while (waterSize-- > 0) {
                Pos w = waterQ.poll();
                for (int d = 0; d < 4; d++) {
                    int nx = w.x + DX[d];
                    int ny = w.y + DY[d];
                    if (canSpreadWater(nx, ny)) {
                        graph[nx][ny] = '*';
                        waterQ.offer(new Pos(nx, ny));
                    }
                }
            }

            int beaverSize = beaverQ.size();
            while (beaverSize-- > 0) {
                Pos cur = beaverQ.poll();
                if (cur.x == endX && cur.y == endY) {
                    result = time;
                    return;
                }

                for (int d = 0; d < 4; d++) {
                    int nx = cur.x + DX[d];
                    int ny = cur.y + DY[d];
                    if (canMoveBeaver(nx, ny)) {
                        beaverVisited[nx][ny] = true;
                        beaverQ.offer(new Pos(nx, ny));
                    }
                }
            }
            time++;
        }
    }

    private static boolean canSpreadWater(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C && graph[x][y] == '.';
    }

    private static boolean canMoveBeaver(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C
                && !beaverVisited[x][y]
                && (graph[x][y] == '.' || graph[x][y] == 'D');
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        graph = new char[R][C];
        beaverVisited = new boolean[R][C];
        waterQ = new LinkedList<>();
        beaverQ = new LinkedList<>();

        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                graph[i][j] = line.charAt(j);
                if (graph[i][j] == 'S') {
                    beaverQ.offer(new Pos(i, j));
                    beaverVisited[i][j] = true;
                    graph[i][j] = '.'; // 처리 후 일반 칸으로 변경
                } else if (graph[i][j] == '*') {
                    waterQ.offer(new Pos(i, j));
                } else if (graph[i][j] == 'D') {
                    endX = i;
                    endY = j;
                }
            }
        }
    }

    static class Pos {
        int x, y;
        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
