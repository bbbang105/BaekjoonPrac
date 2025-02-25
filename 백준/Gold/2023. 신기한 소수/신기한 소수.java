import java.io.*;
import java.util.*;

public class Main {

	private static final int[] ONE_DIGIT_PRIME_NUMS = {2, 3, 5, 7};
	
    private static boolean[] visited;
    private static List<Integer> primeNumbers;
    private static int digit;
    private static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        digit = Integer.parseInt(br.readLine().trim());
        sb = new StringBuilder();

        findAmazingPrimeNumbers();
        System.out.print(sb);
    }
    
    /**
     * 신기한 소수를 찾는 메서드.
     */
    private static void findAmazingPrimeNumbers() {
        // 1자리 소수만 미리 탐색
        for (int prime : ONE_DIGIT_PRIME_NUMS) {
            findAmazingPrimeNum(prime, 1);
        }
    }

    /**
     * DFS를 이용한 신기한 소수 탐색
     * @param num 현재까지 만들어진 숫자
     * @param length 현재 자릿수
     */
    private static void findAmazingPrimeNum(int num, int length) {
        if (length == digit) {
            sb.append(num).append('\n');
            return;
        }

        for (int nextDigit = 1; nextDigit <= 9; nextDigit += 2) { // 짝수는 소수 불가
            int newNum = num * 10 + nextDigit;
            if (isPrime(newNum)) {
            	findAmazingPrimeNum(newNum, length + 1);
            }
        }
    }

    /**
     * 소수인지 판별하는 메서드.
     */
    private static boolean isPrime(int num) {
        if (num < 2) return false;
        for (int divideNum = 2; divideNum * divideNum <= num; divideNum++) {
            if (num % divideNum == 0) return false;
        }
        return true;
    }
}
