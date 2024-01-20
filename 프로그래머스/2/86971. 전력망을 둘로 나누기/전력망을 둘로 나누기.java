import java.util.ArrayList;

class Solution {
    private int oneGroupCnt;

    public int solution(int n, int[][] wires) {
        int answer = 10000000;

        for (int i = 0; i < wires.length; i++) {
            ArrayList<Integer>[] graph = new ArrayList[n+1];
            graphInit(wires, graph, i);

            boolean[] visited = new boolean[n+1];
            oneGroupCnt = 1;
            dfs(graph, visited, i+1);
            int anotherGroupCnt = n - oneGroupCnt;  // 다른 한 그룹의 노드 개수
            answer = Math.min(answer, Math.abs(oneGroupCnt - anotherGroupCnt));
        }

        return answer;
    }

    private void dfs(ArrayList<Integer>[] graph, boolean[] visited, int now) {
        visited[now] = true;

        for (int next : graph[now]) {

            if (!visited[next]) {
                oneGroupCnt++;
                dfs(graph, visited, next);
            }
        }
    }

    private void graphInit(int[][] wires, ArrayList<Integer>[] graph, int cutIndex) {
        for (int k = 0; k < graph.length; k++) {
            // 모든 원소 ArrayList로 초기화
            graph[k] = new ArrayList<>();
        }

        for (int j = 0; j < wires.length; j++) {
            if (j == cutIndex) continue;

            int startNode = wires[j][0];
            int endNode = wires[j][1];
            graph[startNode].add(endNode);
            graph[endNode].add(startNode);
        }
    }
}