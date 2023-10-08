class Solution {
    public int solution(String s) {
        String answer = "";
        String num_str = "";
        String[] nums = {"zero","one","two","three","four","five","six","seven","eight","nine"};
        int index = 0;
        while (index < s.length()) {
            if (Character.isDigit(s.charAt(index))) {
                answer += s.charAt(index);
            } else {
                num_str += s.charAt(index);
                for (int j = 0; j < nums.length; j++) {
                    if (num_str.equals(nums[j])) {
                        answer += Integer.toString(j);
                        num_str = "";
                        break;
                    }

                }
            }
            index += 1;
        }
        return Integer.parseInt(answer);
    }
}