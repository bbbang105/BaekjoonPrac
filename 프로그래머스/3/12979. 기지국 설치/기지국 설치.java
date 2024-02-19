class Solution {
    public int solution(int N, int[] stations, int w) {
        int answer = 0;
        int left = 1; int right = 0;
        int coverNum = (w * 2 + 1);

        for (int s : stations) {
            right = (s - w); 
            
            if (right <= 0) {
                left = (s + w + 1);
                continue;
            }
            
            int distance = (right - left);
            if (distance < 1) {
                left = (s + w + 1);
                continue;
            }
            
            if (distance % coverNum == 0) {
                answer += (distance / coverNum);
            } else {
                answer += (distance / coverNum + 1);
            }
            
            left = (s + w + 1);
        }
        
        if (left <= N) {
            int distance = (N - left + 1);
            
            if (distance % coverNum == 0) {
                answer += (distance / coverNum);
            } else {
                answer += (distance / coverNum + 1);
            }
        }

        return answer;
    }
}