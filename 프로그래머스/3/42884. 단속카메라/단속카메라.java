import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        int answer = 1;
        Arrays.sort(routes, (o1, o2) -> o1[1] - o2[1]);
        
        int lastLocation = routes[0][1];
        for (int index = 1; index < routes.length; index++) {
            if (routes[index][0] <= lastLocation && routes[index][1] >= lastLocation) {
                continue;
            }
            lastLocation = routes[index][1];
            answer++;
        }
        return answer;
    }
}
