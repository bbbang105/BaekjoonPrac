class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        int Sum = brown + yellow;

        for (int i = 1; i <= (int) Math.pow(yellow,0.5); i++) {
            if (yellow % i == 0) {
                int x = (yellow / i) + 2;
                int y = i + 2;

                if ((x * y) == Sum) {
                    answer[0] = x; answer[1] = y;
                    break;
                }
            }
        }
        return answer;
    }
}