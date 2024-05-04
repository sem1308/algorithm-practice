import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static char[] ops = {' ','+','-'};
    static char[] selectedOps;
    static StringBuilder resSb = new StringBuilder();

    static int N;
    static public void dfs(int cnt){
        if(cnt+1 == N){
            int result = 0;
            int curNum = 1;
            char curOp = '+';
            StringBuilder sb = new StringBuilder();
            sb.append("1");
            for (int i = 2; i <= N; i++) {
                char op = selectedOps[i-2];
                sb.append(op).append(i);

                switch (op){
                    case ' ':
                        curNum = curNum * 10 + i;
                        break;
                    case '+':
                    case '-':
                        result += curOp == '+' ? curNum : -curNum;
                        curOp = op;
                        curNum = i;
                        break;
                }
            }

            result += curOp == '+' ? curNum : -curNum;

            if(result == 0){
                resSb.append(sb).append("\n");
            }
            return;
        }

        for (int i = 0; i < 3; i++) {
            selectedOps[cnt] = ops[i];
            dfs(cnt+1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        /*
            1~N 오름차순 숫자
            '+'(43), '-'(45), ' '(32) 숫자 사이에 삽입

            수식 계산 후 0이 될 수 있는지

            N <= 9

            수식 최대 8개
            중복 허용 순열 3^8 (ok)
         */

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());

            selectedOps = new char[N];

            dfs(0);

            resSb.append("\n");
        }

        System.out.println(resSb);
    }
}