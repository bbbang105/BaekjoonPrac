import java.util.List;
import java.util.ArrayList;

class Solution {
    public int solution(String sk, String[] skill_trees) {
        int answer = 0;
        List<String> skill = new ArrayList<>();
        for (int i = 0; i < sk.length(); i++) skill.add(String.valueOf(sk.charAt(i)));
        
        for (String st : skill_trees) {
            boolean flag = true;
            int index = 0;
            for (int j = 0; j < st.length(); j++) {
                String s = String.valueOf(st.charAt(j));
                if (skill.contains(s)) {
                    if (s.equals(skill.get(index))) index++;
                    else {
                        flag = false;
                        break;
                    }
                }
            }
            
            if (flag) answer++;
        }
        
        return answer;
    }
}