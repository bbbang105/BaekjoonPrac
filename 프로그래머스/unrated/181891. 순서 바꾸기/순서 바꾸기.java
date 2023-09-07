class Solution {
    public int[] solution(int[] num_list, int n) {
        int[] answer = new int[num_list.length];
        int index = 0;
        for (int i = n; i < num_list.length; i++) {
            answer[index++] = num_list[i];
        }
        for (int j = 0; j < n; j++) {
            answer[index++] = num_list[j];
        }
        return answer;
    }
}