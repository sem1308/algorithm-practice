import java.util.*;
import java.io.*;

public class Main{
	
	public static List<Integer> select(int[] degrees){
		List<Integer> list = new ArrayList<>();
		for (int i = 1; i < degrees.length; i++) {
			if(degrees[i] == 0) {
				degrees[i] = -1;
				list.add(i);
			}
		}
		return list;
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
		while(true) {
			List<Integer> list = select(degrees);
			
			if(list.isEmpty())break;			
			
			for(int num : list) {
				sb.append(num).append("\n");
				for(int child : childs[num]) {
					degrees[child]--;
				}
			}
			
			numComplete += list.size();
		}
		
		System.out.println(numComplete == N ? sb : "0");
	}
}
