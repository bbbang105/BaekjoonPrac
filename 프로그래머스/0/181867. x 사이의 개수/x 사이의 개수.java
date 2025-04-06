import java.util.*;

/**
*
*
*/
class Solution {
    
    private static int[] result;
    
    public int[] solution(String myString) {
        findResult(myString);
    
        return result;
    }
    
    /**
    * 결과를 도출하는 메서드.
    */
    private static void findResult(String myString) {
        String[] strs = myString.split("x");
        
        if (myString.charAt(myString.length() - 1) == 'x') {
            result = new int[strs.length + 1];
        } else {
            result = new int[strs.length];
        }
        
        for (int index = 0; index < strs.length; index++) {
            result[index] = strs[index].length();
        }
        
        System.out.print(Arrays.toString(strs));
    }
}