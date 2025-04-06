import java.util.*;

class Solution {
    
    private static String[] result;
    
    /**
    * 메인 메서드.
    */
    public String[] solution(String[] strArr) {
        findResult(strArr);
        return result;
    }
    
    /**
    * 결과를 도출하는 메서드.
    */
    private static void findResult(String[] strArr) {
        List<String> nonContainWords = new ArrayList<>();
        
        for (String str : strArr) {
            if (!str.contains("ad")) {
                nonContainWords.add(str);
            }
        }
        
        result = new String[nonContainWords.size()];
        for (int index = 0; index < nonContainWords.size(); index++) {
            result[index] = nonContainWords.get(index);
        }
    }
}
