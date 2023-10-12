import java.util.Arrays;

class Solution {
    public String solution(String s) {
        String answer = "";
        String[] arr = s.split(" ");
        int[] new_arr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            new_arr[i] = Integer.parseInt(arr[i]);
        }
        Arrays.sort(new_arr);
        answer += Integer.toString(new_arr[0]);
        answer += " ";
        answer += Integer.toString(new_arr[new_arr.length-1]);
            
        return answer;
    }
}