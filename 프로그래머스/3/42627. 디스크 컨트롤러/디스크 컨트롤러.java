import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        Arrays.sort(jobs, (o1, o2) -> o1[0] - o2[0]);

        Queue<int[]> q = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]); // 작업 소요 시간을 기준으로 우선순위 큐 설정
        int time = 0; // 현재 시간
        int index = 0; // jobs 배열의 인덱스
        int answer = 0;
        
        while (!q.isEmpty() || index < jobs.length) {
            // 현재 시간까지 요청된 작업을 우선순위 큐에 추가
            while (index < jobs.length && jobs[index][0] <= time) {
                q.offer(jobs[index]);
                index++;
            }
            
            // 우선순위 큐에서 가장 소요 시간이 짧은 작업을 꺼내 처리
            if (!q.isEmpty()) {
                int[] job = q.poll();
                answer += time - job[0] + job[1]; // 현재 시간에서 작업이 요청된 시간을 빼고 작업 소요 시간을 더함
                time += job[1]; // 현재 시간 업데이트
            } else {
                // 우선순위 큐가 비어있을 경우 다음 작업의 시작 시간까지 시간을 더함
                time = jobs[index][0];
            }
        }

        return answer / jobs.length;
    }
}