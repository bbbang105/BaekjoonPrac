import java.util.*;
class Solution {
    public int[] solution(String[] park, String[] routes) {
        int[] answer = new int[2];
        // 북 남 서 동
        int[] dx = {-1,1,0,0}; int[] dy = {0,0,-1,1};
        Map<String,Integer> ht = new HashMap<String,Integer>();
        ht.put("N",0); ht.put("S",1); ht.put("W",2); ht.put("E",3);
        // 시작 위치 찾아주기
        int x = 0; int y = 0;
        for (int i = 0; i < park.length; i++) {
            for (int j = 0; j < park[0].length(); j++) {
                if (park[i].charAt(j) == 'S') {
                    x = i; y = j;
                    break;
                }
            }
        }
        for (String r:routes) {
            String[] info = r.split(" ");
            // 방향 , 얼마나 이동하는지
            int d = ht.get(info[0]); int n = Integer.parseInt(info[1]); boolean flag = true;
            int nx = x; int ny = y;
            for (int i = 0; i < n; i++) {
                nx += dx[d]; ny += dy[d]; // 좌표 이동
                if (nx >= 0 && nx < park.length && ny >= 0 && ny < park[0].length()) {
                    // 장애물을 만난 경우
                    if (park[nx].charAt(ny) == 'X') {
                        flag = false; // 불가능한 명령
                        break;
                    }
                // 공원을 벗어나는 경우
                } else {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                x = nx; y = ny;
            }
        }
        answer[0] = x; answer[1] = y;
        return answer;
    }
}