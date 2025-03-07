import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * [조건]
 * 1. 사이즈 N은 5이상 100이하의 정수이다.
 * 2. 최초 미생물 군집 개수 K는 5이상 1000이하이다.
 * 3. 최초에 둘 이상의 군집이 같은 자리에 배정되지 않는다.
 * 4. 각 군집 내 미생물 수는 1이상 10000이하의 정수이다.
 * 5. 군집의 이동방향은 상1 하2 좌3 우4 이다.
 * 6. 미생물 군집은 1시간마다 이동방향에 있는 다음 셀로 이동한다.
 * 7. 가장자리에 있는 약품에 닿으면, 수가 반으로 줄어들고 이동방향이 반대로 변경된다.
 * 8. 0이 되면 군집 사라짐
 * 9. 같은 위치로 이동하면 합쳐짐(가장 큰 미생물 수의 방향 유지)
 * 10. M시간 후 남은 미생물 수 총합
 *
 * [풀이]
 * 1. Queue<Micro>를 사용해 군집을 관리한다.
 * 2. 각 시간마다 모든 군집을 새 위치로 이동 -> HashMap<(nx,ny), List<Micro>>에 저장
 * 3. 같은 위치로 이동한 군집들은 한꺼번에 합치기 -> 미생물 수 합산, 가장 큰 군집의 방향
 * 4. 약품 구역이면 군집 절반 감소 + 방향 반전
 * 5. M시간 후 남아있는 미생물 수 합산
 */
public class Solution {

    // 0=상,1=하,2=좌,3=우
    private static final int[] DX = {-1, 1, 0, 0};
    private static final int[] DY = {0, 0, -1, 1};
    // 입출력 관련
    private static BufferedReader br;
    private static StringTokenizer st;
    private static StringBuilder sb;
    // 탐색
    private static Queue<Micro> microQueue; // 군집을 담을 큐
    private static int N;          // 구역 크기
    private static int M;          // 격리 시간
    private static int K;          // 군집 개수
    private static int result;     // 최종 결과

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            init();
            simulate();
            sb.append("#").append(t).append(" ").append(result).append("\n");
        }
        System.out.print(sb);
    }

    /**
     *  초기 세팅을 진행하는 메서드.
     *  
     */
    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        microQueue = new LinkedList<>();
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int amount = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken()) - 1;

            microQueue.offer(new Micro(x, y, amount, dir));
        }
        result = 0;
    }

    /**
     *  M시간 동안 시뮬레이션하는 메서드.
     *  
     */
    private static void simulate() {
        for (int time = 0; time < M; time++) {
            // 같은 위치로 이동한 군집들을 합치기 위해 Map 사용
            Map<String, List<Micro>> map = new HashMap<>();

            // 이번 시간에 존재하는 군집들 전부 이동
            int size = microQueue.size();
            for (int i = 0; i < size; i++) {
                Micro cur = microQueue.poll();

                // 1) 새 좌표
                int nx = cur.x + DX[cur.dir];
                int ny = cur.y + DY[cur.dir];

                // 2) 약품 구역이면 절반 감소 + 방향 반전
                if (isEdge(nx, ny)) {
                    cur.amount /= 2;
                    cur.dir = reverseDir(cur.dir);
                }

                // 3) 0이면 소멸
                if (cur.amount == 0) {
                    continue;
                }

                // 4) 위치 갱신
                cur.x = nx;
                cur.y = ny;

                // 5) Map<(nx,ny), List<Micro>> 에 추가
                String key = nx + "," + ny;
                if (!map.containsKey(key)) {
                    map.put(key, new ArrayList<>());
                }
                map.get(key).add(cur);
            }

            // 6) 같은 위치에 모인 군집 합치기
            for (Map.Entry<String, List<Micro>> entry : map.entrySet()) {
                List<Micro> list = entry.getValue();

                if (list.size() == 1) {
                    // 군집이 1개라면 그대로 큐에 삽입
                    microQueue.offer(list.get(0));
                } else {
                    // 여러 개면 합침
                    int sum = 0;
                    int maxAmount = 0;
                    int newDir = 0;
                    int xx = list.get(0).x;
                    int yy = list.get(0).y;

                    // 가장 미생물 수 큰 군집의 방향 찾기
                    for (Micro m : list) {
                        sum += m.amount;
                        if (m.amount > maxAmount) {
                            maxAmount = m.amount;
                            newDir = m.dir;
                        }
                    }
                    // 합쳐진 군집
                    microQueue.offer(new Micro(xx, yy, sum, newDir));
                }
            }
        }

        // M시간 종료 후, 남은 군집 수 합산
        result = 0;
        while (!microQueue.isEmpty()) {
            result += microQueue.poll().amount;
        }
    }

    /**
     *  가장자리(약품 구역)인지 판별하는 메서드.
     *  
     */
    private static boolean isEdge(int x, int y) {
        return x == 0 || x == N - 1 || y == 0 || y == N - 1;
    }

    /**
     *  방향을 반전시키는 메서드.
     *  
     */
    private static int reverseDir(int d) {
        if (d == 0) return 1;
        if (d == 1) return 0;
        if (d == 2) return 3;
        return 2;
    }

    /**
     *  군집 정보를 담은 클래스.
     *  
     */
    static class Micro {
        int x, y;    // 현재 위치
        int amount;  // 미생물 수
        int dir;     // 이동 방향 

        public Micro(int x, int y, int amount, int dir) {
            this.x = x;
            this.y = y;
            this.amount = amount;
            this.dir = dir;
        }
    }
}
