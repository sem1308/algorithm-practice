import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main{
    static int N;
    static int[] health;
    static int ans = Integer.MAX_VALUE;
    static int[] power = {9,3,1};
    static int[][][] minCnt;
    static void perm(int cnt, int attackCnt, int deadCnt, int[] atkIdx, boolean[] selected){
        if(cnt == 3){
            // attack
            int[] tempHealth = health.clone();

            int numDead = 0;
            int idx = 0;
            for (int i : atkIdx) {
                if(health[i] > 0){
                    health[i] -= power[idx++];
                    if(health[i] <= 0){
                        numDead++;
                        health[i] = 0;
                    }
                }
            }

            dfs(attackCnt+1,deadCnt+numDead);
            health = tempHealth;
            return;
        }

        for (int i = 0; i < 3; i++) {
            if(selected[i]) continue;
            selected[i] = true;
            atkIdx[cnt] = i;
            perm(cnt+1, attackCnt, deadCnt, atkIdx, selected);
            selected[i] = false;
        }
    }

    static void dfs(int attackCnt, int deadCnt){
        if(attackCnt >= ans || attackCnt >= minCnt[health[0]][health[1]][health[2]]) return;
        minCnt[health[0]][health[1]][health[2]] = attackCnt;

        if(deadCnt == N){
            ans = attackCnt;
            return;
        }

        perm(0,attackCnt,deadCnt, new int[3], new boolean[3]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokens;

        /*
            9 3 1
            9 1 3
            3 1 9
            3 9 1
            1 3 9
            1 9 3

            3^7
        */

        N = Integer.parseInt(br.readLine());
        health = new int[3];
        minCnt = new int[61][61][61];

        tokens = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            health[i] = Integer.parseInt(tokens.nextToken());
        }

        for (int i = 0; i < 61; i++) {
            for (int j = 0; j < 61; j++) {
                Arrays.fill(minCnt[i][j],Integer.MAX_VALUE);
            }
        }

        dfs(0,0);

        System.out.println(ans);

    }
}