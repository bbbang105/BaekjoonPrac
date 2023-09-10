class Solution {
    public int solution(String my_string, String is_suffix) {
        int result = 0;
        if (my_string.endsWith(is_suffix)){
            result = 1;
        }
        return result;
    }
}