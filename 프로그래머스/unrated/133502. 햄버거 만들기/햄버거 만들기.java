import java.util.*;

class Solution {
    public int solution(int[] ingredient) {
        int answer = 0;

        Stack<Integer> inStack = new Stack<>();

        for (int in : ingredient) {
            inStack.push(in);

            if (inStack.size() >= 4) {
                if (inStack.get(inStack.size()-4) == 1
                    && inStack.get(inStack.size()-3)== 2
                    && inStack.get(inStack.size()-2) == 3
                    && inStack.get(inStack.size()-1) == 1) {

                    answer ++;
                    inStack.pop();inStack.pop();inStack.pop();inStack.pop();
                }

            }
        }
        return answer;
    }
}