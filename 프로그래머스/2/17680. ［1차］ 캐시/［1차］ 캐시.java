import java.util.LinkedList;
import java.util.Queue;

class Solution {
    private final int CACHE_HIT = 1;
    private final int CACHE_MISS = 5;
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        int len = cities.length;

        if (cacheSize != 0) {
            Queue<String> cache = new LinkedList<>();

            for (String c : cities) {
                String city = c.toLowerCase();
                if (cache.contains(city)) {
                    // 캐시 히트
                    answer += CACHE_HIT;
                    // 최근 사용으로 변경
                    cache.remove(city);
                    cache.offer(city);
                } else {
                    // 캐시 미스
                    answer += CACHE_MISS;
                    cache.offer(city);
                }
                // 사이즈를 초과할 경우, 가장 오래된 캐시를 제거
                if (cache.size() > cacheSize) cache.poll();
            }
        } else {
            // 캐시 사이즈가 0인 경우
            answer = len * CACHE_MISS;
        }

        return answer;
    }
}