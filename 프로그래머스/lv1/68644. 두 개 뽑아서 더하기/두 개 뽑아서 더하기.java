import java.util.*;

class Solution {
    public static int[] solution(int[] numbers) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i+1; j < numbers.length; j++) {
                if (!list.contains(numbers[i] + numbers[j])) {
                    list.add(numbers[i] + numbers[j]);
                }
            }
        }
        int[] answer = new int[list.size()];
        int size = 0;
        for (int temp : list) {
            answer[size++] = temp;
        }
        Arrays.sort(answer);
        return answer;
    }
}