import java.util.*;

class Expression{
    // a op b = c
    String a;
    String op;
    String b;
    String c;
    
    // 진법 범위
    // [minBase, maxBase]
    int minBase;
    int maxBase;
    
    public Expression(String expression){
        // exp = [a, op, b, =, c]
        String[] exp = expression.split(" ");

        a = exp[0];
        op = exp[1];
        b = exp[2];
        c = exp[4];
        
        minBase = getMaxDigit() + 1;
        maxBase = 9;
        
        if(!c.equals("X")){
            if(op.equals("+")) computeBaseByAdding(a,b,c);
            else computeBaseByAdding(b,c,a); // a - b = c -> b + c = a
        }
    }
    
   // 표현식에서 제일 큰 숫자 반환
    public int getMaxDigit(){
        String num = c.equals("X") ? a + b : a + b + c;
        int max = 0;
        for(int i=0; i < num.length(); i++){
            max = Math.max(max, num.charAt(i) - '0');
        }
        return max;
    }
    
    public void computeBaseByAdding(String a, String b, String c){
        a = padZero(a, c.length() - a.length());
        b = padZero(b, c.length() - b.length());
        
        int carry = 0;
        for(int i = c.length() - 1; i >= 0; i--){
            int ai = a.charAt(i) - '0';
            int bi = b.charAt(i) - '0';
            int ci = c.charAt(i) - '0';
            
            if(ai + bi + carry == ci){
                carry = 0;
            } else {
                // a,b 각 자리수를 더했을 때의 값 - c 각 자리수
                minBase = maxBase = ai + bi + carry - ci;
                carry = 1;
            }
        }
    }
        
    public void calculate(int minBase, int maxBase){
        Set<String> results = new HashSet<>();
        
        for(int base = minBase; base <= maxBase; base++){
            if(op.equals("+")) {
                results.add(plus(a,b,base));
            } else {
                results.add(subtract(a,b,base));
            }
        }
        
        if(results.size() == 1) {
            Iterator<String> iterator = results.iterator();
            this.c = String.valueOf(Integer.parseInt(iterator.next()));
        } else {
            this.c = "?";
        }
    }

    public String plus(String a, String b, int base){
        int maxLength = Math.max(a.length(), b.length());

        a = padZero(a, maxLength - a.length());
        b = padZero(b, maxLength - b.length());
        
        String c = "";
        
        int carry = 0;
        for(int i = maxLength - 1; i >= 0; i--){
            int ai = a.charAt(i) - '0';
            int bi = b.charAt(i) - '0';
            
            int ci = ai + bi + carry;
            
            if(ci >= base){
                carry = 1;
                ci -= base;
            } else {
                carry = 0;
            }
            
            c = String.valueOf(ci) + c;
        }
        
        c = carry + c;
        
        return c;
    }
    
    public String subtract(String a, String b, int base){
        b = padZero(b, a.length() - b.length());
        
        String c = "";
        
        int borrow = 0;
        for(int i = a.length() - 1; i >= 0; i--){
            int ai = a.charAt(i) - '0';
            int bi = b.charAt(i) - '0';
            
            int ci = ai - bi - borrow;
            
            if(ci < 0) {
                ci += base;
                borrow = 1;
            } else {
                borrow = 0;
            }

            c = String.valueOf(ci) + c;
        }
        
        return c;
    }
    
    private String padZero(String str, int amount) {
        if(amount == 0) return str;
        return "0".repeat(amount) + str;
    }
    
    public String toString(){
        return a + " " + op + " " + b + " = " + c;
    }
}

class Solution {
    
    public String[] solution(String[] expressions) {
        /*
            a op b = c
            
            빼기인 경우 b를 오른쪽 변에 넘겨 더하기로 변환
            
            a[i] + b[i] > c[i] (a,b 각 자리수를 더했을 때의 값이 c 각 자리수보다 큰 경우)            
            - n진법에서 n = a,b 각 자리수를 더했을 때의 값 - c 각 자리수
            a[i] + b[i] = c[i] (a,b 각 자리수를 더했을 때의 값이 c 각 자리수와 같은 경우)
            - n진법에서 n = [a b 각 자리수를 더했을 때의 값 + 1, 9]
            
            풀이
            1. 표현식을 객체로 저장
            2. 각 표현식 객체에서 숫자와 연산자 분리
            3. 각 표현식에서 가능한 최소 진법, 최대 진법 구함
            4. 모든 표현식을 조합하여 전체적인 최소 진법, 최대 진법 구함
            5. X가 들어간 표현식을 검사하여 범위에 맞는 진법을 대입해보며 답을 구함
        */
        
        int minBase = 2; // n진법의 범위 최솟값
        int maxBase = 9; // n진법의 범위 최댓값
        
        List<Expression> unknownExps = new ArrayList<>();
        for(String expression : expressions){
            Expression exp = new Expression(expression);
            
            // 지워진 표현식 저장
            if(exp.c.equals("X")) unknownExps.add(exp);
            
            // 최소, 최대 진법 설정
            minBase = Math.max(minBase, exp.minBase);
            maxBase = Math.min(maxBase, exp.maxBase);
        }
        
        // X 값 결정하기
        for(Expression exp : unknownExps){
            exp.calculate(minBase, maxBase);
        }
        
        return unknownExps.stream().map(Expression::toString).toArray(String[]::new);
    }
}