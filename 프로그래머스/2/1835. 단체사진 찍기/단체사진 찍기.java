class Solution {
    private final String[] names = {"A", "C", "F", "J", "M", "N", "R", "T"};
    private int answer;
    private String[] command;

    public int solution(int n, String[] data) {
        answer = 0;
        command = data;
        String comb = "";
        boolean[] visited = new boolean[8];
        backTraking(comb, visited);

        return answer;
    }

    private void backTraking(String comb, boolean[] visited) {
        if (comb.length() == names.length) {
            if (isPossible(comb)) answer++;
        }

        for (int i = 0; i < names.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                backTraking(comb + names[i], visited);
                visited[i] = false;
            }
        }
    }

    private boolean isPossible(String comb) {
        boolean flag = true;

        for (String cmd : command) {
            String p1 = "" + cmd.charAt(0);
            String p2 = "" + cmd.charAt(2);
            int distance = Math.abs(comb.indexOf(p1) - comb.indexOf(p2)) - 1;
            int need = cmd.charAt(4) - '0';

            char operator = cmd.charAt(3);
            if (operator == '>') {
                if (distance <= need) {
                    flag = false;
                    break;
                }
            } else if (operator == '<') {
                if (distance >= need) {
                    flag = false;
                    break;
                }
            } else {
                if (distance != need) {
                    flag = false;
                    break;
                }
            }
        }

        return flag;
    }
}