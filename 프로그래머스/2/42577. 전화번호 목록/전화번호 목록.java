import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        Arrays.sort(phone_book);
        
        boolean answer = true;
        String cur = "";
        String next = "";
        for (int i = 0; i < phone_book.length - 1; i++) {
            cur = phone_book[i];
            next = phone_book[i + 1];
            boolean isHead = true;
            for (int k = 0; k < cur.length(); k++) {
                if (cur.charAt(k) != next.charAt(k)) {
                    isHead = false;
                    break;
                }
            }
            
            if (isHead) {
                answer = false;
                break;
            }
        }
        return answer;
    }
}