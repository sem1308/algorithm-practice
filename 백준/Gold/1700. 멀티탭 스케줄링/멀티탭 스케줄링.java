import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokens;

        /*
           N : 멀티탭 구멍 개수 , 1 <= N <= 100
           K : 전기 용품 총 사용횟수 , 1 <= K <= 100

            _ _ _

            A B C D B D B

            현재 꽂혀있는 전기요품 중
            가장 나중에 사용될 전기용품 제거

            전기용품 별 사용될 순서 저장

            현재 순서에 해당하는 정기용품의 순서 맨 앞 제거

            현재 꽃혀있는 전기용품 insertion sort
         */

        tokens = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(tokens.nextToken());
        int K = Integer.parseInt(tokens.nextToken());

        tokens = new StringTokenizer(br.readLine());

        int[] nums = new int[K];

        List<Integer>[] orders = new LinkedList[K+1];

        for (int i = 1; i <= K; i++) {
            orders[i] = new LinkedList<>();
        }

        for (int i = 0; i < K; i++) {
            int num = Integer.parseInt(tokens.nextToken());
            nums[i] = num;
            orders[num].add(i);
        }

        for (int i = 1; i <= K; i++) {
            orders[i].add(K);
        }

        if(N >= K){
            System.out.println(0);
            return;
        }

        boolean[] pluged = new boolean[K+1];
        int[] plugNums = new int[N];
        int numPluged = 0;
        int i = 0;
        for (; i < K && numPluged < N; i++) {
            int num = nums[i];
            orders[num].remove(0);
            if(pluged[num]) continue;
            plugNums[numPluged++] = num;
            pluged[num] = true;
        }

        int cnt = 0;

        for (; i < K; i++) {
            int num = nums[i];
            orders[num].remove(0);
            if(pluged[num]) continue;
            int removeIdx = 0;
            int removeNum = 0;
            int maxOrder = 0;
            for (int j = 0; j < N; j++) {
                int plugNum = plugNums[j];
                int order = orders[plugNum].get(0);
                if(maxOrder < order){
                    maxOrder = order;
                    removeNum = plugNum;
                    removeIdx = j;
                }
            }

            cnt++;

            plugNums[removeIdx] = num;
            pluged[removeNum] = false;
            pluged[num] = true;
        }

        System.out.println(cnt);

    }
}