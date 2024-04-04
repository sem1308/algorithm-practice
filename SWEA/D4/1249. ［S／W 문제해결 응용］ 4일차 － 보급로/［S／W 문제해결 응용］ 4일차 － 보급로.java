import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution
{
	static int N;
	static int[] dx = {1,0,-1,0};
	static int[] dy = {0,1,0,-1};
	
	static class Node implements Comparable<Node>{
		int x,y;
		int dist;

		public Node(int x, int y, int dist) {
			this.x = x;
			this.y = y;
			this.dist = dist;
		}

		@Override
		public int compareTo(Node o) {
			return this.dist - o.dist;
		}
	}
	
	public static boolean isRange(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}
	
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T=Integer.parseInt(br.readLine());
        
        StringTokenizer tokens;
        
        StringBuilder sb = new StringBuilder();
        for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			
			int[][] map = new int[N][N];
			int[][] minDist = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				Arrays.fill(minDist[i], Integer.MAX_VALUE);
				String str = br.readLine();
				for (int j = 0; j < N; j++) {
					map[i][j] = str.charAt(j)-'0';
				}
			}
			
			// 다익스트라
			PriorityQueue<Node> pq = new PriorityQueue<>();

			boolean[][] visited = new boolean[N][N];
			pq.add(new Node(0,0,0));
			visited[0][0] = true;
			while(!pq.isEmpty()) {
				Node node = pq.poll();
				
				for (int i = 0; i < 4; i++) {
					int nx = node.x + dx[i];
					int ny = node.y + dy[i];
					
					if(isRange(nx, ny) && !visited[nx][ny]) {
						int dist = node.dist + map[nx][ny];
						if(minDist[nx][ny] > dist) {
							minDist[nx][ny] = dist;
							pq.add(new Node(nx,ny,dist));
						}
					}
				}
			}
			
			sb.append("#").append(t).append(" ").append(minDist[N-1][N-1]).append("\n");
		}
        System.out.println(sb);

    }
}