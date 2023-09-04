class Solution {
    public int[] solution(int start, int end_num) {
        int l = (start-end_num+1);
        int[] answer = new int[l];
        for (int i = 0; i < l; i++) {
            answer[i] = start-i;
        }
        return answer;
    }
}