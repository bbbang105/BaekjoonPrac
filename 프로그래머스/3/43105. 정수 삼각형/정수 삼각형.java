class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        int leftIndex;
        int rightIndex;

        if (triangle.length == 1) {
            answer = triangle[0][0];
        } else {
            for (int i = 1; i < triangle.length; i++) {
                int[] row = triangle[i];
                for (int j = 0; j < row.length; j++) {
                    leftIndex = j - 1;
                    rightIndex = j;
                    if (leftIndex < 0) {
                        triangle[i][j] += triangle[i - 1][rightIndex];
                    } else if (rightIndex >= triangle[i - 1].length) {
                        triangle[i][j] += triangle[i - 1][leftIndex];
                    } else {
                        triangle[i][j] += Math.max(triangle[i - 1][leftIndex], triangle[i - 1][rightIndex]);
                    }
                }
            }

            for (int n : triangle[triangle.length - 1]) {
                answer = Math.max(answer, n);
            }
        }
        
        return answer;
    }
}