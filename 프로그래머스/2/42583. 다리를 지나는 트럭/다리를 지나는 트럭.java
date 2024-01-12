import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public static int solution(int bridgeLength, int bridgeWeight, int[] truckWeights) {
        int curWeight = 0, index = 0, time = 0; // 현재 다리에 올라간 차량들의 무게, 현재 차량 인덱스, 소요 시간
        Queue<Integer> q = new LinkedList<>();
        q.offer(0);

        while (true) {
            if (q.size() == bridgeLength) {
                // 다리에서 빠져나온 경우 -
                curWeight -= q.poll();
            }

            if (index < truckWeights.length) {
                if (bridgeWeight >= curWeight + truckWeights[index]) {
                    // 차량이 다리 위에 올라가는 경우
                    q.offer(truckWeights[index]);
                    curWeight += truckWeights[index++];
                } else {
                    // 올라가지 못하면 0을 삽입
                    q.offer(0);
                }
            } else {
                // 마지막 차량까지 다리 위에 올라간 경우
                time += bridgeLength; // 다리 길이만큼 이동해야 하므로 +
                break;
            }
            // + 1초
            time++;
        }

        return time; // 시간을 더하지 않고 종료했으므로, 처음 큐에 0을 넣어준 것에 대해서 -1을 진행하지 않음
    }
}