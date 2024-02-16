import java.util.*;

class Solution {
    public int solution(int[] order) {
        int main = 1;
        Stack<Integer> sub = new Stack<>();
        int answer = 0;
        
        for (int num : order) {
            if (main == num) {
                // 메인 벨트에서 처리 가능한 경우
                answer++;
                main++;
            } else if (num > main) {
                // 서브 벨트에 실어서 처리 가능한 경우
                while (num >= main) {
                    sub.push(main);
                    main++;
                }
                sub.pop();
                answer++;
            } else if (!sub.isEmpty() && num == sub.peek()) {
                // 서브 벨트에서 꺼내서 처리 가능한 경우
                sub.pop();
                answer++;
            } else {
                // 더 이상 실을 수 없는 경우
                break;
            }
        }
        
        return answer;
    }
}