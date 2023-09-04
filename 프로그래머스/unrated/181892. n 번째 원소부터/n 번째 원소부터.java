class Solution {
    public int[] solution(int[] num_list, int n) {
        n -= 1;
        int[] answer = new int[num_list.length - n];
        for (int i = 0; i < num_list.length - n; i++) {
            answer[i] = num_list[n+i];
        }
        return answer;
    }
}