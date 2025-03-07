import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * [조건]
 * 1. 정점 수 V는 3이상 1,000이하이다.
 * 2. 간선 수 E는 2이상 3,000이하이다.
 * 3. 정점 번호가 나란히 주어졌을 때, 앞 정점이 뒷 정점보다 먼저 수행되어야 하는 작업을 의미한다.
 * 4. 그래프에는 사이클이 존재하지 않는다.(위상정렬이 가능) 
 * 5. 작업 순서를 구하여 공백으로 구분한 후 출력하라.
 * 
 * [풀이]
 * 1. 위상정렬을 활용하여 해결한다.
 */
public class Solution {
	
	// 위상정렬
    private static int countOfVertex;   // 정점 수
    private static int countOfEdge; 	// 간선 수
    private static int[] edgeCount;	 	// 진입차수 저장 배열
    private static ArrayList<ArrayList<Integer>> graph; // 그래프
    private static Queue<Integer> q;    // 진입차수가 0인 정점을 담을 큐
    private static StringBuilder result;
	// 입출력 관련
	private static BufferedReader br;
    private static StringTokenizer st;
    private static StringBuilder finalResult;
	
    public static void main(String[] args) throws IOException {
    	
    	br = new BufferedReader(new InputStreamReader(System.in));
    	finalResult = new StringBuilder();
    	for (int testCase = 1; testCase <= 10; testCase++) {
    		init();
        	topologySort();
        	finalResult.append("#").append(testCase).append(" ").append(result).append('\n');
    	}
        System.out.println(finalResult);
    }
    
    /**
     * 위상정렬
     * 
     */
    private static void topologySort() {
    	// 큐가 빌 때까지 반복
        while (!q.isEmpty()) {
            int vertexNum = q.poll(); // 진입차수가 0인 정점
            result.append(vertexNum).append(" "); // 꺼낸 후 출력값에 추가
            List<Integer> vertexsAtBehind = graph.get(vertexNum); // 연결된 노드들 (본인보다 뒤에 서는 학생들)

            for (int vertexAtBehind : vertexsAtBehind) {
                edgeCount[vertexAtBehind]-- ; // 뒤에 있는 학생 진입차수--(간선 제거)
                if (edgeCount[vertexAtBehind] == 0) {
                	// 감소 후 진입차수가 0이 되면 큐에 넣어줌
                    q.offer(vertexAtBehind);
                }
            }
        }
    }
    
    /*
     * 초기 세팅을 진행하는 메서드.
     *  
     */
    private static void init() throws IOException {
    	result = new StringBuilder();
    	st = new StringTokenizer(br.readLine());
    	countOfVertex = Integer.parseInt(st.nextToken());
    	countOfEdge = Integer.parseInt(st.nextToken());
    	edgeCount = new int[countOfVertex + 1];
    	
       	graph = new ArrayList<ArrayList<Integer>>();
    	for (int index = 0; index < countOfVertex + 1; index++) {
    		graph.add(new ArrayList<Integer>());
    	}
    	
    	st = new StringTokenizer(br.readLine());
    	for (int index = 0; index < countOfEdge; index++) {
        	int A = Integer.parseInt(st.nextToken());
        	int B = Integer.parseInt(st.nextToken());
        	graph.get(A).add(B);
            edgeCount[B]++; // 진입차수(본인을 가리키는 노드 개수)++
    	}
    	
    	q = new LinkedList<>();
        // 초기 진입차수가 0인 값 큐에 넣기
        for (int index = 1; index < edgeCount.length; index++) {
            if (edgeCount[index] == 0) {
                q.offer(index);
            }
        }
    }
}
