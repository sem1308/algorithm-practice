import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N,V;
    static int[] P;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokens;

        /*
            1번 문제 먼저 품
            A번 문제 -> A+1 or A+2 문제

            문제마다 흥미도 존재

            흥미도 최대, 최소 차이가 V보다 크면 중지

            최소 문제 수 구하기
         */
        tokens = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokens.nextToken());
        V = Integer.parseInt(tokens.nextToken());

        P = new int[N];

        tokens = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            P[i] = Integer.parseInt(tokens.nextToken());
        }

        answer = N;

        // 0번 부터 i번 문제까지 풀었을 때

        // 0 1 => 1번
        // 0 1 2 => 1번
        // 0 1 2 3 => 2번
        // 0 1 2 3 4 => 2번
        // 0 1/ 2 3/ => 2번
        // 0 1 2/ 3 4/ => 2번
        // (minIdx + 1)/2 + (maxIdx - minIdx + 1)/2

        // i가 최소인 경우, j개 최대인 경우

        for (int i = 0; i < N; i++) {
            for (int j = i+1; j < N; j++) {
                if(Math.abs(P[i] - P[j]) >= V){
                    // 최소, 최대 차이가 V 이상인 경우
                    int cnt = 1 + (i + 1)/2 + (j - i + 1)/2;
                    answer = Math.min(answer, cnt);
                }
            }
        }
        System.out.println(answer);
    }
}