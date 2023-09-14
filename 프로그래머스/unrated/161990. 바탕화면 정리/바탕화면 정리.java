class Solution {
    public int[] solution(String[] wallpaper) {
        int[] answer = new int[4];
        int sx = 51; int sy = 51; int ex = 0; int ey = 0;
        for (int i = 0; i < wallpaper.length; i++) {
            for (int j = 0; j < wallpaper[i].length(); j++) {
                if (wallpaper[i].charAt(j) == '#') {
                    sx = Math.min(i,sx); ex = Math.max(i,ex);
                    sy = Math.min(j,sy); ey = Math.max(j,ey);
                }
            }
        }
        answer[0] = sx; answer[1] = sy; answer[2] = ex + 1; answer[3] = ey + 1;
        return answer;
    }
}