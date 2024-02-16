import java.util.*;

class Solution {
    public int solution(int x, int y, int n) {
        int answer = -1;
        
        boolean[] visited = new boolean[y + 1];
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(x, 0));
        while (!q.isEmpty()) {
            Node node = q.poll();
            int curNum = node.location;
            int cnt = node.moveCnt;
            
            if (curNum == y) {
                answer = cnt;
                break;
            }
            
            int[] moveNumbers = new int[]{curNum + n, curNum * 2, curNum * 3};
            for (int number : moveNumbers) {
                if (number <= y && !visited[number]) {
                    q.offer(new Node(number, cnt + 1));
                    visited[number] = true;
                }
            }
        }
        
        return answer;
    }
    
    class Node {
        int location;
        int moveCnt;
        
        Node(int location, int moveCnt) {
            this.location = location;
            this.moveCnt = moveCnt;
        }
    }
}