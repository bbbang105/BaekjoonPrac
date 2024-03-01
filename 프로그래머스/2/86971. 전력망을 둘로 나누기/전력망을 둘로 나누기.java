import java.util.*;

class Solution {  
    private static Map<Integer, List<Integer>> map = new HashMap<>();
    private static boolean[] visited;
    
    public int solution(int n, int[][] wires) {
        
        for (int i = 1; i < n + 1; i++) map.put(i, new ArrayList<>());
        for (int[] wire : wires) {
            int v = wire[0]; int e = wire[1];
            map.get(v).add(e);
            map.get(e).add(v);
        }
        
        int answer = 100;
        
        for (int[] wire : wires) {
            visited = new boolean[n + 1];
            int a = wire[0]; int b = wire[1];
            map.get(a).remove(Integer.valueOf(b));
            map.get(b).remove(Integer.valueOf(a));
            int groupCnt = 0;
        
            for (int i = 1; i < n + 1; i++) {
                if (!visited[i]) {
                    groupCnt = bfs(i);
                    break;
                }
            }
            
            int anotherGroupCnt = n - groupCnt;
            
            answer = Math.min(answer, Math.abs(groupCnt - anotherGroupCnt));
            map.get(a).add(b);
            map.get(b).add(a);
        }

        return answer;
    }
    
    private int bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        visited[start] = true;
        int cnt = 1;

        while(!q.isEmpty()) {
            int n = q.poll();
            
            if (!map.get(n).isEmpty()) {
                for (int i = 0; i < map.get(n).size(); i++) {
                    int next = map.get(n).get(i);
                    if (!visited[next]) {
                        q.offer(next);
                        visited[next] = true;
                        cnt++;
                    }
                }
            }
        }
        
        return cnt;
    }
}