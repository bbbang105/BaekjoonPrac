import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        // 숫자 배열을 문자열 배열로 변환
        String[] numberStrings = Arrays.stream(numbers)
                                       .mapToObj(String::valueOf)
                                       .toArray(String[]::new);
            
        // 문자열을 비교하여 내림차순으로 정렬
        Arrays.sort(numberStrings, (s1, s2) -> (s2 + s1).compareTo(s1 + s2));

        // 정렬된 문자열을 연결하여 반환
        String answer = Arrays.stream(numberStrings)
                             .reduce((s1, s2) -> s1.equals("0") ? s2 : s1 + s2)
                             .orElse("");

        return answer;
    }
}