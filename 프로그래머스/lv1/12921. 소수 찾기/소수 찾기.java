import java.util.Arrays;

class Solution {
    public int solution(int n) {
        int answer = 0;
        boolean[] visited = new boolean[n+1];
        Arrays.fill(visited,true);

        for (int i = 2; i < (int)Math.sqrt(n)+1; i++) {
            if (visited[i]) {
                int j = 2;
                while (i*j <= n) {
                    visited[i*j] = false;
                    j += 1;
                }
            }
        }
        for (int i = 2; i < n+1; i++) {
            if (visited[i]) {
                answer += 1;
            }
        }
        return answer;
    }
}