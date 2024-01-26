class Solution {
    public int solution(int number) {
        int left = 1; 
        int right = 2;
        int sum = 1;
        int answer = 0;
        
        while (right <= number + 1) {
            if (sum <= number) {
                if (sum == number) {
                    System.out.println(left);
                    System.out.println(right);
                    answer++;
                }
                sum += right;
                right++;
            } else {
                sum -= left;
                left++;
            }
        }
        
        return answer;
    }
}