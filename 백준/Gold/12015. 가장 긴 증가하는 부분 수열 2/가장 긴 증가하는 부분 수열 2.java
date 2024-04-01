import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main{
	public static int binarySearch(int[] arr, int st, int ed, int num) {
		while(st < ed) {
			int mid = (st+ed) / 2;
			int midNum = arr[mid];
			
			if(num <= midNum) {
				ed = mid;
			}else {
				st = mid+1;
			}
		}
		return st;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer tokens;
		
		int N = Integer.parseInt(br.readLine());
		
		tokens = new StringTokenizer(br.readLine());
		
		int[] C = new int[N];
		Arrays.fill(C, Integer.MAX_VALUE);
		
		int maxK = 0;
		
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(tokens.nextToken());
			int k = binarySearch(C, 0, maxK, num);
			if(C[k] > num) {
				C[k] = num;
			}
			if(maxK == k) {
				maxK++;
			}
		}

		System.out.println(maxK);
	}
}
