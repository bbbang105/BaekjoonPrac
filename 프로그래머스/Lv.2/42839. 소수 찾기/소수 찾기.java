import java.util.*;

class Solution {
    HashSet<Integer> numberSet = new HashSet<>();
    boolean[] isPrimeCheck;
    public int solution(String numbers) {
        int answer = 0;
        // 모든 숫자 조합 생성
        createNumberSet("", numbers);
        // 동적 할당을 위한 최대 숫자 생성
        int maxNum = createMaxNumber(numbers);
        // 에라토스테네스의 체 사용
        isPrime(maxNum);
        // HashSet에 들어 있는 값을 체에 걸러 결과 도출
        for (int number : numberSet) {
            if (isPrimeCheck[number]) {
                answer++;
            }
        }

        return answer;
    }

    // 숫자 조합을 만드는 메소드
    private void createNumberSet(String comb, String other) {
        if (!comb.equals("")) {
            numberSet.add(Integer.parseInt(comb));
        }

        for (int i = 0; i < other.length(); i++) {
            createNumberSet(comb + other.charAt(i), other.substring(0,i) + other.substring(i+1));
        }
    }

    // 최대 숫자를 만드는 메소드
    private int createMaxNumber(String numbers) {
        String maxNum = "";
        for (int i = 0; i < numbers.length(); i++) {
            maxNum += "9";
        }

        return Integer.valueOf(maxNum);
    }

    // 에라토스테네스의 체
    private void isPrime(int n) {
        isPrimeCheck = new boolean[n+1]; // 배열 동적 할당

        // 전부 true로 변경해줌
        Arrays.fill(isPrimeCheck, true);

        isPrimeCheck[0] = isPrimeCheck[1] = false; // 0과 1은 소수가 아님

        // 제곱근까지만 반복
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (isPrimeCheck[i]) {
                // 이전은 이미 확인했으므로, i * i부터 확인
                for (int j = i * i; j <= n; j += i) {
                    isPrimeCheck[j] = false;
                }
            }
        }
    }
}