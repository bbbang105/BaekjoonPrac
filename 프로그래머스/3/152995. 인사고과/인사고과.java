import java.util.*;

class Solution {
    public int solution(int[][] scores) {
        int len = scores.length;
        int a = scores[0][0]; int b = scores[0][1];
        int wanhoSum = a + b;
        
        Arrays.sort(scores, Comparator.comparingInt(o -> o[0] + o[1]));
        
        // 완호의 위치를 찾는 과정
        int wanho = 0;
        for (int i = 0; i < len; i++) {
            if (scores[i][0] == a && scores[i][1] == b) {
                wanho = i;
                break;
            }
        }
        
        // 완호가 1등인 경우
        if (wanho == len - 1) {
            return 1;
        }
        
        int rank = len - wanho; // 현재 기준 석차
        
        // 완호의 석차를 매길 수 있는지 확인하는 과정
        for (int i = wanho + 1; i < len; i++) {
            int nextA = scores[i][0];
            int nextB = scores[i][1];
            
            // 완호의 석차를 매길 수 없는 경우
            if (nextA > a && nextB > b) {
                return -1;
            }
        }
        
        // 상위 등수에서 석차를 매기지 못하는 사람이 몇명인지 파악하는 과정
        for (int i = wanho + 1; i < len; i++) {
            int curA = scores[i][0];
            int curB = scores[i][1];
            
            // 동석차인 경우
            if (curA + curB == wanhoSum) {
                rank--;
                continue;
            }
            
            for (int j = i + 1; j < len; j++) {
                int nextA = scores[j][0];
                int nextB = scores[j][1];
                
                if (nextA > curA && nextB > curB) {
                    rank--;
                    break;
                }
            }
        }
        
        return rank;
    }
}