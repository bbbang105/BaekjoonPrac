import java.util.ArrayList;
import java.util.Arrays;

class Solution {
    public static int solution(int n, int[] lost, int[] reserve) {
        int answer = (n - lost.length);
        ArrayList<Integer> reserve_list = new ArrayList<>();
        ArrayList<Integer> lost_list = new ArrayList<>();
        Arrays.sort(lost); Arrays.sort(reserve);
        for (int l : lost) {
            lost_list.add(l);
        }
        for (int r : reserve) {
            reserve_list.add(r);
        }
        for (int l : lost) {
            int left = l-1; int right = l+1;
            if (reserve_list.contains(l)) {
                reserve_list.remove(Integer.valueOf(l));
                answer += 1;
                continue;
            }
            if (reserve_list.contains(left) && !lost_list.contains(left)) {
                reserve_list.remove(Integer.valueOf(left));
                answer += 1;
            } else if (reserve_list.contains(right) && !lost_list.contains(right)) {
                reserve_list.remove(Integer.valueOf(right));
                answer += 1;
            }
        }
        return answer;
    }
}