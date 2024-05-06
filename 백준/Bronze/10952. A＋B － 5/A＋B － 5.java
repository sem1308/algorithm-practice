import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokens;

        StringBuilder sb = new StringBuilder();
        while(true){
            tokens = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(tokens.nextToken());
            int B = Integer.parseInt(tokens.nextToken());

            if(A == 0) break;

            sb.append(A + B).append("\n");
        }

        System.out.println(sb);
    }
}