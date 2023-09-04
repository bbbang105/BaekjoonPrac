class Solution {
    public int solution(String myString, String pat) {
        String str1 = myString.toUpperCase();
        String str2 = pat.toUpperCase();
        int answer = 0;
        if (str1.contains(str2)) {
            answer = 1;
        }
        return answer;
    }
}