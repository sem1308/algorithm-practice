import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T=Integer.parseInt(br.readLine());
        
        StringTokenizer tokens;
        
        /*
         * x,y
         */
        
        StringBuilder sb = new StringBuilder();
        for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			
			int maxDist = -1;
			int remain = 0;
			
			boolean isValid = true;
			for (int i = 0; i < N; i++) {
				tokens = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(tokens.nextToken());
				int y = Integer.parseInt(tokens.nextToken());
				
				int dist = Math.abs(x) + Math.abs(y);
				if(i == 0) {
					remain = dist % 2;
					maxDist = dist;
					continue;
				}
				if(dist % 2 == remain) {
					maxDist = Math.max(maxDist, dist);					
				}else {
					isValid = false;
				}
			}
			
			if(!isValid) {
				sb.append("#").append(t).append(" ").append(-1).append("\n");
				continue;
			}
			
			int cnt = 0;
			while(maxDist > 0) {
				cnt++;
				maxDist -= cnt;
			}
			
			maxDist *= -1;
			
			if(maxDist % 2 == 1) {
				if(cnt % 2 == 0) cnt++;
				else cnt += 2;
			}
			
			sb.append("#").append(t).append(" ").append(cnt).append("\n");
		}
        
        System.out.println(sb);
    }
}