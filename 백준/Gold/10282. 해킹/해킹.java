import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static class Info implements Comparable<Info>{
		int num;
		int hackedTime;
		List<DependInfo> dependInfoList;

		public Info(int num) {
			this.num = num;
			this.hackedTime = 0;
			this.dependInfoList = new ArrayList<>();
		}


		public Info(Info info, int hackedTime) {
			this.num = info.num;
			this.hackedTime = hackedTime;
			this.dependInfoList = info.dependInfoList;
		}

		@Override
		public int compareTo(Info i) {
			return this.hackedTime - i.hackedTime;
		}
	}

	static class DependInfo{
		int to;
		int time;

		public DependInfo(int to, int time) {
			this.to = to;
			this.time = time;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer tokens;
		
		/*
		 * 컴퓨터 해킹 -> 전염
		 *
		 * 해킹한 컴퓨터 번호, 의존성
		 *
		 * 총 해킹되는 컴퓨터 개수, 걸린 시간
		 *
		 * a가 b를 의존 => b가 감염되면 s초 후 a가 감염
		 *
		 * 처음 감염된 컴퓨터 c
		 *
		 * c를 의존하는 => ... =>
		 *
		 */

		int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수

		StringBuilder result = new StringBuilder();
		for (int t = 0; t < T; t++) {
			tokens = new StringTokenizer(br.readLine());

			int n = Integer.parseInt(tokens.nextToken()); // 컴퓨터 개수
			int d = Integer.parseInt(tokens.nextToken()); // 의존성 개수
			int c = Integer.parseInt(tokens.nextToken()); // 해킹당한 컴퓨터 번호

			int numHacked = 0;
			int totalTime = 0;

			Info[] computerInfo = new Info[n+1];

			for (int i = 1; i <= n; i++) {
				computerInfo[i] = new Info(i);
			}

			for (int i = 0; i < d; i++) {
				tokens = new StringTokenizer(br.readLine());
				// a가 b를 의존
				int a = Integer.parseInt(tokens.nextToken());
				int b = Integer.parseInt(tokens.nextToken());
				int s = Integer.parseInt(tokens.nextToken()); // 걸린 시간

				// b에 들어가는 의존 컴퓨터 정보 생성
				DependInfo info = new DependInfo(a,s);

				// b에 들어가는 의존 컴퓨터 정보 목록에 삽입
				Info computerInfoOfB = computerInfo[b];
				computerInfoOfB.dependInfoList.add(info);
			}

			PriorityQueue<Info> pq = new PriorityQueue<>();

			pq.add(computerInfo[c]);

			boolean[] isHacked = new boolean[n + 1];
			while(!pq.isEmpty()) {
				Info info = pq.poll();

				if(isHacked[info.num]) continue;
				isHacked[info.num] = true;

				numHacked++;
				totalTime = info.hackedTime;

				for (DependInfo dependInfo : info.dependInfoList) {
					Info toInfo = computerInfo[dependInfo.to];
					pq.add(new Info(toInfo, info.hackedTime + dependInfo.time));
				}
			}
			result.append(numHacked).append(" ").append(totalTime).append("\n");
		}

		System.out.println(result);
	}
}
