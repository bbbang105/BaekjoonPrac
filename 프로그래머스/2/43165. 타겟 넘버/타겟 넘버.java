import java.util.LinkedList;
import java.util.Queue;

class Solution {
    private int answer;

    public int solution(int[] numbers, int target) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(0, 0));
        int len = numbers.length;
        int answer = 0;

        while (!q.isEmpty()) {
            Node node = q.poll();
            if (node.index == len) {
                if (node.sum == target) answer++;
                continue;
            }

            q.offer(new Node(node.sum + numbers[node.index], node.index + 1));
            q.offer(new Node(node.sum - numbers[node.index], node.index + 1));
        }

        return answer;
    }

    class Node {
        int sum;
        int index;

        Node(int sum, int index) {
            this.sum = sum;
            this.index = index;
        }
    }
}