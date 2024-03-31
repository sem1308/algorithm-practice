import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main{
	
	static int[][] map;
	static StringBuilder sb;
	
	public static void sepMerge(int x1, int y1, int x2, int y2) {
		int sum = 0;
		
		for (int i = x1; i <= x2; i++) {
			for (int j = y1; j <= y2; j++) {
				sum += map[i][j];
			}
		}
		
		if(sum == 0) {
			sb.append(0);
			return;
		}else if(sum == (x2-x1+1)*(y2-y1+1)) {
			sb.append(1);
			return;
		}else {
			int x3 = (x1+x2)/2;
			int y3 = (y1+y2)/2;
			sb.append("(");
			sepMerge(x1,y1,x3,y3);
			sepMerge(x1,y3+1,x3,y2);
			sepMerge(x3+1,y1,x2,y3);
			sepMerge(x3+1,y3+1,x2,y2);
			sb.append(")");
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j)-'0';
			}
		}

		sb = new StringBuilder();
		
		sepMerge(0,0,N-1,N-1);
		
		System.out.println(sb.toString());
	}
}
