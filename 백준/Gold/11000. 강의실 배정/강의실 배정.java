import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer tokens;
		
		/*
		 * S_i 시작, T_i에 끝
		 * N개 수업
		 * 최소 강의실, 모든 수업 가능
		 * 
		 * 수업이 끝난 직후 다음 수업 시작 가능
		 *
		 * 1) 시작 시간이 빠른 순으로 정렬
		 * 2) 끝나는 시간이 빠른 순으로 pq 구성
		 * 3) 앞에서부터 수업 빼옴
		 * 4) 그 수업의 시작 시간이 현재 가장 빨리 끝나는 시간보다 작다면 강의실 수 증가, pq에 넣음
		 * 5) 그 수업의 시작 시간이 현재 가장 빨리 끝나는 시간보다 크거나 같다면
		 * 	  - pq에서 수업 하나씩 pop 한 후 그 수업의 끝나는 시간이 현재 수업 시작 시간보다 같거나 작으면 강의실 수 감소
		 * 	  - pq에서 수업 하나씩 pop 한 후 그 수업의 끝나는 시간이 현재 수업 시작 시간보다 크다면 break
		 */

		int N = Integer.parseInt(br.readLine());

		int[][] times = new int[N][2];

		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(br.readLine());
			times[i][0] = Integer.parseInt(tokens.nextToken());
			times[i][1] = Integer.parseInt(tokens.nextToken());
		}

		Arrays.sort(times, Comparator.comparingInt(a -> a[0]));

		PriorityQueue<Integer> pq = new PriorityQueue<>();
		pq.add(times[0][1]);

		int maxRoom = 1;
		int curRoom = 1;
		for (int i = 1; i < N; i++) {
			int[] curTime = times[i];
			curRoom++;

			while(!pq.isEmpty() && pq.peek() <= curTime[0]){
				curRoom--;
				pq.poll();
			}
			if(maxRoom < curRoom) maxRoom = curRoom;
			pq.add(curTime[1]);
		}

		System.out.println(maxRoom);
	}
}
