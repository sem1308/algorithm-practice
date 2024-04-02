import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

	    for (int i = 0; i < T; i++) {
	        int N = Integer.parseInt(br.readLine());
	        long[] arr = new long[101];
	
	        arr[1] = arr[2] = arr[3] = 1;
	        arr[4] = arr[5] = 2;
	
	        for (int j = 6; j <= 100; j++) {
	            arr[j] = arr[j - 2] + arr[j - 3];
	        }
	        sb.append(arr[N]).append("\n");
	    }
	    System.out.println(sb.toString());
	}
}