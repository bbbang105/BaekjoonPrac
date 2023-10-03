class Solution {
    public static String solution(int a, int b) {
        String answer = "";
        // 1월 1일로부터 며칠이 지났는지 파악
        int day_sum = b;
        for (int month = 1; month < a; month++) {
            switch (month) {
                case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                    day_sum += 31;
                    break;
                case 4: case 6: case 9: case 11:
                    day_sum += 30;
                    break;
                case 2: // 윤년 2월
                    day_sum += 29;
                    break;
            }
        }
        // 무슨 요일인지 확인
        int day_num = (day_sum % 7);
        switch (day_num) {
            case 1:
                answer = "FRI";
                break;
            case 2:
                answer = "SAT";
                break;
            case 3:
                answer = "SUN";
                break;
            case 4:
                answer = "MON";
                break;
            case 5:
                answer = "TUE";
                break;
            case 6:
                answer = "WED";
                break;
            case 0:
                answer = "THU";
                break;
        }
        return answer;
    }
}