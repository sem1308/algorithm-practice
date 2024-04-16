import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String a1 = br.readLine();
        String a2 = br.readLine();

        System.out.println(a1.length() >= a2.length() ? "go" : "no");

    }
}