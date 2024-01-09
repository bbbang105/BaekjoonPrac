import java.util.*;

class Solution {

    int[] dx = {-1,1,0,0};
    int[] dy = {0,0,-1,1};
    Map<Integer, Integer> map = new HashMap<>();

    public int solution(int[][] land) {
        int answer = 0;
        int groupId = 1;
        int[][] visited = new int[land.length][land[0].length];

        // 각 그룹 석유량 파악
        for (int i = 0; i < land.length; i ++) {
            for (int j = 0; j < land[0].length; j++) {
                if (visited[i][j] == 0 && land[i][j] == 1) {
                    bfs(new int[]{i, j}, land, visited, groupId);
                    groupId += 1;
                }
            }
        }

        // 각 열마다 석유량 계산
        for (int col = 0; col < land[0].length; col++) {
            answer = Math.max(answer, countOilAmount(col, land, visited));
        }

        return answer;
    }

    // bfs
    private void bfs(int[] start, int[][] land, int[][] visited, int groupId) {
        int amount = 1;
        Queue<int[]> q = new LinkedList<>();
        q.add(start);
        visited[start[0]][start[1]] = groupId;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || nx >= land.length || ny < 0 || ny >= land[0].length) {
                    continue;
                }

                if (visited[nx][ny] == 0 && land[nx][ny] == 1) {
                    visited[nx][ny] = groupId;
                    amount += 1;
                    q.add(new int[]{nx, ny});
                }
            }
        }
        // 그룹 석유량 저장
        map.put(groupId, amount);
    }

    // 석유량을 계산하는 메소드
    private int countOilAmount(int col, int[][] land, int[][] visited) {
        int totalAmount = 0;
        List<Integer> visitedGroup = new ArrayList<>();

        for (int i = 0; i < land.length; i++) {
            int id = visited[i][col];
            if (id != 0 && !visitedGroup.contains(id)) {
                // 처음 만나는 석유 그룹만 더해줌
                totalAmount += map.get(id);
                visitedGroup.add(id);
            }
        }

        return totalAmount;
    }
}
