import static java.lang.Math.min;

public class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int answer = 0;
        int limitHealth = health;
        int success = 0;
        int time = 1;
        int atkIndex = 0;

        while (true) {
            if (attacks[atkIndex][0] == time) {
                // 공격을 받는 경우
                health -= attacks[atkIndex][1];
                if (health <= 0) {
                    // 플레이어가 사망한 경우
                    answer = -1;
                    break;
                }
                atkIndex += 1;
                success = 0;

                if (atkIndex == attacks.length) {
                    // 마지막 공격까지 종료된 경우
                    answer = health;
                    break;
                }
            } else {
                // 붕대를 감는 경우
                success += 1;
                health = Math.min(limitHealth, health + bandage[1]);

                if (success == bandage[0]) {
                    // 연속 성공한 경우
                    health = Math.min(limitHealth, health + bandage[2]);
                    success = 0;
                }
            }
            
            time += 1;
        }

        return answer;
    }
}
