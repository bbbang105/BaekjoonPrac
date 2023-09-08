class Solution {
    public int solution(int a, int b) {
        String str_a = Integer.toString(a); 
        String str_b = Integer.toString(b);
        String a1 = str_a + str_b;
        String a2 = str_b + str_a;
        int ab = Integer.parseInt(a1); int ba = Integer.parseInt(a2);
        if (ab >= ba) {
            return ab;
        } else {
            return ba;
        }
    }
}