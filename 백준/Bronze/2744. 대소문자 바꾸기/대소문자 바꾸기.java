import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{

    static boolean isUpper(char c){
        return 'A' <= c && c <= 'Z';
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            // c - 'A' + 'a'
            sb.append(isUpper(c) ? (char)(c - 'A' + 'a') :(char)(c - 'a' + 'A'));
        }
        System.out.println(sb);
    }
}