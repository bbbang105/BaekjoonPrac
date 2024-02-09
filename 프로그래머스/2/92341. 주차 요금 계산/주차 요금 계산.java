import java.util.*;
import java.util.stream.Stream;

class Solution {
    private int defaultTime;
    private int defaultFee;
    private int perUnit;
    private int perFee;
    private final int lateTime = 23 * 60 + 59; // 출차 내역이 없는 경우
    private Map<String, Integer> totalTimes;
    private Map<String, Integer> store;

    public int[] solution(int[] fees, String[] records) {
        defaultTime = fees[0];
        defaultFee = fees[1];
        perUnit = fees[2];
        perFee = fees[3];
        totalTimes = new HashMap<>();
        store = new HashMap<>();

        for (String r : records) {
            String[] temp = r.split(" ");
            String carNumber = temp[1];
            int[] times = Stream.of(temp[0].split(":")).mapToInt(Integer::parseInt).toArray();
            int minutes = times[0] * 60 + times[1];

            if (temp[2].equals("OUT")) {
                // 출차하는 경우
                int usedTime = minutes - store.get(carNumber);
                totalTimes.put(carNumber, totalTimes.getOrDefault(carNumber, 0) + usedTime);
                store.remove(carNumber);
            } else {
                // 입차하는 경우
                store.put(carNumber, minutes);
            }
        }

        // 출차되지 않은 자동차 처리
        if (!store.isEmpty()) {
            for (String carNumber : store.keySet()) {
                int usedTime = lateTime - store.get(carNumber);
                totalTimes.put(carNumber, totalTimes.getOrDefault(carNumber, 0) + usedTime);
            }
        }

        int[] answer = new int[totalTimes.size()];
        List<String> keys = new ArrayList<>(totalTimes.keySet());
        Collections.sort(keys);
        for (int i = 0; i < keys.size(); i++) {
            answer[i] = calculateFee(totalTimes.get(keys.get(i)));
        }

        return answer;
    }

    private int calculateFee(int usedTime) {
        int fee = defaultFee;
        usedTime -= defaultTime; // 기본 시간 차감
        if (usedTime > 0) {
            if (usedTime % perUnit == 0) {
                fee += (usedTime / perUnit) * perFee;
            } else {
                fee += ((usedTime / perUnit) + 1) * perFee;
            }
        }

        return fee;
    }
}