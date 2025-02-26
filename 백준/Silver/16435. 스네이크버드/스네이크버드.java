import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * [조건]
 * 1. 과일의 개수 N은 1이상 1,000이하이다.
 * 2. 스네이크 버드의 초기 길이 정수 L은 1이상 10,000이하이다.
 * 3. 각 과일의 높이 h는 1이상 10,000이하이다.
 * 4. 스네이크 버드는 자신보다 아래에 있거나 같은 높이에 있는 과일들을 먹을 수 있다.
 * 5. 과일들을 먹어 늘릴 수 있는 최대 길이를 구하라.
 * 
 * [풀이]
 * 1. 과일들의 높이를 입력 받으며, 초기 길이 이하인 과일들의 개수를 카운트한다.
 * 2. 이는 '필요시에 꺼내 먹을 수 있는' 과일들의 개수이다.
 * 3. 초기 길이보다 높이가 높은 과일들만 배열에 저장한다.
 * 4. 배열을 하나씩 탐색하며, 가장 가까운 과일을 꺼내 먹기를 사용해 도달할 수 있는지 확인한다.
 * 5. 가능하다면 먹은 후 해당 높이 + 1로 길이를 갱신하고, 꺼내 먹을 수 있는 개수에서 차감한다.
 * 6. 단, 4 4 와 같이 같은 높이의 과일들이 존재할 수 있으므로 현재 L이 더 크다면 L++로 진행한다.
 * 7. 마지막 출력 전, 꺼내 먹을 과일이 남아있다면 해당 개수를 더해준다.
 *
 */
public class Main {
	
	private static int N;
	private static int L;
	private static int keepFruitsCount;
	private static List<Integer> fruits;

	public static void main(String[] args) throws IOException {
		init();
		Collections.sort(fruits);
		
		int index = 0;
		while (index < fruits.size()) {
			int curFruitHeight = fruits.get(index++);
			if (L + keepFruitsCount < curFruitHeight) {
				break;
			}
			if (L >= curFruitHeight) {
				L++;
			} else {
				int eatFruitsCount = curFruitHeight - L;
				L = curFruitHeight + 1;
				keepFruitsCount -= eatFruitsCount;
			}
		}
		L += keepFruitsCount > 0 ? keepFruitsCount : 0; // 만약 꺼내 먹을 과일이 끝까지 남아 있다면 더해줌
		System.out.println(L);
	}
	
	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 과일 개수
		L = Integer.parseInt(st.nextToken()); // 초기 길이
		fruits = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for (int index = 0; index < N; index++) {
			int curFruitHeight = Integer.parseInt(st.nextToken());
			if (curFruitHeight <= L) {
				// 꺼내먹을 과일++
				keepFruitsCount++;
				continue;
			}
			fruits.add(curFruitHeight);
		}
	}
}
