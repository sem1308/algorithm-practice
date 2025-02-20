class Solution {
    public int solution(int n, int w, int num) {
        /*
            # 변수 설명            
            n : 택배 상자 개수
            w : 가로로 놓는 상자 개수
            num : 꺼내려는 택배 상자 번호
            
            # 풀이
            1) 꺼내려는 택배 상자의 행(row), 열(col) 구하기
            2) 행, 열을 다음고 같이 표현하자면 (row, col) 
                (0,0) => (1,0) : 2*w - 1
                (row,col) => (row + 1, col) : 2*(w - col) - 1
                (1,0) => (2,0) : 1
                (row,col) => (row + 1, col) : 2*(col) + 1
                
                row가 짝수
                (row,col) => (row + 1, col) : 2*(w - col) - 1
                row가 홀수
                (row,col) => (row + 1, col) : 2*(col) + 1
            3) 위 공식대로 꺼내려는 택배 상자의 행(row), 열(col)부터 행을 하나씩 증가해가며 다음 상자 번호 구한 후
               마지막 택배 상자 번호보다 커지면 멈춘다.
        */ 
        
        int answer = 0;
        
        int row, col;
        boolean isRowEven;
        if(num % w == 0) {
            row = num / w - 1;
            isRowEven = row % 2 == 0;
            col = isRowEven ? w - 1 : 0;
        } else {
            row = num / w;
            isRowEven = row % 2 == 0;
            col = isRowEven ? num - row * w - 1 : w - (num - row * w - 1) - 1;
        }
        
        int curNum = num; // 현재 박스 번호
        while(curNum <= n){
            answer++;
            
            if(isRowEven) curNum += 2*(w - col) - 1;
            else curNum = curNum += 2*(col) + 1;
                
            isRowEven = !isRowEven;
        }
        
        return answer;
    }
}