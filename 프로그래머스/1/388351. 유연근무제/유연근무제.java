class Solution {
    public boolean isWeekend(int day){
        return day == 6 || day == 7;
    }
    
    public int toMinute(int time){
        return (time / 100) * 60 + time % 100;
    }
    
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        /*
            출근 희망 시각 + 10분까지 출근
            토요일, 일요일 이벤트 X
            
            모든 시각은 "시 * 100 + 분" 으로 표시
            
            상품 받을 직원 수 구하기
        */
        int answer = 0;
        
        for(int i=0; i < timelogs.length; i++){
            int[] timelog = timelogs[i];
            int dueTime = schedules[i];
            int day = startday - 1;

            boolean shouldGivePresent = true;
            for(int time : timelog) {
                if(++day == 8) day = 1;
                if(isWeekend(day)) continue;
                
                int diff = toMinute(time) - toMinute(dueTime);
                
                if(diff > 10) {
                    shouldGivePresent = false;
                    break;
                }
            }
            
            if(shouldGivePresent) answer++;
        }
        
        return answer;
    }
}