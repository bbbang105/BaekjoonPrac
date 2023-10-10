class Solution {
    public static int solution(int[][] sizes) {
        int answer = 0;
        int w = 0; int h = 0;

        for (int[] size : sizes) {
            int n1 = size[0]; int n2 = size[1];
            if (n2 > n1) {
                n1 = size[1];
                n2 = size[0];
            }
            w = Math.max(n1,w); h = Math.max(n2,h);
        }
        return w*h;
    }
}