import java.util.*;

class Solution {
    private final String ENTER_MESSAGE = "님이 들어왔습니다.";
    private final String LEAVE_MESSAGE = "님이 나갔습니다.";
    
    public String[] solution(String[] record) {
        List<String[]> logs = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        
        for (String r : record) {
            String[] tmp = r.split(" ");
            String cmd = tmp[0];
            String id = tmp[1];
            
            if (cmd.equals("Enter")) {
                // 채팅방 입장
                String name = tmp[2];
                map.put(id, name);
                logs.add(new String[]{cmd, id});
            } else if (cmd.equals("Leave")) {
                // 채팅방 퇴장
                logs.add(new String[]{cmd, id});
            } else {
                // 닉네임 변경
                String name = tmp[2];
                map.put(id, name);
            }    
        }
        
        String[] answer = new String[logs.size()];
        for (int i = 0; i < logs.size(); i++) {
            String[] log = logs.get(i);
            String finalName = map.get(log[1]);
            if (log[0].equals("Enter")) {
                answer[i] = finalName + ENTER_MESSAGE;
            } else {
                answer[i] = finalName + LEAVE_MESSAGE;
            }
        }
        
        return answer;
    }
}