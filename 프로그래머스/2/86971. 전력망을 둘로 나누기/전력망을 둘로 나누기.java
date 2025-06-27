import java.util.*;

class Solution {
    
    private static Map<Integer, List<Integer>> map;
    private static boolean[] visited;
    
    public int solution(int n, int[][] wires) {
        map = new HashMap<>();
        
        // 맵 구성
        for (int i = 1; i <= n; i++) {
            map.put(i, new ArrayList<>());
        }
        int a, b;
        for (int[] wire : wires) {
            a = wire[0];
            b = wire[1];
            map.get(a).add(b);
            map.get(b).add(a);
        }
        // BFS로 탐색
        int answer = n;
        for (int[] wire : wires) {
            visited = new boolean[n + 1];
            int aGroupCount = bfs(wire[0], wire[1]);
            int bGroupCount = n - aGroupCount;
            answer = Math.min(answer, Math.abs(aGroupCount - bGroupCount));
        }
        
        return answer;
    }
    
    private static int bfs(int a, int b) {
        int count = 1;
        Queue<Integer> q = new LinkedList<>();
        q.offer(a);
        
        while (!q.isEmpty()) {
            int cur = q.poll();
            if (visited[cur]) {
                continue;
            }
            visited[cur] = true;
            
            for (int next : map.get(cur)) {
                if (next != b && !visited[next]) {
                    count++;
                    q.offer(next);
                }
            }            
        }
        
        return count;
    }
}
