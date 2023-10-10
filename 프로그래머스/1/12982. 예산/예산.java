import java.util.Arrays;

class Solution {
    public int solution(int[] d, int budget) {
        int answer = 0;
        Arrays.sort(d);
        int sum = 0;
        for (int n : d) {
            sum += n;
            if (sum > budget) break;
            answer += 1;
        }
        return answer;
    }
}