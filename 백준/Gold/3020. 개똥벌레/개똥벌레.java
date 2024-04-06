import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokens;

        tokens = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(tokens.nextToken());
        int H = Integer.parseInt(tokens.nextToken());

        /*
            1. 종유석 : 중간부터 누적합
            2. 석순 : 아래부터 중간까지 누적합
            3. num이 최소가 되는 값과 그 개수 구함
         */

        int[] num = new int[H];

        boolean isBottom = true;
        for (int i = 0; i < N; i++) {
            int size = Integer.parseInt(br.readLine());
            if(isBottom){
                num[0] += 1;
                num[size] -= 1;
                isBottom = false;
            }else{
                num[H-size] += 1;
                isBottom = true;
            }
        }

        for (int i = 1; i < H; i++) {
            num[i] += num[i-1];
        }

        int min = Integer.MAX_VALUE;
        int minCnt = 0;
        for (int i = 0; i < H; i++) {
            if(min > num[i]){
                minCnt = 1;
                min = num[i];
            }else if(min == num[i]){
                minCnt++;
            }
        }

        System.out.println(min+" "+minCnt);

    }
}