class Solution {
    private static int[] answer;
    private static int[][] board;
    
    public int[] solution(int[][] arr) {
        answer = new int[2];
        board = arr;
        
        divide(0, 0, arr.length);
        
        return answer;
    }
    
    private void divide(int x, int y, int n) {
        int first = board[x][y]; // 초기 숫자
        
        for (int i = x; i < x + n; i++) {
            for (int j = y; j < y + n; j++) {
                if (board[i][j] != first) {
                    n /= 2;
                    
                    divide(x, y, n);
                    divide(x + n, y, n);
                    divide(x, y + n, n);
                    divide(x + n, y + n, n);  
                    
                    return;
                } 
            }
        }
        
        if (first == 0) answer[0]++;
        else if (first == 1) answer[1]++;
    }
}