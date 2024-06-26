import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        List<int[]> timeTable = new ArrayList<>();

        for (int n = 0; n < N; n++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] time = new int[2];

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            time[0] = start; time[1] = end;

            timeTable.add(time);
        }

        Collections.sort(timeTable, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] != o2[1]) {
                    return o1[1] - o2[1]; // 종료 시간을 기준으로 오름차순 정렬
                } else {
                    return o1[0] - o2[0]; // 시작 시간을 기준으로 오름차순 정렬
                }
            }
        });

        /* 리스트 원소 확인용
        for (int[] t : timeTable) {
            System.out.println(Arrays.toString(t));
        } */

        int cnt = 1; // 최소 1개는 가능
        int lastEndTime = timeTable.get(0)[1]; // 가장 종료 시간이 빠른 회의

        for (int i = 1; i < N; i++) {
            int curStartTime = timeTable.get(i)[0];
            int curEndTime = timeTable.get(i)[1];

            if (curStartTime >= lastEndTime) {
                // 새로운 회의를 시작할 수 있는 경우
                lastEndTime = curEndTime;
                cnt++;

                // System.out.println("START : " + curStartTime + " END : " + curEndTime);
            }
        }

        System.out.println(cnt);
    }
}