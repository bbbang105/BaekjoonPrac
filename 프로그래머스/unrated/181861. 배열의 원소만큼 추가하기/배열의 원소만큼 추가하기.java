import java.util.*;
class Solution {
    public List<Integer> solution(int[] arr) {
        ArrayList<Integer> arrList = new ArrayList<Integer>();
        for (int i = 0; i < arr.length; i++) {
            int n = arr[i];
            for (int j = 0; j < n; j++) {
                arrList.add(n);
            }
        }
        return arrList;
    }
}