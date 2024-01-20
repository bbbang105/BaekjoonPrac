class Solution {
    static boolean[] visited;
    static int answer = 0;

    public static int solution(int health, int[][] dungeons) {
        visited = new boolean[dungeons.length];
        adventure(health, dungeons, 0);
        return answer;
    }

    private static void adventure(int curHealth, int[][] dungeons, int clearCount) {
        answer = Math.max(answer, clearCount);

        for (int i = 0; i < dungeons.length; i++) {
            if (!visited[i]) {
                int atLeast = dungeons[i][0];
                int consumption = dungeons[i][1];

                if (curHealth >= atLeast) {
                    visited[i] = true;
                    adventure(curHealth - consumption, dungeons, clearCount + 1);
                    visited[i] = false;
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(solution(80, new int[][]{{80, 20}, {50, 40}, {30, 10}}));
    }
}