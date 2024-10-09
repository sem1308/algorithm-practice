import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static class Homework implements Comparable<Homework>{
		int score;
		boolean isSelected;

		public Homework(int score) {
			this.score = score;
		}

		public void select() {
			isSelected = true;
		}

		@Override
		public int compareTo(Homework o) {
			return o.score - this.score;
		}

		@Override
		public String toString() {
			return "Homework{" +
					"score=" + score +
					'}';
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer tokens;
		
		/*
		 * 과제마다 얻을 수 있는 점수 존재
		 * 과제마다 마감일 존재
		 *
		 * 점수 최댓값 구하기
		 */

		int MAX_D = 1000;

		PriorityQueue<Homework>[] pqs = new PriorityQueue[MAX_D];
		for (int i = 0; i < MAX_D; i++) {
			pqs[i] = new PriorityQueue<>();
		}

		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(tokens.nextToken()); // 남은 일수
			int w = Integer.parseInt(tokens.nextToken()); // 점수

			Homework homework = new Homework(w);
			for (int j = 0; j < d; j++) {
				pqs[j].offer(homework);
			}
		}

		int answer = 0;
		for (int i = MAX_D-1; i >= 0; i--) {
			PriorityQueue<Homework> pq = pqs[i];
			while(!pq.isEmpty()){
				Homework homework = pq.poll();
				if(!homework.isSelected){
					homework.select();
					answer += homework.score;
					break;
				}
			}
		}

		System.out.println(answer);
	}
}
