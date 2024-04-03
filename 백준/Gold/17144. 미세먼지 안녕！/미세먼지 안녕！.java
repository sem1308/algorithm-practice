import java.util.*;
import java.io.*;

public class Main{
	
	static int R,C,T;
	static int[][] map;
	static int[][] tempMap;
	static int[] dx = {1,0,-1,0};
	static int[] dy = {0,1,0,-1};
	
	static class Dust{
		int x,y;
		int amount;

		public Dust(int x, int y, int amount) {
			this.x = x;
			this.y = y;
			this.amount = amount;
		}
	}

	public static boolean isRange(int x, int y, int R, int C) {
		return isRange(x,y,0,0,R,C);
	}
	
	public static boolean isRange(int x, int y, int stX, int stY, int R, int C) {
		return x >= stX && x < R && y >= stY && y < C;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer tokens;
		
		/*
		 * RxC
		 */
		
		tokens = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(tokens.nextToken());
		C = Integer.parseInt(tokens.nextToken());
		T = Integer.parseInt(tokens.nextToken());
		
		map = new int[R][C];
		tempMap = new int[R][C];
		
		boolean flag = false;
		int[] upper = new int[2];
		int[] lower = new int[2];
		for (int i = 0; i < R; i++) {
			tokens = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(tokens.nextToken());
				
				if(map[i][j] == -1) {
					if(!flag) {
						upper[0] = i;
						upper[1] = 0;
						flag = true;
					}else {
						lower[0] = i;
						lower[1] = 0;
					}
				}
			}
		}
		
		for(int t=0; t<T; t++) {
			// 미세먼지 확산
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					tempMap[i][j] += map[i][j];
					if(map[i][j] > 0) {
						int amount = map[i][j] / 5;
						for (int l = 0; l < 4; l++) {
							int nx = i + dx[l];
							int ny = j + dy[l];
							
							if(isRange(nx, ny, R, C) && map[nx][ny] != -1) {
								tempMap[nx][ny] += amount;
								tempMap[i][j] -= amount;
							}
						}
					}
				}
			}
			
			for (int i = 0; i < R; i++) {
				map[i] = tempMap[i].clone();
				Arrays.fill(tempMap[i], 0);
			}

			// 공기청정기
			int dir = 2;
			// 윗풍
			int px = upper[0];
			int py = upper[1];
			int nx = upper[0]+dx[dir];
			int ny = upper[1]+dy[dir];
			while(map[nx][ny] != -1) {
				if(map[px][py] != -1)
					map[px][py] = map[nx][ny];
				px = nx;
				py = ny;
				
				if(!isRange(nx + dx[dir], ny + dy[dir], lower[0], C)) {
					dir = (dir+3) % 4;
				}
				
				nx = nx+dx[dir];
				ny = ny+dy[dir];
			}
			map[px][py] = 0;
			// 아랫품
			dir = 0;
			px = lower[0];
			py = lower[1];
			nx = lower[0]+dx[dir];
			ny = lower[1]+dy[dir];
			while(map[nx][ny] != -1) {
				if(map[px][py] != -1)
					map[px][py] = map[nx][ny];
				px = nx;
				py = ny;
				
				if(!isRange(nx + dx[dir], ny + dy[dir], lower[0], 0, R, C)) {
					dir = (dir+1) % 4;
				}
				
				nx = nx+dx[dir];
				ny = ny+dy[dir];
			}
			map[px][py] = 0;
		}
		
		int total = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				total += map[i][j];
			}
		}
		total += 2;
		
		System.out.println(total);
	}
}
