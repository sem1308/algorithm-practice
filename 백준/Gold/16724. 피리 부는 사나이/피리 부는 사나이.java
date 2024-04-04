import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{

    static class Coord{
        int x,y;
        public Coord(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    static Coord find(Coord c){
        if(parent[c.x][c.y] == c) return c;
        return parent[c.x][c.y] = find(parent[c.x][c.y]);
    }

    static int N,M;
    static char[][] map;
    static Coord[][] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokens;

        tokens = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(tokens.nextToken());
        int M = Integer.parseInt(tokens.nextToken());

        map = new char[N][M];
        parent = new Coord[N][M];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j);
                parent[i][j] = new Coord(i,j);
            }
        }


        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                Coord a = parent[i][j];
                Coord b = null;
                switch(map[i][j]){
                    case 'U':
                        b = parent[i-1][j];
                        break;
                    case 'D':
                        b = parent[i+1][j];
                        break;
                    case 'L':
                        b = parent[i][j-1];
                        break;
                    case 'R':
                        b = parent[i][j+1];
                        break;
                }

                Coord pa = find(a);
                Coord pb = find(b);

                // a->b : a의 조상 : b의 조상
                if(pa != pb){
                    parent[pa.x][pa.y] = pb;
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                Coord p = parent[i][j];
                if(p.x == i && p.y == j){
                    ans++;
                }
            }
        }

        System.out.println(ans);

    }
}