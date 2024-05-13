import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> cards = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            int cardNum = Integer.parseInt(br.readLine());
            cards.offer(cardNum);
        }

        int cardSum = 0;
        // 한 묶음만 남을 때까지 반복
        while (cards.size() > 1) {
            int cardOne = cards.poll();
            int cardTwo = cards.poll();

            cardSum += (cardOne + cardTwo);
            cards.offer(cardOne + cardTwo); // 합친 카드를 다시 넣어줌
        }

        System.out.println(cardSum);
    }
}