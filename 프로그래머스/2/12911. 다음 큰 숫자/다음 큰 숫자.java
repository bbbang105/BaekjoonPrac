public class Solution {
    public int solution(int first) {
        int firstCnt = convertToBinaryNumberAndcountNumberOne(first);
        int answer = first + 1;
        int answerCnt;

        while (true) {
            answerCnt = convertToBinaryNumberAndcountNumberOne(answer);
            if (firstCnt == answerCnt) {
                break;
            }
            answer++;
        }

        return answer;
    }

    private int convertToBinaryNumberAndcountNumberOne(int number) {
        int cnt = 0;

        while (number > 0) {
            if (number % 2 == 1) cnt++;
            number /= 2;
        }

        return cnt;
    }
}