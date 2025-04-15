import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [조건]
 * 1. 나무의 개수 N은 2이상 100이하이다.
 * 2. 주어지는 나무의 초기 높이는 1이상 120이하이다.
 * 3. 하루에 한 나무에게 물을 줄 수 있다. 물을 주지 않는 것도 할 수 있다.
 * 4. 홀수 번째 날에 물을 주면 1만큼, 짝수 번째 날에 물을 주면 2만큼 자란다. 날은 1일부터 시작한다.
 * 5. 모든 나무의 키가 처음에 가장 키가 컸던 나무와 같아지도록 할 수 있는 최소 날짜 수를 계산하라.
 * 
 * [풀이]
 * 1. 초기 세팅을 진행하며, 가장 키가 큰 나무의 높이를 구해둔다.
 * 2. 해당 높이를 가지고, 남은 나무들 중에서 더 자라야 하는 값을 구해서 더해준다.(remain)
 * 3. 모든 나무가 도달해야 하는 높이까지 추가 성장해야 하는 높이를 구하고, 1씩 성장해야 하는 날과 2씩 성장해야 하는 날을 분리한다.
 * 4. 짝수, 홀수를 조정하여 3일 주기를 고려한 최적의 최소 날짜를 계산한다.
 * 5. 최종적인 최소 날짜 수를 구하고 출력한다.
 */
public class Solution {
	
	private static BufferedReader br;
	private static StringTokenizer st;
	
	private static int[] trees;
	private static int size;
	private static int maxHeight;
	private static int minDays;
	
	/**
	 * 메인 메서드.
	 */
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int testCaseNum = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= testCaseNum; testCase++) {
        	init();
        	findMinDays();
            sb.append("#").append(testCase).append(" ").append(minDays).append("\n");
        }
        System.out.println(sb);
    }
    
    /**
     * 최소 날짜 수를 계산하는 메서드.
     */
    private static void findMinDays() {
    	int oddCount = 0, evenCount = 0;
        for (int height : trees) {
            int diff = maxHeight - height;
            oddCount += diff % 2; // 1씩 성장해야 하는 개수
            evenCount += diff / 2; // 2씩 성장해야 하는 개수
        }
        
        // 짝수와 홀수의 균형을 맞추기 위해 조정 과정
        int temp = Math.max(evenCount - oddCount, 0) / 3;
        oddCount += temp * 2;
        evenCount -= temp;
        
        int oddEvenMin = Math.min(oddCount, evenCount);
        minDays = oddEvenMin * 2
                + Math.max((oddCount - oddEvenMin) * 2 - 1, 0)
                + (evenCount - oddEvenMin) / 2 * 3
                + (evenCount - oddEvenMin) % 2 * 2;
    }
    
    /**
     * 초기 세팅 메서드.
     */
    private static void init() throws IOException {
    	size = Integer.parseInt(br.readLine());
        trees = new int[size];
        st = new StringTokenizer(br.readLine());
        
        maxHeight = 0;
        for (int i = 0; i < size; i++) {
            trees[i] = Integer.parseInt(st.nextToken());
            maxHeight = Math.max(maxHeight, trees[i]);
        }
    }
}
