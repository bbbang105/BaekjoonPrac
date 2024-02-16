import java.util.*;

class Solution {

    public int solution(int m, int n, String[] board) {
        int answer = 0;

        char[][] map = new char[m][n];
        for (int i = 0; i < m; i++) {
            map[i] = board[i].toCharArray();
        }

        while (true) {
            boolean flag = false;
            boolean[][] check = new boolean[m][n];

            // 블록을 터트리는 과정
            for (int i = 0; i < m - 1; i++) {
                for (int j = 0; j < n - 1; j++) {
                    if (map[i][j] != '.' &&
                            map[i][j] == map[i][j + 1] &&
                            map[i][j] == map[i + 1][j] &&
                            map[i][j] == map[i + 1][j + 1]) {
                        check[i][j] = true;
                        check[i + 1][j] = true;
                        check[i][j + 1] = true;
                        check[i + 1][j + 1] = true;
                        flag = true;
                    }
                }
            }

            // 터진 블록이 없다면 게임 종료
            if (!flag) break;

            // 터진 블록들을 제거하는 과정
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (check[i][j]) map[i][j] = '.';
                }
            }

            // 위에 남아있는 블록을 내리는 과정
            for (int j = 0; j < n; j++) {
                Queue<Character> q = new LinkedList<>();
                for (int i = m - 1; i >= 0; i--) {
                    if (map[i][j] != '.') q.offer(map[i][j]);
                }
                // 내린 후의 윗공간을 채워줌
                if (q.size() < m) {
                    int needMore = m - q.size();
                    for (int i = 0; i < needMore; i++) {
                        q.offer('.');
                    }
                }
                // 맵을 다시 형성해줌
                for (int i = m - 1; i >= 0; i--) {
                    map[i][j] = q.poll();
                }
            }
        }

        // 터진 블록의 개수를 세주는 과정
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == '.') answer++;
            }
        }

        return answer;
    }
}