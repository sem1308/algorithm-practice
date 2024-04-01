import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main{
	
	static int N, M;
	static int[] degrees;
	static PriorityQueue<Integer> problems;
	static List<Integer>[] nextProblems;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer tokens;
		
		tokens = new StringTokenizer(br.readLine());
		
		/*
		 * 3->5->1
		 * 2->4
		 * 
		 * 4->2
		 * 3->1
		 * 
		 * degree가 0인 것들 중 가장 작은 값 넣기
		 * 
		 */
		
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		
		nextProblems = new ArrayList[N+1];
		degrees = new int[N+1];
		
		problems = new PriorityQueue<>();
		for (int i = 1; i <= N; i++) {
			nextProblems[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			tokens = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(tokens.nextToken());
			int B = Integer.parseInt(tokens.nextToken());
			degrees[B]++;
			nextProblems[A].add(B);
		}
		
		for(int i = 1; i<=N; i++) {
			if(degrees[i] == 0) {
				degrees[i] = -1;
				problems.add(i);				
			}
		}
		
		StringBuilder sb = new StringBuilder();
		while(!problems.isEmpty()) {

			int problem = problems.poll();
			
			sb.append(problem).append(" ");
			
			for (int p : nextProblems[problem]) {
				if(--degrees[p] == 0) {
					problems.add(p);
				}
			}
		}
		
		System.out.println(sb);
	}
}
