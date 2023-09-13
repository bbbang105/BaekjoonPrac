import java.util.*;
class Solution {
    public List<Integer> solution(String today, String[] terms, String[] privacies) {
        ArrayList<Integer> answer = new ArrayList<>();
        HashMap<String,Integer> map = new HashMap<>();
        // 현재 날짜
        int date = getDate(today);
        // 약관별 유효기간
        for (String t : terms) {
            String[] term = t.split(" ");
            map.put(term[0], Integer.parseInt(term[1]));
        }
        for (int i = 0; i < privacies.length; i++) {
            String[] privacy = privacies[i].split(" ");
            if (getDate(privacy[0]) + map.get(privacy[1]) * 28 <= date) {
                answer.add(i+1);
            }
        }
        return answer;
    }
    // 날짜를 일수로 변환해 주는 함수
    private int getDate(String today) {
        String[] date = today.split("\\.");
        int year = Integer.parseInt(date[0]);
        int month = Integer.parseInt(date[1]);
        int day = Integer.parseInt(date[2]);
        return (year * 12 * 28) + (month * 28) + day;
    }
}