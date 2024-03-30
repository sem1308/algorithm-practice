import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        /*
            (1,0) 1: 1
            (1,1) 1: 1 2
            (1,2) -> (2,1) 2: 1 2 5
            (2,2) 2: 1 2 5 10
            
            pq 2개 사용
            mid보다 작은거, mid보다 큰 거
         */

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> smaller = new PriorityQueue<>((a,b)->b-a);
        PriorityQueue<Integer> bigger = new PriorityQueue<>();

        StringBuilder sb = new StringBuilder();
        smaller.add(Integer.parseInt(br.readLine()));
        sb.append(smaller.peek()).append("\n");
        for (int i = 1; i < N; i++) {
            int num = Integer.parseInt(br.readLine());

            if(num <= smaller.peek()){
                smaller.add(num);
            }else{
                bigger.add(num);
            }

            if(smaller.size() < bigger.size()){
                smaller.add(bigger.poll());
            }else if(smaller.size() > bigger.size()+1){
                bigger.add(smaller.poll());
            }

            sb.append(smaller.peek()).append("\n");
        }

        System.out.println(sb);
    }
}