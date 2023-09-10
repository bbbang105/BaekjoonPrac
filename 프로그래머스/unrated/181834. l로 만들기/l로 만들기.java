class Solution {
    public String solution(String my_str) {
        String res = "";
        for (int i = 0; i < my_str.length(); i++) {
            if ((int) my_str.charAt(i) < (int) 'l') {
                res += "l";
            } else {
                res += my_str.charAt(i);
            }
        }
        return res;
    }
}