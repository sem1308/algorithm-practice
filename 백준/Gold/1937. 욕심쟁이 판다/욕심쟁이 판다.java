import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{

    static int N;
    static int[][] map;
    static int[][] dp;
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};

    static boolean isRange(int x, int y){
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    static void dfs(int x, int y){
        dp[x][y] = 1;
        int num = map[x][y];

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(isRange(nx,ny) && map[nx][ny] > num){
                if(dp[nx][ny] == 0){
                    dfs(nx,ny);
                }
                dp[x][y] = Math.max(dp[x][y],1+dp[nx][ny]);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokens;

        /*
            500 * 500 = 250_000

            dp[i][j] : i,j에서 출발했을 때 최대 거리

         */

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        dp = new int[N][N];
        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j]= Integer.parseInt(tokens.nextToken());
            }
        }

        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (dp[i][j] == 0){
                    dfs(i,j);
                }
                ans = Math.max(ans, dp[i][j]);
            }
        }

        System.out.println(ans);
    }
}