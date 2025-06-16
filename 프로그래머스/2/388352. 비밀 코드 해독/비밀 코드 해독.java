class Solution {
    int n;
    int[][] q;
    int[] ans;
    int answer;
    boolean[] included;
    
    public boolean isAvailable(){
        boolean available = true; 
        for(int i=0; i<q.length; i++) {
            int[] nums = q[i];
            int numIncluded = 0;
            for(int num : nums) {
                if(included[num]) {
                    numIncluded++;
                }
            }
            if(numIncluded > ans[i]) {
                available = false;
                break;
            }
        }
        return available;
    }
    
    public boolean isEqual(){
        boolean equal = true; 
        for(int i=0; i<q.length; i++) {
            int[] nums = q[i];
            int numIncluded = 0;
            for(int num : nums) {
                if(included[num]) {
                    numIncluded++;
                }
            }
            if(numIncluded != ans[i]) {
                equal = false;
                break;
            }
        }
        return equal;
    }
    
    public void print(){
        for(int i=1; i<=n; i++) {
            if(included[i]) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }
    
    // cnt번째에 num이 있을 때 처리
    public void dfs(int cnt, int number){                
        included[number] = true;

        if(isAvailable()) {
            if(cnt == 4 && isEqual()){    
                answer++;
            } else {
                for(int i=number+1; i<=n; i++){
                    dfs(cnt+1, i);
                }
            }
        }
        included[number] = false;
    }
    
    public int solution(int n, int[][] q, int[] ans) {
        /*
            정수 5개 : 1~n, 오름차순 
            (10 <= n <= 30)
            
            m번 시도 : 5개 정수 입력시 비밀 코드 포함된 개수 반환
            (1 <= m <= 10)
            
            비밀 코드로 가능한 정수 조합 개수
            
            100,000 * 243 = 24,300,000
            
            브루트 포스, 백트래킹
        */
        this.n = n; 
        this.q = q;
        this.ans = ans;
        included = new boolean[n+1];
        answer = 0;
        
        for(int i=1; i<=n-4; i++){
            dfs(0, i);
        }
            
        return answer;
    }
}