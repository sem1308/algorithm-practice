import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main{

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokens;

        tokens = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(tokens.nextToken());
        int H = Integer.parseInt(tokens.nextToken());

        /*
            1. 종유석(top) 누적합 구함
            2. 석순(bottom) 누적합 구함
            3. top + bottom이 최소가 되는 값과 그 개수 구함
         */

        int[] top = new int[H+1];
        int[] bottom = new int[H+1];

        boolean isBottom = true;
        for (int i = 0; i < N; i++) {
            int size = Integer.parseInt(br.readLine());
            if(isBottom){
                bottom[1] += 1;
                bottom[size+1] -= 1;
                isBottom = false;
            }else{
                top[H] += 1;
                top[H-size] -= 1;
                isBottom = true;
            }
        }

        for (int i = 2; i <= H; i++) {
            bottom[i] += bottom[i-1];
        }

        for (int i = H-1; i >= 1; i--) {
            top[i] += top[i+1];
        }

        int min = Integer.MAX_VALUE;
        int minCnt = 0;
        for (int i = 1; i <= H; i++) {
            int num = bottom[i] + top[i];
            if(min > num){
                minCnt = 1;
                min = num;
            }else if(min == num){
                minCnt++;
            }
        }

        System.out.println(min+" "+minCnt);

    }
}