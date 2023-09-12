class Solution {
    public int[] solution(String[] name, int[] yearning, String[][] photo) {
        int[] answer = new int[photo.length];

        for (int i = 0; i < photo.length; i++) {
            int sum = 0;
            for (int j = 0; j < photo[i].length; j++) {
                String n = photo[i][j];
                for (int k = 0; k < name.length; k++) {
                    if (n.equals(name[k])) {
                        sum += yearning[k];
                        break;
                    }
                }
            }
            answer[i] = sum;
        }
        return answer;
    }
}