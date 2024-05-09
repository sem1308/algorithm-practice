import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokens;

        /*
            같은 말 n번 반복, 문자 확인

            n번 반복 걸리는 시간

            말 타이핑 => s초
            문자 복사/붙여넣기 => t초

            n -> 0으로 만듦

            17 -> 16 -> 8 -> 4 -> 2 -> 1 -> 0
                1    5    4     2    1    1

            c개 => c*s , t

            2 5 => 5/2 = 2
            2 6 => 6/2 = 3
         */

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            long n = Long.parseLong(br.readLine());

            tokens = new StringTokenizer(br.readLine());

            long s = Long.parseLong(tokens.nextToken());
            long t = Long.parseLong(tokens.nextToken());

            long diff = t/s;

            long answer = 0;
            while(n > 0){
                if(n % 2 == 1){
                    answer += s;
                    n--;
                }else{
                    n /= 2;
                    if(n <= diff){
                        answer += n * s;
                    }else{
                        answer += t;
                    }
                }
            }
            sb.append(answer).append("\n");
        }

        System.out.println(sb);
    }
}