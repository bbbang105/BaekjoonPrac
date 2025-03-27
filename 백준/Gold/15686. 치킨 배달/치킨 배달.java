import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * [조건]
 * 1. 행 열 r c는 1부터 시작한다.
 * 2. 치킨 거리는 맨해튼 거리이다.
 * 3. 일부 치킨집을 폐업한다. (= 폐업하지 않는 치킨집 개수 M만큼 뽑는다)
 * 4. 도시의 치킨 거리를 가장 작게 만들어라.
 * 5. 도시의 크기 N은 2이상 50이하이다.
 * 6. 폐업하지 않는 치킨집 개수 M은 1이상 13이하이다.
 * 7. 0은 빈 칸, 1은 집, 2는 치킨 집이다.
 */
public class Main {
	
    private static int size;					// 도시 크기
    private static int countOfRemains;			// 남는 치킨집 개수(뽑을 개수)
    private static List<Location> houses;		// 집들의 좌표
    private static List<Location> chickens;	// 기존 치킨집들의 좌표
    private static List<Set<Integer>> chickenLocations; // 치킨집 조합 목록
    private static int result;

    public static void main(String[] args) throws IOException {
    	init();
    	findResult();
    	System.out.println(result);
    }
    
    /**
     * 결과를 도출하는 메서드.
     */
    private static void findResult() {
    	chickenLocations = new ArrayList<>();
        createNumberSet(0, new HashSet<>());  // 가능한 치킨집 조합을 생성

        for (Set<Integer> locations : chickenLocations) {
            int currentDistance = 0;
            for (Location house : houses) {
                int minDist = Integer.MAX_VALUE;
                for (int location : locations) {
                    int chickenX = chickens.get(location).x;
                    int chickenY = chickens.get(location).y;
                    minDist = Math.min(minDist, calculateDistance(house.x, chickenX, house.y, chickenY));
                }
                // 현재 치킨집 조합에서의 최소 거리
                currentDistance += minDist;
            }
            result = Math.min(result, currentDistance); // 최소로 계속 갱신
        }
    }
    
    /**
     * 맨해튼 거리를 반환하는 메서드. 
     */
    private static int calculateDistance(int x1, int x2, int y1, int y2) {
    	return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    /**
     * 가능한 치킨집 조합을 생성하는 메서드. 
     */
    private static void createNumberSet(int start, Set<Integer> set) {
        if (set.size() == countOfRemains) {
            chickenLocations.add(new HashSet<>(set));
            return;
        }

        for (int index = start; index < chickens.size(); index++) {
            set.add(index);
            createNumberSet(index + 1, set);
            set.remove(index);
        }
    }
    
    /**
     * 초기 세팅을 진행하는 메서드.
     */
    private static void init() throws IOException {
    	result = Integer.MAX_VALUE;
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        size = Integer.parseInt(st.nextToken());
        countOfRemains = Integer.parseInt(st.nextToken());

        houses = new ArrayList<>();
        chickens = new ArrayList<>();
        for (int rowIndex = 0; rowIndex < size; rowIndex++) {
            st = new StringTokenizer(br.readLine());
            for (int colIndex = 0; colIndex < size; colIndex++) {
                int info = Integer.parseInt(st.nextToken());

                if (info == 1) {
                	// 집인 경우
                    houses.add(new Location(rowIndex, colIndex));
                } else if (info == 2) {
                	// 기존 치킨 집인 경우
                    chickens.add(new Location(rowIndex, colIndex));
                }
            }
        }
    }

    /**
     * 좌표 정보를 담는 클래스.
     */
    static class Location {
        int x;
        int y;

        Location(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
