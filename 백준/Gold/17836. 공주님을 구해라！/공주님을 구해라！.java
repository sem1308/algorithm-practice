import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Knight{
        int x,y;
        int t;

        public Knight(int x, int y, int t) {
            this.x = x;
            this.y = y;
            this.t = t;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokens;

        /*
            N x M 크기
            1,1에서 시작

            T시간 이내로 구출

            그람을 구하면 어디든 갈 수 있음

            1) 그람 구하지 않고 가능한지
            2) 그람 구한 후 가능한지

            구출 가능 => 최단시간
            구출 불가능 => Fail

            1) 최단시간 구함
            2) 그 시간이 T보다 크면 Fail

            bfs
         */

        tokens = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(tokens.nextToken());
        int M = Integer.parseInt(tokens.nextToken());
        int T = Integer.parseInt(tokens.nextToken());

        int[] dx = {1,0,-1,0};
        int[] dy = {0,1,0,-1};

        int[][] map = new int[N+1][M+1];
        for (int i = 1; i <= N; i++) {
            tokens = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                map[i][j] = Integer.parseInt(tokens.nextToken());
            }
        }

        Queue<Knight> q = new ArrayDeque<>();

        boolean[][] visited = new boolean[N+1][M+1];

        visited[1][1] = true;
        q.add(new Knight(1,1,0));

        int answer = T+1;
        while(!q.isEmpty()){
            Knight knight = q.poll();

            if(knight.t > answer){
                break;
            }

            if(map[knight.x][knight.y] == 2){
                answer = Math.min(answer, knight.t + Math.abs(knight.x-N) + Math.abs(knight.y-M));
            }

            if(knight.x == N && knight.y == M){
                answer = knight.t;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx = knight.x + dx[i];
                int ny = knight.y + dy[i];

                if(nx > 0 && nx <= N && ny > 0 && ny <= M && map[nx][ny] != 1 && !visited[nx][ny]){
                    visited[nx][ny] = true;
                    q.add(new Knight(nx,ny, knight.t+1));
                }
            }
        }

        System.out.println(answer == T+1 ? "Fail" : answer);
    }
}