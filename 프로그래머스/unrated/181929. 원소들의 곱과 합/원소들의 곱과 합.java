class Solution {
    public int solution(int[] num_list) {
        int answer = 0; int Sum = 0; int Power = 1;
        for (int i = 0; i < num_list.length; i++) {
            Sum += num_list[i];
            Power *= num_list[i];
        }
        if (Power < Math.pow(Sum,2)) {
            answer = 1;
        }
        return answer;
    }
}