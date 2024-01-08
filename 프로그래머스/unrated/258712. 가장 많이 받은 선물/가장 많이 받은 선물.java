import java.util.HashMap;
import java.util.Map;

public class Solution {
    Map<String, Integer> friendsMap = new HashMap<>();
    public int solution(String[] friends, String[] gifts) {
        int len = friends.length;
        createMap(friends);
        int[][] giveAndTakeArray = countGiveAndTake(len, gifts);

        int[] giftsNumber = new int[len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (i == j) continue;
                
                if (giveAndTakeArray[i][j] > giveAndTakeArray[j][i]) {
                    // 더 많이 선물을 준 경우
                    giftsNumber[i] += 1;
                } else if (giveAndTakeArray[i][j] == giveAndTakeArray[j][i]) {
                    if (countGiftScore(i, giveAndTakeArray) > countGiftScore(j, giveAndTakeArray)) {
                        // 선물 개수가 같지만, 지수가 더 높은 경우
                        giftsNumber[i] += 1;
                    }
                }
            }
        }
        
        int answer = 0;
        for (int n : giftsNumber) {
            answer = Math.max(answer, n);
        }
        
        return answer;
    }

    // 맵 생성 메소드
    private void createMap(String[] friends) {
        for (int i = 0; i < friends.length; i++) {
            friendsMap.put(friends[i],i);
        }
    }

    // 주고 받은 선물 계산 메소드
    private int[][] countGiveAndTake(int friendsNum, String[] gifts) {
        int[][] giveAndTakeArray = new int[friendsNum][friendsNum];

        for (String friends : gifts) {
            String give = friends.split(" ")[0];
            String take = friends.split(" ")[1];

            giveAndTakeArray[friendsMap.get(give)][friendsMap.get(take)] += 1;
        }

        return giveAndTakeArray;
    }

    // 선물 지수 계산 메소드
    private int countGiftScore(int index, int[][] giveAndTakeArray) {
        int score = 0;

        for (int i = 0; i < giveAndTakeArray.length; i++) {
            if (i == index) continue;

            score += giveAndTakeArray[index][i];
            score -= giveAndTakeArray[i][index];
        }

        return score;
    }
}