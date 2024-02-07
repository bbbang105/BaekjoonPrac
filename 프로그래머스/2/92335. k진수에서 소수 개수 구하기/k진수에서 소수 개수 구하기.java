import java.util.stream.Stream;

class Solution {
    public int solution(int n, int k) {
        String convertedNumber = convertToKNumber(n, k);
        long[] nums = Stream.of(convertedNumber.split("0+")).mapToLong(Long::parseLong).toArray();
        
        int answer = 0;
        for (long number: nums) {
            if (isPrime(number)) answer++;
        }

        return answer;
    }

    private String convertToKNumber(int n, int k) {
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            sb.append(n % k);
            n /= k;
        }

        return sb.reverse().toString();
    }

    private boolean isPrime(long number) {
        boolean flag = true;
        if (number == 1) flag = false;
        else if (number > 2) {
            for (int i = 2; i < ((int) Math.sqrt(number)) + 1; i++) {
                if (number % i == 0) {
                    flag = false;
                    break;
                }
            }
        }

        return flag;
    }
}