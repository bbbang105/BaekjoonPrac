import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        List<Integer> lostList = new ArrayList();
        List<Integer> reserveList = new ArrayList();
        
        for (int l : lost) lostList.add(l);
        for (int r : reserve) reserveList.add(r);
        
        Collections.sort(lostList);
        Collections.sort(reserveList);
        
        List<Integer> complete = new ArrayList<>();
        // 여벌 체육복을 가져온 학생들 중, 도난 당한 학생들 처리
        if (!Collections.disjoint(lostList, reserveList)) {
            for (int r : reserveList) {
                if (lostList.contains(r)) {
                    lostList.remove(Integer.valueOf(r));
                    complete.add(Integer.valueOf(r));
                }
            }
        }
        
        for (int c : complete) reserveList.remove(Integer.valueOf(c));
        
        for (int r : reserveList) {
            int left = r - 1;
            int right = r + 1;
            
            if (lostList.contains(left)) {
                lostList.remove(Integer.valueOf(left));
            } else if (lostList.contains(right)) {
                lostList.remove(Integer.valueOf(right));
            }
        }
        
        return n - lostList.size();
    }
}