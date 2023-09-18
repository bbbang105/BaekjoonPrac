class Solution {
    public static String solution(int[] numbers, String hand) {
        String answer = "";
        String[][] keypad = {{"1","2","3"},{"4","5","6"},{"7","8","9"},{"*","0","#"}};
        int[] r_start = {3,0}; int[] l_start = {3,2};

        for (int n : numbers) {
            String str = "" + n;
            boolean find = false;
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 3; j++) {
                    if (keypad[i][j].equals(str)) {
                        if (str.equals("1") || str.equals("4") || str.equals("7")) {
                            answer += "L";
                            l_start[0] = i; l_start[1] = j;
                        } else if (str.equals("3") || str.equals("6") || str.equals("9")) {
                            answer += "R";
                            r_start[0] = i; r_start[1] = j;
                        } else {
                            int r_distance = Math.abs(i - r_start[0]) + Math.abs(j - r_start[1]);
                            int l_distance = Math.abs(i - l_start[0]) + Math.abs(j - l_start[1]);
                            if (r_distance > l_distance) {
                                answer += "L";
                                l_start[0] = i; l_start[1] = j;
                            }
                            else if (r_distance < l_distance) {
                                answer += "R";
                                r_start[0] = i; r_start[1] = j;
                            } else {
                                if (hand.equals("right")) {
                                    answer += "R";
                                    r_start[0] = i; r_start[1] = j;
                                } else {
                                    answer += "L";
                                    l_start[0] = i; l_start[1] = j;
                                }
                            }
                        }
                        find = true;
                        break;
                    }
                }
                if (find) break;
            }
        }
        return answer;
    }
}