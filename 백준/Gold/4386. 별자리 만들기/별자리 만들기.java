import java.util.*;
import java.io.*;

public class Main{

	static class Edge implements Comparable<Edge>{
		int a,b;
		double dist;
		
		public Edge(int a, int b, double dist) {
			this.a = a;
			this.b = b;
			this.dist = dist;
		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(dist, o.dist);
		}
	}
	
	static int[] parent;
	
	public static int find(int x) {
		if(parent[x] == x) return x;
		return parent[x] = find(parent[x]);
	}
	
	public static double getDist(double x1, double y1, double x2, double y2) {
		double diffX = x1-x2;
		double diffY = y1-y2;
		return Math.sqrt(diffX*diffX + diffY*diffY);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer tokens;
		
		int N = Integer.parseInt(br.readLine());
		
		parent = new int[N];
		double[][] xy = new double[N][2];
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		
		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(br.readLine());
			
			Double x = Double.parseDouble(tokens.nextToken());
			Double y = Double.parseDouble(tokens.nextToken());
			
			xy[i][0] = x;
			xy[i][1] = y;
			
			for (int j = 0; j < i; j++) {
				pq.add(new Edge(i,j,getDist(x,y,xy[j][0],xy[j][1])));
			}
		}
		
		for (int i = 0; i < N; i++) {
			parent[i] = i;
		}
		
		int numLinked = 0;
		double totalDist = 0;
		while(!pq.isEmpty() && numLinked < N-1) {
			Edge edge = pq.poll();
			
			int pa = find(edge.a);
			int pb = find(edge.b);
			
			if(pa == pb) continue;
			
			numLinked++;
			parent[pa] = pb;
			totalDist += edge.dist;
 		}
		
		System.out.println(totalDist);
		
	}
}
