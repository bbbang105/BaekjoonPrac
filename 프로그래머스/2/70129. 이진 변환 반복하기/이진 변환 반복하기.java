class Solution {
    public int[] solution(String s) {
        int[] answer = new int[2];

        while (!s.equals("1")) {
            int delete_zero = 0;

            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '0') delete_zero++;
            }
            answer[0]++; answer[1]+= delete_zero;
            s = Integer.toBinaryString(s.length() - delete_zero);
        }
        return answer;
    }
}