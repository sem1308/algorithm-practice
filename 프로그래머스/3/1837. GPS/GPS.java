import java.util.*;

class Solution {
    private static List<Integer>[] trees;
    private static int[][] dp;
    
    public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
        int answer = 0;

        trees = new ArrayList[n + 1];
        dp = new int[n + 1][k];
        for (int i = 1; i <= n; i++) {
            trees[i] = new ArrayList<>();
            Arrays.fill(dp[i], -1);
        }

        for (int i = 0; i < m; i++) {
            trees[edge_list[i][0]].add(edge_list[i][1]);
            trees[edge_list[i][1]].add(edge_list[i][0]);
        }

        answer = func(gps_log[0], 0,gps_log, k);

        if (answer == Integer.MAX_VALUE) answer = -1;
        System.out.println(answer);
        return answer;
    }

    private static int func(int node, int time, int[] gpsLog, int k) {
        if (time == k - 1) {
            if (gpsLog[time] == node) return 0;
            return dp[node][time] = Integer.MAX_VALUE;
        }

        if (dp[node][time] != -1) return dp[node][time];        

        dp[node][time] = Integer.MAX_VALUE;
        
        int diff = 1;
        if (gpsLog[time] == node) diff = 0;
        
        int result = dp[node][time];

        for (Integer next : trees[node]) {
            if(dp[next][time + 1] == Integer.MAX_VALUE) continue;
            result = Math.min(result, func(next, time + 1, gpsLog, k));
        }

        if(result != Integer.MAX_VALUE) dp[node][time] = result + diff;        
        return dp[node][time];
    }
}