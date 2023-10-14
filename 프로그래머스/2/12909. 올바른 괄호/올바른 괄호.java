import java.util.Stack;

class Solution {
    boolean solution(String s) {
        Stack<Character> stk = new Stack<>();
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stk.push(c);
            } else if (!stk.isEmpty()){
                stk.pop();
            } else {
                return false;
            }
        }
        
        return (!stk.isEmpty() ? false : true);
       
    }
}