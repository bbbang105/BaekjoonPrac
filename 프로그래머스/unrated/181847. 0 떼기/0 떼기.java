class Solution {
    public String solution(String n_str) {
        int id = 0;
        for (int i = 0; i < n_str.length(); i++) {
            if (n_str.charAt(i) != '0') {
                break;
            }
            id++;
        }
        
        if (id == 0) {
            return n_str;
        } else {
            return n_str.substring(id);
        }    
    }   
}