class Solution {
    public String solution(String[] survey, int[] choices) {
        String answer = "";
        int[] score_arr = {3,2,1,0,1,2,3};
        int R = 0; int T = 0;
        int C = 0; int F = 0;
        int J = 0; int M = 0;
        int A = 0; int N = 0;
        // 점수 배정
        for (int i = 0; i < survey.length; i++) {
            int choice_num = choices[i];
            String CHR = "";
            if (choice_num < 4) {
                CHR = survey[i].substring(0,1);
            } else if (choice_num > 4) {
                CHR = survey[i].substring(1,2);
            } else {
                continue;
            }
            switch (CHR) {
                case "R":
                    R += score_arr[choice_num-1]; break;
                case "T":
                    T += score_arr[choice_num-1]; break;
                case "C":
                    C += score_arr[choice_num-1]; break;
                case "F":
                    F += score_arr[choice_num-1]; break;
                case "J":
                    J += score_arr[choice_num-1]; break;
                case "M":
                    M += score_arr[choice_num-1]; break;
                case "A":
                    A += score_arr[choice_num-1]; break;
                case "N":
                    N += score_arr[choice_num-1]; break;
            }
        }
        // 지표 점수 비교
        if (R > T) answer += "R";
        else if (R < T){
            answer += "T";
        } else {
            answer += "R";
        }
        if (C > F) answer += "C";
        else if (C < F) {
            answer += "F";
        } else {
            answer += "C";
        }
        if (J > M) answer += "J";
        else if (J < M) {
            answer += "M";
        } else {
            answer += "J";
        }
        if (A > N) answer += "A";
        else if (A < N) {
            answer += "N";
        } else {
            answer += "A";
        }

        return answer;
    }
}