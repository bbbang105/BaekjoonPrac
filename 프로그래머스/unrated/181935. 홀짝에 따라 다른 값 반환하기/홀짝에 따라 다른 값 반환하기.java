class Solution {
    public int solution(int n) {
        int res = 0;
        if (n % 2 != 0) {
            for (int i = 1; i <= n; i+=2) {
                res += i;
            }
        } else {
            for (int i = 2; i <= n; i+=2) {
                res += Math.pow(i,2);
            }
        }
        return res;
    }
}