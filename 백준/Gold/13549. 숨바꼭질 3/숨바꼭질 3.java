import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int result = find(N, K);
        System.out.println(result);
    }

    private static int find(int N, int K) {
        boolean[] visited = new boolean[100_001];
        Deque<Location> q = new LinkedList<>();
        q.offer(new Location(N, 0));
        visited[N] = true;

        while (!q.isEmpty()) {
            Location location = q.poll();
            int cur = location.cur;
            int time = location.time;

            if (cur == K) {
                return time;
            }

            // 순간이동 (0초)
            int moveTeleport = cur * 2;
            if (moveTeleport <= 100000 && !visited[moveTeleport]) {
                q.offerFirst(new Location(moveTeleport, time));
                visited[moveTeleport] = true;
            }

            // -1 이동 (1초)
            int moveMinusOne = cur - 1;
            if (moveMinusOne >= 0 && !visited[moveMinusOne]) {
                q.offer(new Location(moveMinusOne, time + 1));
                visited[moveMinusOne] = true;
            }
            
            // +1 이동 (1초)
            int movePlusOne = cur + 1;
            if (movePlusOne <= 100000 && !visited[movePlusOne]) {
                q.offer(new Location(movePlusOne, time + 1));
                visited[movePlusOne] = true;
            }
        }

        return -1;
    }

    static class Location {
        int cur;
        int time;

        Location(int cur, int time) {
            this.cur = cur;
            this.time = time;
        }
    }
}