import java.util.*;

class Solution {
        public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
            /*
                n : 거점 개수 <= 200
                m : 도로 개수 <= 10,000
                edge_list : 연결된 도로 정보
                k : 거점 정보 총 개수 <= 100
                gps_log : 거점 정보
                
                dp[t][n] : t초에 n지점인 경우 변경 최솟값
                
                dp[6][7] = 0
                
                dp[t][n] = gps_log[t] == n ? 1 : 0;                
                dp[t][n] = min(dp[t+1][c]) // c는 n과 연결된 node
            */
            List<Integer>[] adjList = new ArrayList[n+1];

            for (int i = 1; i <= n; i++) {
                adjList[i] = new ArrayList<>();
            }

            for (int i = 0; i < m; i++) {
                int[] edge = edge_list[i];
                int a = edge[0];
                int b = edge[1];
                adjList[a].add(b);
                adjList[b].add(a);
            }

            int[][] dp = new int[k][n+1];
            
            for(int i=0; i < k; i++){
                Arrays.fill(dp[i],Integer.MAX_VALUE);
            }
            
            int ed = k-1;
            dp[ed][gps_log[ed]] = 0;
            
            for(int i = ed-1; i >= 0; i--){
                for(int j = 1; j <= n; j++){
                    for(int c : adjList[j]){
                        dp[i][j] = Math.min(dp[i][j],dp[i+1][c]);
                    }
                    if(dp[i][j] != Integer.MAX_VALUE && gps_log[i] != j) dp[i][j]++;
                }
            }

            return dp[0][gps_log[0]] == Integer.MAX_VALUE ? -1 : dp[0][gps_log[0]];
        }
    }