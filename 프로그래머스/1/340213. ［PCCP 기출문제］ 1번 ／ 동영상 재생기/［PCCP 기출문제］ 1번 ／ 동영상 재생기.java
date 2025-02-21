class Solution {
    public int parseToSeconds(String time){
        // time = "mm:ss"
        String[] mmss = time.split(":"); 
        
        int min = Integer.parseInt(mmss[0]);
        int sec = Integer.parseInt(mmss[1]);
        
        return min * 60 + sec; 
    }
    
    public String padZero(int num){
        return num >= 10 ? "" + num : "0" + num; 
    }
    
    public String parseToTime(int sec){
        int min = sec / 60; 
        sec -= min * 60;
        
        return padZero(min) + ":" + padZero(sec);  
    }
    
    public boolean isOpening(int posSec, int opStSec, int opEdSec){
        return opStSec <= posSec && posSec <= opEdSec;
    }
    
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        /*
            기능
            - 10초 전 이동
                prev
            - 10초 후 이동
                next
            - 오프닝 건너뛰기
            
            video_len : 동영상 길이
            pos : 기능 수행 직전 재생위치
            op_start : 오프닝 시작 시각
            op_end : 오프닝 끝나는 시각
            commands : 사용자 입력
            
            입력 후 동영상 위치 "mm:ss"로 표시
        */
        
        int posSec = parseToSeconds(pos);
        int opStSec = parseToSeconds(op_start);
        int opEdSec = parseToSeconds(op_end);
        int videoSec = parseToSeconds(video_len);
        
        for(String command : commands){
            if(isOpening(posSec, opStSec, opEdSec)) posSec = opEdSec;
            
            switch(command){
                case "prev" :
                    posSec = Math.max(posSec - 10, 0);
                    break;
                case "next" :
                    posSec = Math.min(posSec + 10, videoSec);
                    break;
                default :
                    break;
            }
        }
        
        if(isOpening(posSec, opStSec, opEdSec)) posSec = opEdSec;
        
        return parseToTime(posSec);
    }
}