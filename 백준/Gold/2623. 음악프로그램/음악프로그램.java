import java.util.*;
import java.io.*;

public class Main{
	
	public static Queue<Integer> select(int[] degrees){
		Queue<Integer> q = new ArrayDeque<>();
		for (int i = 1; i < degrees.length; i++) {
			if(degrees[i] == 0) {
				q.add(i);
			}
		}
		return q;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer tokens;
		
		tokens = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(tokens.nextToken());
		int M = Integer.parseInt(tokens.nextToken());
		
		int[] degrees = new int[N+1];
		List<Integer>[] childs = new ArrayList[N+1];
		for (int i = 1; i <= N; i++) {
			childs[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			tokens = new StringTokenizer(br.readLine());
			
			int	n = Integer.parseInt(tokens.nextToken());

			int num;
			int prevNum = Integer.parseInt(tokens.nextToken());
			
			for(int j=1; j<n; j++) {
				num = Integer.parseInt(tokens.nextToken());
				
				degrees[num]++;
				childs[prevNum].add(num);
				prevNum = num;
			}
		}
		
		int numComplete = 0;
		StringBuilder sb = new StringBuilder();
		Queue<Integer> q = select(degrees);
		while(!q.isEmpty()) {
			int num = q.poll();
			sb.append(num).append("\n");
			for(int child : childs[num]) {
				degrees[child]--;
				if(degrees[child] == 0) {
					q.add(child);
				}
			}
			numComplete++;
		}
		
		System.out.println(numComplete == N ? sb : "0");
	}
}
