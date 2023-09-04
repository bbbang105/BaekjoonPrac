class Solution {
    public String solution(String my_string, int n) {
        String answer = "";
        int k = my_string.length() - n;
        answer = my_string.substring(k,my_string.length());
        return answer;
    }
}