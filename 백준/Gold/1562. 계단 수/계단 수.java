import java.util.*;
import java.io.*;

public class Main{

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer tokens;
		
		/*
		 * 2^10
		 * 
		 * dp[n][i][k] : n자리 수 중 i로 시작하는 계단 수 중 k인 수
		 * (k : 0000000000 각 비트 값은 0~9가 포함되었는지 아닌지 표시
		 * 
		 * bitI = 1 << n;
		 * dp[n][i][k | bitI] += (dp[n-1][i-1][k] + dp[n-1][i+1][k])
		 * 
		 * 0 0000000001
		 * 1 0000000010
		 * .
		 * .
		 * .
		 * 8 0100000000
		 * 9 1000000000
		 * 
		 * dp[1][i][1<<i] = 1;
		 * 
		 * dp[1][1][2] = 1;
		 * 
		 * 01 => dp[2][0][1 | 2] += dp[1][1][2]
		 */
		
		int MOD = 1_000_000_000;
		
		int N = Integer.parseInt(br.readLine());
		int bitNum = (int)Math.pow(2, 10);
		
		int[][][] dp = new int[N+1][10][bitNum];
		
		for (int i = 0; i < 10; i++) {
			dp[1][i][1<<i] = 1;
		}
		
		for (int n = 2; n <= N; n++) {
			for (int i = 0; i < 10; i++) {
				int bitI = 1 << i;
				if(i != 0) {
					// i-1로 시작하는 계단수
					int j = i-1;
					for (int k = 0; k < bitNum; k++) {
						int nk = k | bitI;
						dp[n][i][nk] = (dp[n][i][nk] + dp[n-1][j][k]) % MOD;
					}
				}
				
				if(i != 9) {
					// i+1로 시작하는 계단수
					int j = i+1;
					for (int k = 0; k < bitNum; k++) {
						int nk = k | bitI;
						dp[n][i][nk] = (dp[n][i][nk] + dp[n-1][j][k]) % MOD;
					}
				}
			}
		}
		
		int sum = 0;
		for (int i = 1; i < 10; i++) {
			sum = (sum+dp[N][i][bitNum-1]) % MOD;
		}
		
		System.out.println(sum);
	}
}
