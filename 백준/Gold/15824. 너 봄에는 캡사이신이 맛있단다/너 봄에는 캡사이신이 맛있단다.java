import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int MOD = 1_000_000_007;
    static long pow(int n, int m){
        if(m == 0) return 1;
        if(m == 1) return n;

        long res = pow(n,m/2);
        res = (res * res) % MOD;
        if(m % 2 == 1){
            res = (res * n) % MOD;
        }
        return res;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokens;

        /*
            음식과 상대 수치에 기반
            1. 정렬
            1 10 -> 2^4
            1 6 -> 2^3
            1 5 -> 2^2
            1 5 -> 2^1
            1 4 -> 2^0

            4 10 -> 2^3
            4 6 -> 2^2
            4 5 -> 2^1
            4 5 -> 2^0

            5 10 -> 2^2
            5 6 -> 2^1
            5 5 -> 2^0

            5 10 -> 2^1
            5 6 -> 2^0

            6 10 -> 2^0

            10 => 2^0 + ... + 2^4 = 2^5 - 2^0
            6 -> 2^0 + ... + 2^3 - 2^0 = 2^4 - 2^1
            5 -> 2^0 + ... + 2^2 - (2^0 + 2^1) = 2^3 - 2^2
            5 -> 2^0 + ... + 2^1 - (2^0 + 2^1 + 2^2) = 2^2 - 2^3
            4 -> 2^0 - (2^0 + 2^1 + 2^2 + 2^3) = 2^1 - 2^4
            1 -> 0 - (2^0 + 2^1 + 2^2 + 2^3 + 2^4) = 2^0 - 2^5

            (10 - 1) * (2^5 - 2^0)
            (6 - 4) * (2^4 - 2^1)
            (5 - 5) * (2^3 - 2^2)



            정렬 후 i번째 원소
            2^0 + ... + 2^(i-1) - (2^0 + ... + 2^(N-i-2))
            = 2^i-1 - (2^(N-i-1)-1)
            = 2^i - 2^(N-i-1)


            2^(N-i-1) - 2^i
            = 2^i(2^(N-2*i-1) - 1)
         */

        int N = Integer.parseInt(br.readLine());

        long[] spicy = new long[N];
        tokens = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            spicy[i] = Integer.parseInt(tokens.nextToken());
        }

        Arrays.sort(spicy);

        int halfN = N/2;
        long sum = 0;
        for (int i = 0; i < halfN; i++) {
            long diff = spicy[N-i-1] - spicy[i];
            long diffPow = pow(2,i) * (pow(2,N-2*i-1)-1) % MOD;
            sum = (sum + (diff * diffPow) % MOD ) % MOD;
        }
        System.out.println(sum);
    }
}