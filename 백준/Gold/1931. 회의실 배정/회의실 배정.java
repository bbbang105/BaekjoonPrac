import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Meeting[] meetings = new Meeting[N];
		
		StringTokenizer st;
		for (int index = 0; index < N; index++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			meetings[index] = new Meeting(start, end);
		}
		
		Arrays.sort(meetings);
		List<Meeting> result = new ArrayList<>();
		result.add(meetings[0]);
		
		for (int index = 1; index < N; index++) {
			if (result.get(result.size() - 1).end <= meetings[index].start) {
				// 이미 배정된 마지막 회의의 종료 시간보다 다음 고려 회의 시작 시간이 같거나 크다면 배정 가능!
				result.add(meetings[index]);
			}
		}
		
		System.out.println(result.size());
	}

	static class Meeting implements Comparable<Meeting>{
		int start;
		int end;
		
		Meeting(int start, int end) {
			this.start = start;
			this.end = end;
		}

		// 기본적으로 종료 시간을 기준으로 오름차순 정렬
		// 만약, 종료 시간이 같다면 시작 시간이 빠른 순으로 오름차순 정렬 -> (3,3)과 같이 시작 시간 == 종료 시간이 경우를 대비하기 위함.
		@Override
		public int compareTo(Meeting o) {
			if (this.end == o.end) {
				return Integer.compare(this.start, o.start);
			}
			return Integer.compare(this.end, o.end);
		}
	}
}
