import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        final int len = numbers.length;
        int[] answer = new int[len];
        Arrays.fill(answer, -1);
        Stack<Integer> stk = new Stack<>();

        for (int i = len - 1; i > -1; i--) {
            int num = numbers[i];

            while (!stk.isEmpty() && num >= stk.peek()) {
                stk.pop();
            }
            // 뒷 큰수가 있는 경우
            if (!stk.isEmpty()) {
                answer[i] = stk.peek();
            }
            // 현재 숫자 추가
            stk.push(num);
        }

        return answer;
    }
}