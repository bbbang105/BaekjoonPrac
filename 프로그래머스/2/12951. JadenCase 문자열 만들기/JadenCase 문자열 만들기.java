class Solution {
    public String solution(String s) {
        StringBuilder answer = new StringBuilder();

        boolean start_str = true;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                answer.append(" ");
                start_str = true;
            } else {
                String str = "" + c;
                if (start_str && Character.isAlphabetic(c)) {
                    answer.append(str.toUpperCase());
                    start_str = false;
                } else if (Character.isDigit(c)){
                    answer.append(str);
                    start_str = false;
                } else {
                    answer.append(str.toLowerCase());
                }
            }
        }
        return answer.toString();
    }
}