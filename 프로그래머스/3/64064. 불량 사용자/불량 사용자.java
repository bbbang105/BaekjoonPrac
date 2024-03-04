import java.util.*;
import java.util.regex.*;

class Solution {
    private static List<List<String>> ids;
    private static List<String> current;
    private static Set<String> answerSet;
    private static int answer;
    private static int limit;
    
    public int solution(String[] user_id, String[] banned_id) {
        ids = new ArrayList<>();
        answerSet = new HashSet<>();
        answer = 0;
        limit = banned_id.length;
        
        for (String b : banned_id) {
            List<String> list = new ArrayList<>();
            
            // 패턴 생성
            String PATTERN = b.replace("*", "[a-z0-9]");
            
            // 가능한 아이디 추가
            for (String u : user_id) {
                if (Pattern.matches(PATTERN, u)) {
                    list.add(u);
                }
            }
            
            ids.add(list);
        }
        
        current = new ArrayList<>();
        backTracking(0);
        
        return answer;
    }
    
    private void backTracking(int index) {
        if (index == limit) {
            Collections.sort(current); // 순서에 의한 중복 방지
            String answerStr = String.join(",", current);
            if (!answerSet.contains(answerStr)) {
                answerSet.add(answerStr);
                answer++;
            }
            return;
        }
        
        for (String id : ids.get(index)) {
            if (!current.contains(id)) {
                current.add(id);
                backTracking(index + 1);
                current.remove(id);
            }
        }
    }
}