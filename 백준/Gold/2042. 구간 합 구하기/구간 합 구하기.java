import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
    static long[] num;
    static long[] tree;

    static void build(int node, int start, int end) {
        if (start == end) {
            tree[node] = num[start];
        } else {
            int mid = (start + end) / 2;
            int leftChild = node << 1;
            build(leftChild, start, mid);
            build(leftChild + 1, mid + 1, end);
            tree[node] = tree[leftChild] + tree[leftChild + 1];
        }
    }

    static void update(int node, int start, int end, int index, long value) {
        if (start == end) {
            tree[node] = value;
        } else {
            int mid = (start + end) / 2;
            int leftChild = node << 1;
            if (index <= mid) {
                update(leftChild, start, mid, index, value);
            } else {
                update(leftChild + 1, mid + 1, end, index, value);
            }
            tree[node] = tree[leftChild] + tree[leftChild + 1];
        }
    }

    static long sum(int node, int start, int end, int left, long right) {
        if (end < left || right < start) return 0;
        if (left <= start && end <= right) return tree[node];
        int mid = (start + end) / 2;
        int leftChild = node << 1;
        return sum(leftChild, start, mid, left, right) + sum(leftChild + 1, mid + 1, end, left, right);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        num = new long[N + 1];
        tree = new long[4 * N];

        for (int i = 1; i <= N; i++) {
            num[i] = Long.parseLong(br.readLine());
        }

        build(1, 1, N);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            long b = Long.parseLong(st.nextToken());
            if (command == 1) {
                update(1, 1, N, a, b);
            } else {
                sb.append(sum(1, 1, N, a, b)).append("\n");
            }
        }

        System.out.print(sb.toString());
    }
}