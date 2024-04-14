import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokens;

        /*
            1. 숫자 2개 조합으로 만들 수 있는 모든 수 set에 저장
            2. a + b = a or b인 상황 처리
            3. N개 숫자중 set에 포함되는 숫자 개수 출력
         */

        int N = Integer.parseInt(br.readLine());

        int[] nums = new int[N];
        HashMap<Integer, Integer> numsCount = new HashMap<>();

        tokens = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(tokens.nextToken());
            nums[i] = num;
            int count = numsCount.getOrDefault(num,0);
            numsCount.put(num,count+1);
        }

        Arrays.sort(nums);

        int answer = 0;
        boolean[] checked = new boolean[N];
        for (int i = 0; i < N; i++) {
            for (int j = i+1; j < N; j++) {
                int iCount = numsCount.get(nums[i]);
                int jCount = numsCount.get(nums[j]);

                if(nums[i] == 0 && nums[j] == 0 && iCount < 3 ||
                    nums[i] == 0 && jCount < 2 ||
                    nums[j] == 0 && iCount < 2) continue;

                int sum = nums[i] + nums[j];
                int k = Arrays.binarySearch(nums,sum);
                if(k >= 0 && !checked[k]){
                    checked[k] = true;
                    answer += numsCount.get(sum);
                }
            }
        }

        System.out.println(answer);
    }
}