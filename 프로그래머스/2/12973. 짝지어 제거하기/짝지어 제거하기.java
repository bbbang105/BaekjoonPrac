import java.util.Stack;

class Solution {
    public int solution(String s) {
        Stack<Character> stk = new Stack<>();
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!stk.isEmpty() && stk.peek() == c) {
                stk.pop();
                continue;
            }
            stk.push(c);
        }
        
        return stk.isEmpty() ? 1 : 0;
    }
}