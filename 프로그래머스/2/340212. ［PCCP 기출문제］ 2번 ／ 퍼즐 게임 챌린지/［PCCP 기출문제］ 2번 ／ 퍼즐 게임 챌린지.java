class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        /*
            n개 퍼즐, 제한 시간 내에
            
            각 퍼즐 => 난이도, 소요 시간
            
            숙련도에 따라 퍼즐 풀 때 트리는 횟수 변경
            
            diff : 퍼즐 난이도
            time_cur : 현재 퍼즐 소요 시간
            time_prev : 이전 퍼즐 소요 시간
            level : 숙련도
            
            diff <= level
            => time_cur 시간 사용하여 해결
            diff > level
            - diff - level번 틀림
            - 틀릴 때마다, time_cur 시간 사용, time_prev 시간 사용해 이전 퍼즐 다시 풀어야함
            - 그 후 time_cur만큼 사용하여 해결
            => (diff - level)(time_cur + time_prev) + time_cur 시간 사용하여 해결
            
            limit : 전체 제한 시간
            
            제한 시간 내에 퍼즐을 모두 해결하기 위한 "숙련도 최솟값"
            
            매개변수 탐색
        */
        
        int st = 1;
        int ed = 100_000;
        
        while(st < ed){
            int level = (st + ed) / 2;
            
            int time_prev = 0;
            long total_time = 0;
            for(int i=0; i < times.length; i++){
                int diff = diffs[i];
                int time_cur = times[i];
                
                if(diff <= level) {
                    total_time += time_cur;    
                } else {
                    total_time += (diff - level) * (time_cur + time_prev) + time_cur;
                }
                
                time_prev = time_cur;
            }
            
            if(total_time <= limit){
                // 퍼즐 해결 가능
                ed = level;
            } else {
                // 퍼즐 해결 불가능
                st = level + 1;
            }
        }
        
        return ed;
    }
}