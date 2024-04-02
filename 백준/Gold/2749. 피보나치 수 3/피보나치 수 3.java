import java.io.*;

public class Main{

	static int MOD = 1_000_000;
	static int MAX = 1_500_000;
	static long[] pivos = new long[MAX];
	
	public static long pivo(long n, int p) {
		if(n == 0) return 0;
		if(n == 1) return 1;

		long pivo1 = 0;
		long pivo2 = 0;
		int ni = (int)n;
		if(n % 2 == 1) {
			n = n/2;
			if(pivos[ni] == 0 ) pivos[ni] = pivo(n,p);
			if(pivos[ni+1] == 0 ) pivos[ni+1] = pivo(n+1,p);
			pivo1 = pivos[ni+1];
			pivo2 = pivos[ni];
			return ((pivo1 * pivo1) % p + (pivo2 * pivo2) % p) % p;
		}else {
			n = n/2;
			if(pivos[ni] == 0 ) pivos[ni] = pivo(n,p);
			if(pivos[ni-1] == 0 ) pivos[ni-1] = pivo(n-1,p);
			pivo1 = pivos[ni];
			pivo2 = pivos[ni-1];
			return ((pivo1 * pivo1) % p + 2*(pivo1 * pivo2) % p) % p;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		long N = Long.parseLong(br.readLine());
		
		N = N % 1500000;
		
		/*
		 * pivo[2n+1] = pivo[n+1]^2 + pivo[n]^2;
		 * pivo[2n] = pivo[n]^2 + 2*pivo[n]*pivo[n-1];
		 */
		
		System.out.println(pivo(N,MOD));

	}
}
