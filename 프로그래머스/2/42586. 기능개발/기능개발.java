import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

class Solution {
    public static Integer[] solution(int[] progresses, int[] speeds) {
        List<Integer> answer = new ArrayList<>();
        Stack<Integer> need_days = new Stack<>();
        // 뒤쪽 작업부터 스택에 넣음
        for (int i = progresses.length-1; i >= 0; i--) {
            int per_remain = progresses[i];
            int per_speed = speeds[i];
            int per_need_day = cal_remain(per_remain, per_speed);
            need_days.push(per_need_day);
        }
        int pre = need_days.pop();
        int cnt = 1;
        while (!need_days.isEmpty()) {
            int cur = need_days.pop();
            if (pre < cur) {
                answer.add(cnt);
                pre = cur;
                cnt = 0;
            }
            cnt ++;
        }
        answer.add(cnt);

        return answer.toArray(new Integer[0]);
    }

    public static int cal_remain(int r, int s) {
        int need_day = 0;
        while (r < 100) {
            r += s;
            need_day++;
        }
        return need_day;
    }

    public static void main(String args[]) {
        System.out.println(Arrays.toString(solution(new int[]{90, 98, 97, 96, 98}, new int[]{1, 1, 1, 1, 1})));
    }
}