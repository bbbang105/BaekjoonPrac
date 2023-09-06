import java.util.ArrayList;

class Solution {
    public String[] solution(String[] names) {
        ArrayList<String> strList = new ArrayList<String>();

        for (int i = 0; i < names.length; i++) {
            if (i == 0 || i % 5 == 0) strList.add(names[i]);
        }   
        return strList.toArray(new String[strList.size()]);
    }
}