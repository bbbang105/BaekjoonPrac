class Solution {
    public static String solution(String new_id) {
        // 1단계
        new_id = new_id.toLowerCase();
        // 2단계
        String new_id2 = "";
        for (int i = 0; i < new_id.length(); i++) {
            char c = new_id.charAt(i);
            if (Character.isAlphabetic(c)
                    || Character.isDigit(c)
                    || c == '-'
                    || c == '_'
                    || c == '.') {
                new_id2 += c;
            }
        }
        // 3단계
        String new_id3 = "";
        for (int i = 0; i < new_id2.length(); i++) {
            char c = new_id2.charAt(i);
            if (new_id3.length() > 0 && new_id3.charAt(new_id3.length()-1) == '.' && c == '.') {
                continue;
            }
            new_id3 += c;
        }
        // 4단계
        String new_id4 = new_id3;
        if (new_id4 == "." || new_id4 == "..") {
            new_id4 = "";
        } else {
            if (new_id3.length() > 2 && new_id3.charAt(0) == '.' && new_id3.charAt(new_id3.length()-1) == '.'){
                new_id4 = new_id3.substring(1,new_id3.length()-1);
            } else if (new_id3.charAt(0) == '.') {
                new_id4 = new_id3.substring(1);
            } else if (new_id3.charAt(new_id3.length()-1) == '.') {
                new_id4 = new_id3.substring(0,new_id3.length()-1);
            }
        }
        // 5단계
        String new_id5 = new_id4;
        if (new_id4.length() == 0) {
            new_id5 = "a";
        }
        // 6단계
        String new_id6 = new_id5;
        if (new_id5.length() > 15) {
            new_id6 = new_id5.substring(0,15);
        }
        if (new_id6.charAt(new_id6.length()-1) == '.') {
            new_id6 = new_id6.substring(0,new_id6.length()-1);
        }
        // 7단계
        String new_id7 = new_id6;
        if (new_id6.length() < 3) {
            int l = new_id6.length();
            char c = new_id6.charAt(new_id6.length()-1);
            for (int i = 0; i < (3-l); i++) {
                new_id7 += c;
            }
        }
        return new_id7;
    }
}