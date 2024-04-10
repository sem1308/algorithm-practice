import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main{

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokens;

        tokens = new StringTokenizer(br.readLine());

        BigInteger n = new BigInteger(tokens.nextToken());
        BigInteger m = new BigInteger(tokens.nextToken());

        BigInteger money = n.divide(m);
        BigInteger change = n.remainder(m);

        System.out.println(money + " " + change);
    }
}