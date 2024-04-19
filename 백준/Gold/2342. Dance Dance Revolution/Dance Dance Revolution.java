import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        /*
            해당 칸을 왼발로 하는 경우, 오른발로 하는 경우
            힘이 같으면 오른발
            dp[i][l][r] : i번째에 왼발이 l칸, 오른발이 r칸일 때 힘 최솟값
            // 왼발과 오른발은 한 쌍으로 취급이 되므로 순서를 신경쓰지 않아도 됨

            다음 칸이 n이어야 한다면
            왼발을 n으로 바꾸거나 오른발을 n으로 바꾸는 경우 존재

            현재 가능한 위치 조합에서
            왼발을 n으로 바꾸었을 때와
            오른발을 n으로 바꾸었을 때 중에
            힘의 최솟값을 저장

            int min = min(dp[i+1][n][r] + powerL, dp[i+1][l][n] + powerR);
            dp[i][l][r] = min(dp[i][l][r], min);
         */

        String input = br.readLine();

        int[] nums = Arrays.stream(input.split(" ")).mapToInt(Integer::parseInt).toArray();

        int N = nums.length;

        int[][] power = new int[5][5]; // power[a][b] a - > b로 바꿀 때 드는 힘

        for (int l = 0; l < 5; l++) {
            for (int r = 0; r < 5; r++) {
                if(l == r) power[l][r] = 1;
                else if(l == 0 || r == 0) power[l][r] = 2;
                else if(Math.abs(l-r) == 2) power[l][r] = 4;
                else power[l][r] = 3;
            }
        }

        int[][][] dp = new int[N][5][5];

        for (int i = N-3; i >= 0; i--) {
            int curN = nums[i];
            int nextN = nums[i+1];
            for (int l = 0; l < 5; l++) {
                for (int r = 0; r < 5; r++) {
                    // 양 발이 같은 위치에 있거나 현재 칸에 없는 경우는 넘김
                    if(l == r || (r != curN && l != curN)) continue;

                    if(l == nextN){
                        // 왼발이 다음 위치와 동일하다면
                        dp[i][l][r] = dp[i+1][l][r] + 1;
                    }else if(r == nextN){
                        // 오른발이 다음 위치와 동일하다면
                        dp[i][l][r] = dp[i+1][l][r] + 1;
                    }else{
                        // 양발 모두 다음 위치와 다르다면
                        dp[i][l][r] = Math.min(dp[i+1][nextN][r] + power[l][nextN], dp[i+1][l][nextN] + power[r][nextN]);
                    }
                }
            }
        }

        System.out.println(dp[0][0][nums[0]]+2);
    }
}