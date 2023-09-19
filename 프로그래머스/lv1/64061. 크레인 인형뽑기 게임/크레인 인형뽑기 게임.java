import java.util.Stack;

class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        Stack<Integer> stk = new Stack<>();

        for (int m : moves) {
            m -= 1;
            for (int i = 0; i < board.length; i++) {
                if (board[i][m] != 0) {
                    int num = board[i][m];
                    board[i][m] = 0;
                    if (!stk.isEmpty() && stk.peek() == num){
                        stk.pop();
                        answer += 2;
                    } else {
                        stk.push(num);
                    }
                    break;
                }
            }
        }
        return answer;
    }
}