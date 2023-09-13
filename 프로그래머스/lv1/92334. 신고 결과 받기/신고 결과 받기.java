import java.util.*;
class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int n = id_list.length;
        int[] answer = new int[n];
        // HashMap을 사용하여 id 저장
        Map<String,Integer> ht = new HashMap<String,Integer>();
        for (int i = 0; i < n; i++) {
            ht.put(id_list[i],i);
        }
        // ArrayList 2차원 배열로 이름별 신고한 사람 저장
        ArrayList<String>[] report_people = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            report_people[i] = new ArrayList<String>();
        }
        // report 배열 탐색
        for (String r : report) {
            String[] names = r.split(" ");
            String user_name = names[0]; String report_name = names[1];
            int report_id = ht.get(report_name); // 신고 당한 아이디
            // 해당 사람에게 처음 신고 당했으면 넣어줌
            if (report_people[report_id].contains(user_name) == false) {
                report_people[report_id].add(user_name);
            }
        }
        // 리스트의 길이가 k이상이라면, 처리 결과 메일 +1
        for (int i = 0; i < report_people.length; i++) {
            if (report_people[i].size() >= k) {
               for (int j = 0; j < report_people[i].size(); j++) {
                   String mail_name = report_people[i].get(j);
                   int mail_id = ht.get(mail_name);
                   answer[mail_id] += 1; // 메일 +1
               }
            }
        }
        return answer;
    }
}