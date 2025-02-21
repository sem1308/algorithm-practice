import java.util.*;

class Robot {
    int r;
    int c;
    int targetRouteIndex;
    int[] route;
    
    public Robot(int[] route, int[][] points){
        int[] point = points[route[0] - 1];
        r = point[0];
        c = point[1];
        this.route = route;
        targetRouteIndex = 1;
    }
    
    public void move(int[][] points){
        // 목표 포인트 정보 가져오기
        int[] targetPoint = points[route[targetRouteIndex] - 1];
        int targetPointR = targetPoint[0];
        int targetPointC = targetPoint[1];
        
        // 목표 포인트로 이동하기
        if(targetPointR != r){
            if(targetPointR < r) r--;
            else r++;
        } else if (targetPointC != c){
            if(targetPointC < c) c--;
            else c++;
        }
        
        // 목표 포인트에 도착했다면 다음 목표로 설정
        if(targetPointR == r && targetPointC == c) {
            targetRouteIndex++;
        }
    }
    
    public boolean isFinished(){
        return targetRouteIndex == route.length;
    }
}


class Solution {
    public int flatten(int r, int c){
        return r * 1000 + c;
    }
    
    public int solution(int[][] points, int[][] routes) {
        Robot[] robots = new Robot[routes.length];

        // 좌표, 좌표에 존재하는 로봇의 수 
        Map<Integer, Integer> map = new HashMap<>();
        
        for(int i = 0; i < routes.length; i++){
            robots[i] = new Robot(routes[i], points);
            
            // 좌표에 대한 로봇 수 추가
            int key = flatten(robots[i].r,robots[i].c);
            map.put(key, map.getOrDefault(key,0)+1);
        }
        
        int answer = 0;
        
        // 로봇이 존재하지 않을때 까지 반복
        while(!map.isEmpty()){
            // 충돌 감지
            for(int key : map.keySet()){
                if(map.get(key) >= 2) answer++;                
            }
            map.clear();
            
            // 시뮬레이션
            for(Robot robot : robots){
                if(robot.isFinished()) continue;
                
                // 아직 도착하지 않은경우
                // 로봇 이동
                robot.move(points);
                // 이동 좌표에 대한 로봇 수 추가
                int key = flatten(robot.r,robot.c);
                map.put(key, map.getOrDefault(key,0)+1);
            }
        }
        
        return answer;
    }
}