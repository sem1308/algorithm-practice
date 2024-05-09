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
            T시간
            최대 만족도인 시간

            누적합 + 슬라이딩
         */

        tokens = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(tokens.nextToken());
        int T = Integer.parseInt(tokens.nextToken());

        int MAX_TIME = 100_000;

        int[] acc = new int[MAX_TIME+2];

        int maxE = 0;
        for (int i = 0; i < N; i++) {
            int K = Integer.parseInt(br.readLine());
            for (int j = 0; j < K; j++) {
                tokens = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(tokens.nextToken());
                int e = Integer.parseInt(tokens.nextToken());
                acc[s] += 1;
                acc[e] -= 1;

                maxE = Math.max(maxE,e);
            }
        }

//        System.out.print(acc[0] + " ");
        for (int i = 1; i <= maxE; i++) {
            acc[i] += acc[i-1];
//            System.out.print(acc[i] + " ");
        }
//        System.out.println();

        int curCnt = 0;
        int answer = 0;

        // 초기 값 구하기
        for (int i = 0; i < T; i++) {
            curCnt += acc[i];
        }

        int maxCnt = curCnt;

        for (int i = 1; i < maxE-T; i++) {
            curCnt = curCnt - acc[i-1] + acc[i+T-1];
            if(curCnt > maxCnt){
                maxCnt = curCnt;
                answer = i;
            }
//            System.out.println(curCnt);
        }

        System.out.println(answer+" "+(answer+T));

    }
}