import java.util.ArrayList;

class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];
        ArrayList<Integer> win_list = new ArrayList<>();
        for (int w : win_nums) {
            win_list.add(w);
        }
        int win_cnt = 0; int zero_cnt = 0;
        for (int l : lottos) {
            if (l == 0) {
                zero_cnt ++;
            } else {
                if (win_list.contains(l)) {
                    win_cnt ++;
                }
            }
        }
        int high_rank = 0; int low_rank = 0;
        if (zero_cnt == 0 && win_cnt == 0) {
            high_rank = 6; low_rank = 6;
        } else {
            high_rank = (7 - (zero_cnt + win_cnt));
            if (win_cnt != 0) {
                low_rank = (7 - win_cnt);
            } else {
                low_rank = 6;
            }
        }
        answer[0] = high_rank; answer[1] = low_rank;

        return answer;
    }
}