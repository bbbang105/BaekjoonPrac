class Solution {
    public static int solution(String[] babbling) {
        int answer = 0;
        for (String s : babbling) {
            String str = "";
            String pre_str = "";
            boolean impossible = false;
            for (int i = 0; i < s.length(); i++) {
                str += s.charAt(i);

                if (str.length() == 2) {
                    if (!pre_str.equals(str) && (str.equals("ye") || str.equals("ma"))) {
                        pre_str = str;
                        str = "";
                    }
                }
                if (str.length() == 3) {
                    if (!pre_str.equals(str) && (str.equals("aya") || str.equals("woo"))) {
                        pre_str = str;
                        str = "";
                    } else {
                        impossible = true;
                        break;
                    }
                }
            }
            if (str != "") {
                impossible = true;
            }
            if (!impossible) {
                answer += 1;
            }
        }
        return answer;
    }
    public static void main(String args[]) {
        System.out.println(solution(new String[]{"aya", "yee", "u"}));
    }
}