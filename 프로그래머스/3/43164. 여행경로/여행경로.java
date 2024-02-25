import java.util.*;

class Solution {
    private static List<String> answer;
    private static Map<String, PriorityQueue<String>> flights; // 알파벳순을 위해 우선순위 큐 사용
    
    public String[] solution(String[][] tickets) {
        answer = new ArrayList<>();
        flights = new HashMap<>();
        
        for (String[] ticket : tickets) {
            flights.putIfAbsent(ticket[0], new PriorityQueue<>());
            flights.get(ticket[0]).offer(ticket[1]);
        }
        
        dfs("ICN"); // 인천부터 시작
        
        return answer.toArray(new String[0]);
    }
    
    private void dfs(String airport) {
        // 방문 가능한 모든 도시 방문
        while (flights.containsKey(airport) && !flights.get(airport).isEmpty()) {
            dfs(flights.get(airport).poll());
        }
        
        answer.add(0, airport); // 역순 방지를 위해 맨 앞에 추가해줌
    }
}