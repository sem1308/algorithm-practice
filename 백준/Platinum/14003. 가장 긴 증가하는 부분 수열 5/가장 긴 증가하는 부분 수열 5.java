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
		
		int[] indicies = new int[N];
		int[] nums = new int[N];
		Arrays.fill(indicies, -1);
		
		int maxK = 0;
		
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(tokens.nextToken());
			nums[i] = num;
			int k = binarySearch(C, 0, maxK, num);
			if(C[k] > num) {
				indicies[i] = k;
				C[k] = num;
			}
			if(maxK == k) {
				maxK++;
			}
		}
		
		int[] result = new int[maxK];
		
		StringBuilder sb = new StringBuilder();
		int k = maxK-1;
		for (int i = N-1; i >= 0 && k >= 0; i--) {
		    if(indicies[i] == k) {
		        result[k--] = nums[i];
		    }
		}
		
		for(int i = 0; i < maxK; i++) {
			sb.append(result[i]).append(" ");
		}
		
		System.out.println(maxK);
		System.out.println(sb);
	}
}
