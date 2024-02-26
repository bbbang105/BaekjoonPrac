import java.util.*;

class Solution {
    private static Map<Integer, Stack<Integer>> graph;
    private static List<Integer> nums;
    private static boolean[] check;
    
    public int solution(int n, int[][] edge) {
        graph = new HashMap<>();
        nums = new ArrayList<>();
        check = new boolean[n + 1];
        
        for (int[] e : edge) {
            int a = e[0]; int b = e[1];
            graph.putIfAbsent(a, new Stack<>());
            graph.get(a).push(b);
            graph.putIfAbsent(b, new Stack<>());
            graph.get(b).push(a);
        }
        
        bfs(new Node(1, 0));
        
        Collections.sort(nums);
        int maxNum = nums.get(nums.size() - 1);

        return Collections.frequency(nums, maxNum);
    }
    
    private void bfs(Node start) {
        Queue<Node> q = new LinkedList<>();
        q.offer(start);
        
        while (!q.isEmpty()) {
            Node node = q.poll();
            
            int cur = node.vertex;
            int cnt = node.cnt;
            nums.add(cnt);
            check[cur] = true;
            
            while (graph.containsKey(cur) && !graph.get(cur).isEmpty()) {
                int next = graph.get(cur).pop();
                if (!check[next]) {
                    check[next] = true;
                    q.offer(new Node(next, cnt + 1));
                }
            }
        }
    }
    
    class Node {
        int vertex;
        int cnt;
        
        Node(int vertex, int cnt) {
            this.vertex = vertex;
            this.cnt = cnt;
        }
    }
}