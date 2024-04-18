import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokens;

        /*
            최대 N개 종류 알파벳을 가진 연속된 문자열 인식
         */

        int N = Integer.parseInt(br.readLine());

        String str = br.readLine();

        int st = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        int answer = 0;
        for (int ed = 0; ed < str.length(); ed++) {
            char c = str.charAt(ed);
            map.put(c,map.getOrDefault(c,0)+1);
            while(st < ed && map.size() > N){
                char stChar = str.charAt(st++);
                int cnt = map.get(stChar);
                if(cnt == 1) map.remove(stChar);
                else map.put(stChar,cnt-1);
            }
            answer = Math.max(answer, ed-st+1);
        }

        System.out.println(answer);
    }
}