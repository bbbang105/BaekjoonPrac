import java.util.*;

class Solution {
    private static Set<Integer> numberSet = new HashSet<>();
    private static boolean[] check;
    
    public int solution(String numbers) {
        createNumberSet("", numbers);
        int maxNum = Collections.max(numberSet);
        check = new boolean[maxNum + 1];
        checkPrimeNumber(maxNum);
        
        int answer = 0;
        for (int n : numberSet) {
            if (!check[n]) {
                answer++;
            }
        }
    
        return answer;
    }
    
    private void createNumberSet(String comb, String other) {
        if (!comb.equals("")) numberSet.add(Integer.parseInt(comb));
        
        for (int i = 0; i < other.length(); i++) {
            createNumberSet(comb + other.charAt(i), other.substring(0, i) + other.substring(i + 1));
        }
    }
    
    private void checkPrimeNumber(int n) {
        check[0] = true; check[1] = true;
        
        for (int i = 2; i < (int) (Math.sqrt(n)) + 1; i++) {
            int m = 2;
            while (i * m <= n) {
                if (!check[i * m]) check[i * m] = true;
                m++;
            }
        }
    }
}