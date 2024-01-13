import java.util.List;
import java.util.Stack;

class Solution {
    public int[] solution(int[] prices) {
        int[] answer = createAnswerArray(prices.length);
        Stack<List<Integer>> stk = new Stack<>();

        for (int i = 0; i < prices.length; i++) {
            while (!stk.isEmpty() && prices[i] < stk.peek().get(0)) {
                List<Integer> stockInfos = stk.pop();
                answer[stockInfos.get(1)] = i - stockInfos.get(1);
            }
            stk.push(List.of(prices[i], i));
        }

        return answer;
    }

    private int[] createAnswerArray(int stockCounts) {
        int[] arr = new int[stockCounts];
        for (int i = 0; i < stockCounts; i++) {
            arr[i] = (stockCounts - i - 1);
        }

        return arr;
    }
}