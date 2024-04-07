import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{

    static int N,M;

    static int[] num;
    static int[] minTree;
    static int[] maxTree;

    static int makeMinTree(int start, int end, int node){
        if(start == end) return minTree[node] = num[start];
        int mid = (start+end)/2;
        return minTree[node] = Math.min(makeMinTree(start,mid,node*2),makeMinTree(mid+1,end,node*2+1));
    }

    static int min(int start, int end, int node, int from, int to){
        if(start > to || end < from) return Integer.MAX_VALUE;
        if(start >= from && end <= to) return minTree[node];
        int mid = (start+end)/2;
        int leftChild = node*2;
        return Math.min(min(start,mid,leftChild,from,to),min(mid+1,end,leftChild+1,from,to));
    }

    static int makeMaxTree(int start, int end, int node){
        if(start == end) return maxTree[node] = num[start];
        int mid = (start+end)/2;
        return maxTree[node] = Math.max(makeMaxTree(start,mid,node*2),makeMaxTree(mid+1,end,node*2+1));
    }

    static int max(int start, int end, int node, int from, int to){
        if(start > to || end < from) return 0;
        if(start >= from && end <= to) return maxTree[node];
        int mid = (start+end)/2;
        int leftChild = node*2;
        return Math.max(max(start,mid,leftChild,from,to),max(mid+1,end,leftChild+1,from,to));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokens;

        /*
            a번째 ~ b번째 정수 중
            제일 작은, 제일 큰 정수 찾기
         */

        tokens = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        num = new int[N+1];
        minTree = new int[N*4];
        maxTree = new int[N*4];

        for (int i = 1; i <= N; i++) {
            num[i] = Integer.parseInt(br.readLine());
        }

        makeMinTree(1,N,1);
        makeMaxTree(1,N,1);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            tokens = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(tokens.nextToken());
            int to = Integer.parseInt(tokens.nextToken());
            int min = min(1,N,1,from,to);
            int max = max(1,N,1,from,to);
            sb.append(min).append(" ").append(max).append("\n");
        }
        System.out.println(sb);
    }
}