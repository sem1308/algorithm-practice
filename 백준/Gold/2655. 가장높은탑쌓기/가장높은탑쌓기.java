import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Block implements Comparable<Block>{
        int no, width, height, weight;

        public Block(int no, int width, int height, int weight) {
            this.no = no;
            this.width = width;
            this.height = height;
            this.weight = weight;
        }

        @Override
        public int compareTo(Block o) {
            return o.width - this.width;
        }

        public String toString(){
            return no + " ";
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokens;

        /*
            벽돌 아래에서 위로 한 개씩

            1. 벽돌 회전 불가
            2. 밑면 넓이, 무게 같은 벽돌 X
            3. 벽돌 높이 같을 수도 있음
            4. 밑면이 좁은 벽돌 위에 밑면이 넓은 벽돌 놓을 수 없음
            5. 무게 무거운 벽돌 위에 가벼운거 X

            무게 무겁고 밑면 넓은 게 아래

            N : 벽돌 수 <= 100

            밑면 넓이, 높이, 무게

            가장 높은 탑

            1) 밑면 넓은 순서로 정렬
            2) top down 방식으로 그 블럭을 쌓았을 때 가능한 최대 높이 구함
            
            dp[i] : i번째 블럭을 쌓았을 때 가능한 최대 높이
         */

        int N = Integer.parseInt(br.readLine());

        int[] dp = new int[N];
        Block[] blocks = new Block[N];
        Block[] nextBlock = new Block[N];

        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(br.readLine());

            int width = Integer.parseInt(tokens.nextToken());
            int height = Integer.parseInt(tokens.nextToken());
            int weight = Integer.parseInt(tokens.nextToken());

            blocks[i] = new Block(i,width,height,weight);
        }

        Arrays.sort(blocks);

        dp[N-1] = blocks[N-1].height;
        for (int i = N-2; i >= 0; i--) {
            for (int j = i+1; j < N; j++) {
                if(blocks[j].weight < blocks[i].weight){
                    // 다른 블럭의 무게가 현재 블럭보다 가볍다면?
                    // 이 블럭 위에 쌓을 수 있음
                    if(dp[i] < dp[j]){
                        dp[i] = dp[j];
                        nextBlock[blocks[i].no] = blocks[j];
                    }
                }
            }
            dp[i] += blocks[i].height;
        }

        Block result = null;
        int max = 0;
        for (int i = 0; i < N; i++) {
            if(max < dp[i]){
                max = dp[i];
                result = blocks[i];
            }
        }

        StringBuilder sb = new StringBuilder();
        List<Block> resultList = new ArrayList<>();
        while(result != null){
            resultList.add(result);
            result = nextBlock[result.no];
        }

        sb.append(resultList.size()).append("\n");
        for (int i = resultList.size()-1; i >= 0; i--) {
            sb.append(resultList.get(i).no+1).append("\n");
        }

        System.out.println(sb);
    }
}