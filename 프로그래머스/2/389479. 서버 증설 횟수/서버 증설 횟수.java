import java.util.*;

class Server {
    int endTime; // 끝나는 시간
    int num; // 증설된 수
    
    public Server(int endTime, int num){
        this.endTime = endTime;
        this.num = num;
    }
}

class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        
        /*
            필요할 때 증설하기
            현재 증설된 서버 수
        */
        
        int numServer = 0; // 현재 증설된 서버 수
        
        Queue<Server> queue = new LinkedList<>(); // 증설한 서버 정보
        
        int time = 0;
        for(int player : players) {                       
            time++;

            if(!queue.isEmpty()){
                // 가장 최근에 설치된 서버 정보 가져오기
                Server server = queue.peek();
                // 끝나는 시간이 되었다면 서버 반납
                if(server.endTime == time) {
                    numServer -= server.num; 
                    queue.poll();
                }
            }
            
            int numRequiredServer = player / m; // 요구되는 서버 수
            
            // 증설하기
            int numBuiltServer = 0;
            if(numServer < numRequiredServer) {
                numBuiltServer = numRequiredServer - numServer;
                answer += numBuiltServer;
                numServer = numRequiredServer;
                queue.offer(new Server(time + k, numBuiltServer));
            }
        }
        
        return answer;
    }
}