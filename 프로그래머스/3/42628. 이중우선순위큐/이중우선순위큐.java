import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Solution {
    public int[] solution(String[] operations) {
        PriorityQueue<Integer> max = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> min = new PriorityQueue<>();
        StringTokenizer st;
        int[] answer;
        int delNum;

        for (String operation : operations) {
            st = new StringTokenizer(operation);
            char op = st.nextToken().charAt(0);
            int num = Integer.parseInt(st.nextToken());

            switch (op) {
                case 'I' :
                    max.offer(num);
                    min.offer(num);
                    break;
                case 'D':
                    if (max.isEmpty() || min.isEmpty()) break;
                    if (num == 1) {
                        delNum = max.poll();
                        min.remove(delNum);
                    } else if (num == -1) {
                        delNum = min.poll();
                        max.remove(delNum);
                    }
            }
        }

        if (max.isEmpty() || min.isEmpty()) {
            answer = new int[]{0,0};
        } else {
            answer = new int[]{max.peek(), min.peek()};
        }

        return answer;
    }
}