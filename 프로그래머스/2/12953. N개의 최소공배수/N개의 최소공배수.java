import java.util.HashMap;

class Solution {
    public static int solution(int[] arr) {
        int lcm = arr[0];

        for (int i = 1; i < arr.length; i++) {
            int n1 = lcm;
            int n2 = arr[i];

            for (int j = Math.min(n1,n2); j > 0; j--) {
                if (n1 % j == 0 && n2 % j == 0) {
                    lcm = (n1 * n2) / j;
                    break;
                }
            }
        }
        return lcm;
    }
    // 테스트 케이스
    public static void main(String args[]) {
        System.out.println(solution(new int[]{2,6,8,14}));
    }
}