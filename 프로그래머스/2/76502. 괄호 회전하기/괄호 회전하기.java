import java.util.Stack;

class Solution {
    public static int solution(String s) {
        int res = 0;
        // x가 0부터 시작
        for (int i = 0; i < s.length(); i++) {
            Stack<Character> stk = new Stack<>();
            boolean flag = true;
            for (int j = i; j < s.length() + i; j++) {
                char c = s.charAt(j % s.length());
                // 스택에 추가
                if (c == '[' || c == '(' || c == '{') {
                    stk.push(c);
                }
                // 짝이 맞는지 확인
                else if (!stk.isEmpty()){
                    char p = stk.peek();
                    if (p == '[' && c == ']') stk.pop();
                    else if (p == '(' && c == ')') stk.pop();
                    else if (p == '{' && c == '}') stk.pop();
                    else {flag = false; break;}
                }
                else {flag = false; break;}
            }
            // 스택이 비었으면 성공
            if (flag && stk.isEmpty()) res++;
        }
        return res;
    }
    // 테스트 케이스
    public static void main(String args[]) {
        System.out.println(solution("[](){}"));
    }
}