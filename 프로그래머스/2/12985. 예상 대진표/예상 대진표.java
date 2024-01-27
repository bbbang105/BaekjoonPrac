public class Solution {
    public int solution(int n, int a, int b) {
        int round = 1;
        while (true) { 
            if (Math.min(a, b) % 2 != 0 && Math.abs(a - b) == 1) break;
            a = (a / 2) + (a % 2);
            b = (b / 2) + (b % 2);
            round++;
        }
        
        return round;
    }
}