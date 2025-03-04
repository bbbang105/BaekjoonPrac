import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * [조건]
 * 1. 방은 NxN이다.
 * 2. 방의 숫자는 모두 다르다.
 * 3. 현재 방에서 상하좌우로 이동할 수 있으며, 이동하려는 방이 현재 숫자보다 정확히 1이 커야만 이동 가능하다.
 * 4. 처음 어떤 수가 적힌 방에 있어야 가장 많은 개수의 방을 이동할 수 있는지 구하여라.
 * 
 * [풀이]
 * 1. 입력을 받으며, 숫자와 좌표를 함께 저장한다.
 * 2. 숫자를 기준으로 오름차순 정렬한 후 탐색을 시작한다.
 * 3. 상하좌우로 이동하며 정확히 +1인 경우에만 이동하고 방문처리를 한다.
 * 4. 만약 해당 숫자에서 가장 많이 방문했다면, 값을 갱신한다.
 * *
 */
public class Solution {
    
    private static final int[] DX = {-1, 1, 0, 0};
    private static final int[] DY = {0, 0, -1, 1};

    private static BufferedReader br;
    private static StringTokenizer st;
    private static int[][] board;
    private static boolean[] isVisited;
    private static List<Room> rooms;
    private static int size;
    private static int startNum;
    private static int moveCount;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        int testCaseNum = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int testCase = 1; testCase <= testCaseNum; testCase++) {
            init();
            // 초기값 지정
            startNum = Integer.MAX_VALUE;
            moveCount = 0;
            findBestStartNum();
            sb.append("#").append(testCase).append(" ").append(startNum).append(" ").append(moveCount).append('\n');
        }
        System.out.print(sb);
    }

    /**
     * 초기 세팅을 진행하는 메서드.
     * 
     */
    private static void init() throws IOException {
        size = Integer.parseInt(br.readLine());
        board = new int[size][size];
        isVisited = new boolean[size * size + 1];
        rooms = new ArrayList<>();
        
        for (int rowIndex = 0; rowIndex < size; rowIndex++) {
            st = new StringTokenizer(br.readLine());
            for (int colIndex = 0; colIndex < size; colIndex++) {
                int roomNum = Integer.parseInt(st.nextToken());
                rooms.add(new Room(roomNum, rowIndex, colIndex));
                board[rowIndex][colIndex] = roomNum;
            }
        }
        Collections.sort(rooms);
    }

    /**
     * 최적의 시작 지점을 찾는 메서드.
     * 
     */
    private static void findBestStartNum() {
        int curNum;
        int curRowIndex;
        int curColIndex;
        int nextRowIndex;
        int nextColIndex;
        for (Room room : rooms) {
            
            if (isVisited[room.number]) {
                continue;
            }
            
            int index = room.number;
            int sum = 1;
            Queue<Room> q = new LinkedList<>();
            q.add(room);
            while (!q.isEmpty()) {
                Room curRoom = q.poll();
                curNum = curRoom.number;
                curRowIndex = curRoom.rowIndex;
                curColIndex = curRoom.colIndex;
                
                for (int directionIndex = 0; directionIndex < 4; directionIndex++) {
                    nextRowIndex = curRowIndex + DX[directionIndex];
                    nextColIndex = curColIndex + DY[directionIndex];
                    
                    if (nextRowIndex < 0 || nextRowIndex >= size || nextColIndex < 0 || nextColIndex >= size) {
                        continue;
                    }
                    if (board[nextRowIndex][nextColIndex] == curNum + 1) {
                        isVisited[board[nextRowIndex][nextColIndex]] = true;
                        sum++;
                        q.add(new Room(board[nextRowIndex][nextColIndex], nextRowIndex, nextColIndex));
                    }
                }
            }
            
            if (sum > moveCount) {
                moveCount = sum;
                startNum = index;
            } else if (sum == moveCount) {
                startNum = Math.min(startNum, index);
            }
        }
    }
    
    /**
     * 방의 정보가 담긴 클래스. 
     *
     */
    private static class Room implements Comparable<Room> {
        int number;
        int rowIndex;
        int colIndex;
        
        Room(int number, int rowIndex, int colIndex) {
            this.number = number;
            this.rowIndex = rowIndex;
            this.colIndex = colIndex;
        }

        @Override
        public int compareTo(Room o) {
            return this.number - o.number;
        }
    }
}
