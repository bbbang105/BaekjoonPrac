import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public String[] solution(String[] strings, int n) {
        String[] answer = new String[strings.length];
        String[][] arr = new String[strings.length][2];
        // 2차원 배열 생성
        for (int i = 0; i < strings.length; i++) {
            arr[i][0] = "" + strings[i].charAt(n);
            arr[i][1] = strings[i];
        }
        // 정렬
        Arrays.sort(arr, new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                if (o1[0].toString().contentEquals(o2[0].toString())) {
                    return o1[1].toString().compareTo(o2[1].toString());
                } else {
                    return o1[0].toString().compareTo(o2[0].toString());
                }
            }
        });
        // 원래 문자열만 넣어줌
        for (int i = 0; i < arr.length; i++) {
            answer[i] = arr[i][1];
        }
        return answer;
    }
}