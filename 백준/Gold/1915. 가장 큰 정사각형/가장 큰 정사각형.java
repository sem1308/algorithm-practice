import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer tokens;
		
		/*
		 * dp[i][j] : i,j를 맨 오른쪽 아래 꼭짓점으로 하는 가장 큰 정사각형 크기
		 */

		tokens = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(tokens.nextToken());
		int M = Integer.parseInt(tokens.nextToken());

		int[][] dp = new int[N+1][M+1];
		for (int i = 1; i <= N; i++) {
			String numbers = br.readLine();
			for (int j = 1; j <= M; j++) {
				dp[i][j] = numbers.charAt(j-1)-'0';
			}
		}
		int result = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if(dp[i][j] == 0) continue;
				int min = Math.min(dp[i-1][j],dp[i][j-1]); // 왼쪽 또는 위를 꼭짓점으로 하는 가장 큰 정사각형의 크기 중 최솟값
				if(dp[i-min][j-min] >= 1) {
					dp[i][j] = min+1;
				}else{
					dp[i][j] = min;
				}
				result = Math.max(result,dp[i][j]);
			}
		}

		System.out.println(result * result);
	}
}
