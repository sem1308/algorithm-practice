import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);

        StringTokenizer tokens;

        StringBuilder sb = new StringBuilder();
        while(scan.hasNext()){
            tokens = new StringTokenizer(scan.nextLine());
            int A = Integer.parseInt(tokens.nextToken());
            int B = Integer.parseInt(tokens.nextToken());
            sb.append(A+B).append("\n");
        }
        System.out.println(sb);
    }
}