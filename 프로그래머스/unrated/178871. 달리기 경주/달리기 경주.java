import java.util.*;
class Solution {
    public String[] solution(String[] players, String[] callings) {
        HashMap<String,Integer> map = new HashMap<>();
        // 맵에 플레이어 초기 순위대로 추가
        for (int i = 0; i < players.length; i++) {
            map.put(players[i], i);
        }
        // 순서 변경
        for (String cur_player : callings) {
            int rank = map.get(cur_player); // 불린 선수 순위
            // 앞 선수 찾기
            String forward_player = players[rank-1];
            // 불린 선수 앞으로 이동
            map.replace(cur_player, rank - 1);
            players[rank-1] = cur_player;
            // 앞 선수 뒤로 이동
            map.replace(forward_player, rank);
            players[rank] = forward_player;
        }
        return players;
    }
}