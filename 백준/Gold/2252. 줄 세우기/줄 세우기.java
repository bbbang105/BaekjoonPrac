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
 * 1. 학생 수  N은 1이상 32,000이하이다.
 * 2. 키를 비교한 횟수 M은 1이상 100,000이하이다.
 * 3. 두 학생 번호  A,B가 주어졌을 때, A가 B보다 더 작음(앞에 서야 함)을 의미한다.
 * 
 * [풀이]
 * 1. 위상정렬을 활용하여 해결한다.
 */
public class Main {
	
	// 위상정렬
    private static int countOfStudent;   // 학생 수
    private static int countOfComparing; // 비교 횟수
    private static int[] edgeCount;	 	 // 진입차수 저장 배열
    private static ArrayList<ArrayList<Integer>> graph; // 그래프
    private static Queue<Integer> q;
	// 입출력 관련
	private static BufferedReader br;
    private static StringTokenizer st;
    private static StringBuilder sb;
	
    public static void main(String[] args) throws IOException {
    	init();
    	topologySort();
        System.out.println(sb);
    }
    
    /**
     * 위상정렬
     * 
     */
    private static void topologySort() {
    	// 큐가 빌 때까지 반복
        while (!q.isEmpty()) {
            int studentNumber = q.poll(); // 진입차수가 0인 노드
            sb.append(studentNumber).append(" "); // 꺼낸 후 출력값에 추가
            List<Integer> studentsAtBehind = graph.get(studentNumber); // 연결된 노드들 (본인보다 뒤에 서는 학생들)

            for (int studentAtBehind : studentsAtBehind) {
                edgeCount[studentAtBehind]-- ; // 뒤에 있는 학생 진입차수--(간선 제거)
                if (edgeCount[studentAtBehind] == 0) {
                	// 감소 후 진입차수가 0이 되면 큐에 넣어줌
                    q.offer(studentAtBehind);
                }
            }
        }
    }
    
    /*
     * 초기 세팅을 진행하는 메서드.
     *  
     */
    private static void init() throws IOException {
    	sb = new StringBuilder();
    	br = new BufferedReader(new InputStreamReader(System.in));
    	st = new StringTokenizer(br.readLine());
    	countOfStudent = Integer.parseInt(st.nextToken());
    	countOfComparing = Integer.parseInt(st.nextToken());
    	edgeCount = new int[countOfStudent + 1];
    	
       	graph = new ArrayList<ArrayList<Integer>>();
    	for (int index = 0; index < countOfStudent + 1; index++) {
    		graph.add(new ArrayList<Integer>());
    	}
    			
    	for (int index = 0; index < countOfComparing; index++) {
    		st = new StringTokenizer(br.readLine());
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
