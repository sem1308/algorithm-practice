import java.util.*;

class Solution {
    
    public int add(int offset, int minute) {
        int offsethour = offset / 100;
        int offsetMinute = offset % 100;
        int sumMinute = offsetMinute + minute;
        int sum = 0;
        if(sumMinute >= 60) {
            sum = offsethour * 100 + (sumMinute + 40);
        } else {
            sum = offsethour * 100 + sumMinute;
        }
        return sum;
    }
    
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        /*
            출근 희망 시각 + 10분 이내
            토,일은 이벤트 X
            1 : 월 ~ 7: 일
            
            timelog를 보면서 schedule + 10 안에 있으면 이벤트 통과 
        */
        int answer = 0;
        
        for(int i = 0; i < schedules.length; i++) {
            int day = startday;
            int schedule = schedules[i];
            int limit = add(schedule, 10);
            boolean isDone = true;
            for(int timelog : timelogs[i]) {
                if(day != 6 && day != 7 && timelog > limit) {
                    isDone = false;
                    break;
                }
                day++;
                if(day == 8) day = 1;
            }
            if(isDone) {
                answer++;
            }
        }
        
        return answer;
    }
}