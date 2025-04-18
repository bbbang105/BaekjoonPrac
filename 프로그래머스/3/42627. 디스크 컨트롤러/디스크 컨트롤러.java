import java.util.*;
import java.io.*;

class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;
        PriorityQueue<Job> pq = new PriorityQueue<>();
        Arrays.sort(jobs, (o1, o2) -> o1[0] - o2[0]);
        
        int curTime = 0; // 현재 시간
        int index = 0;   // 현재 인덱스
        int perTime = 0; // 작업 반환 시간
        Job curJob;
        
        while (index < jobs.length || !pq.isEmpty()) {
            while (index < jobs.length && jobs[index][0] <= curTime) {
                // 현재 시간까지 요청 가능한 작업들을 모두 우선순위 큐에 추가
                pq.offer(new Job(jobs[index][0], jobs[index][1], index));
                index++;
            }
            
            if (!pq.isEmpty()) {
                // 1. 큐가 비어있지 않은 경우 -> 작업을 꺼내 처리함
                curJob = pq.poll();
                perTime = (curTime + curJob.spendTime) - curJob.requestTime; // 최종 종료 시간 - 최초 요청 시간 == 각 작업 반환 시간
                curTime += curJob.spendTime; // 현재 시각 업데이트
                answer += perTime;
            } else {
                // 2. 큐가 비어있는 경우 -> 다음 작업 요청 시간으로 바로 이동
                curTime = jobs[index][0];
            }
        }
        return (int) answer / jobs.length;
    }
    
    static class Job implements Comparable<Job> {
        int requestTime;
        int spendTime;
        int index;
        
        Job(int requestTime, int spendTime, int index) {
            this.requestTime = requestTime;
            this.spendTime = spendTime;
            this.index = index;
        }
        
        @Override
        public int compareTo(Job j) {
            if (this.spendTime == j.spendTime) {
                if (this.requestTime == j.requestTime) {
                    return this.index - j.index;
                }
                return this.requestTime - j.requestTime;
            }
            return this.spendTime - j.spendTime;
        }
    }
}
