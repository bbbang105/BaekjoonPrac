import java.util.*;

class Solution {
    public static int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
        HashMap<Integer, Double> map = new HashMap<>();
        int[] userFailcnts = new int[N+2];
        int[] userTotalCnts = new int[N+1];

        // 스테이지 별 머물러 있는 플레이어 수
        for (int s : stages) {
            userFailcnts[s]++;
        }
        // 스테이지 별 도달한 플레이어 수
        userTotalCnts[N] = userFailcnts[N] + userFailcnts[N+1];
        for (int i = N-1; i >= 1; i--) {
            userTotalCnts[i] = userFailcnts[i] + userTotalCnts[i+1];
        }
        // 실패율 계산
        for (int i = 1; i < userTotalCnts.length; i++) {
            if (userFailcnts[i] == 0 || userTotalCnts[i] == 0) {
                map.put(i,0.0);
            } else {
                map.put(i, (double) userFailcnts[i] / userTotalCnts[i]);
            }
        }
        // 실패율 기준으로 내림차순 정렬
        List<Integer> list = new ArrayList<>(map.keySet());
        Collections.sort(list, (o1,o2) -> map.get(o2).compareTo(map.get(o1)));
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }
}