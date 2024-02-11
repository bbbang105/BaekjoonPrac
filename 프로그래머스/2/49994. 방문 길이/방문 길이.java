class Solution {
    private final int[][] directions = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}}; // L U D R 순

    public int solution(String dirs) {
        int answer = 0;
        boolean[][][] visited = new boolean[11][11][4];
        int x = 5; int y = 5;
        int nx = 0; int ny = 0;

        for (int i = 0; i < dirs.length(); i++) {
            String cmd = String.valueOf(dirs.charAt(i));
            int dir = 0; // 상하좌우

            switch (cmd) {
                case "L" :
                    dir = 0;
                    break;
                case "U" :
                    dir = 1;
                    break;
                case "D" :
                    dir = 2;
                    break;
                case "R" :
                    dir = 3;
            }
            nx = x + directions[dir][0];
            ny = y + directions[dir][1];
            if (nx < 0 || nx >= 11 || ny < 0 || ny >= 11) continue;
            if (!visited[x][y][dir] && !visited[nx][ny][3 - dir]) {
                answer++;
                visited[x][y][dir] = true;
                visited[nx][ny][3 - dir] = true;
            }
            x = nx; y = ny; // 좌표 갱신
        }

        return answer;
    }
}