import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N,K;
    static int[] requiredBits;
    static int answer;
    static int[] chars = {1, 3, 4, 5, 6, 7, 9, 10, 11, 12, 14, 15, 16, 17, 18, 20, 21, 22, 23, 24, 25};
    public static void comb(int idx, int cnt, int bit){
        if(cnt == K){
            // K개 글자 골랐을 때
            int numRequired = 0;
            for (int i = 0; i < N; i++) {
                if((bit & requiredBits[i]) == requiredBits[i]) numRequired++;
            }

            answer = Math.max(answer, numRequired);
            return;
        }
        for (int i = idx; i < chars.length; i++) {
            comb(i+1,cnt+1,bit | (1 << chars[i]));
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokens;

        /*
            K개 글자 가르침

            단어는
            anta로 시작
            tica로 끝
            => a,n,t,i,c는 무조건 가르쳐야함

            남극에 단어 N개 <= 50

            학생들이 읽을 수 있는 단어 개수 최댓값

            부분 조합 + 백 트래킹

            -     -                 -              -                 -
            a  b  c  d  e  f  g  h  i  j  k  l  m  n  o  p  q  r  s  t  u  v  w  x  y  z
            0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25
         */

        tokens = new StringTokenizer(br.readLine());

        N = Integer.parseInt(tokens.nextToken());
        K = Integer.parseInt(tokens.nextToken()) - 5;

        requiredBits = new int[N];

        for (int i = 0; i < N; i++) {
            String word = br.readLine();

            for (int j = 0; j < word.length(); j++) {
                int idx = word.charAt(j) - 'a';
                requiredBits[i] |= 1 << idx;
            }
        }

        if(K >= 0){
            int bit = (1) | (1 << 2) | (1 << 8) | (1 << 13) | (1 << 19);
            comb(0,0,bit);
        }

        System.out.println(answer);
    }
}