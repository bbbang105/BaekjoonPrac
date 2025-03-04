import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static StringBuilder result;
    private static String line;
    private static int size;
    private static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        size = Integer.parseInt(br.readLine());
        board = new int[size][size];
        for (int rowIndex = 0; rowIndex < size; rowIndex++) {
            line = br.readLine();
            for (int colIndex = 0; colIndex < size; colIndex++) {
                board[rowIndex][colIndex] = line.charAt(colIndex) - '0';
            }
        }

        result = new StringBuilder();
        compress(0, 0, size); // 처음에는 전 배열을 탐색
        System.out.println(result);
    }

    private static void compress(int startX, int startY, int moveCount) {
        int first = board[startX][startY];
        int nextMoveCount;
        boolean isAllSame = true;

        for (int rowIndex = 0; rowIndex < moveCount; rowIndex++) {
            for (int colIndex = 0; colIndex < moveCount; colIndex++) {
                if (board[startX + rowIndex][startY + colIndex] != first) {
                    isAllSame = false;
                    break;
                }
            }
            if (!isAllSame) {
                break;
            }
        }

        if (!isAllSame) {
            // 모든 값이 같지 않다면
            result.append("(");
            nextMoveCount = moveCount / 2;
            // 범위를 반으로 줄여 4분할로 다시 탐색
            compress(startX, startY, nextMoveCount);
            compress(startX, startY + nextMoveCount, nextMoveCount);
            compress(startX + nextMoveCount, startY, nextMoveCount);
            compress(startX + nextMoveCount, startY + nextMoveCount, nextMoveCount);
            result.append(")");
        } else {
            // 모든 값이 같다면 숫자 추가
            result.append(first);
        }
    }
}
