import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        Arrays.sort(routes, (o1, o2) -> o1[1] - o2[1]);
        int answer = 1;
        int len = routes.length;
        int maxNum = routes[0][1];
        
        for (int i = 1; i < len; i++) {
            if (maxNum >= routes[i][0] && maxNum <= routes[i][1]) continue;
            maxNum = routes[i][1];
            answer++;
        }
        
        return answer;
    }
}