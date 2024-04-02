import java.io.*;
import java.math.BigInteger;

public class Main{

	static int MAX = 1_000_000;
	static BigInteger[] pivos = new BigInteger[MAX];
	
	public static BigInteger pivo(int n) {
		if(n == 0) return BigInteger.ZERO;
		if(n == 1) return BigInteger.ONE;

		BigInteger pivo1 = BigInteger.ZERO;
		BigInteger pivo2 = BigInteger.ZERO;
		if(n % 2 == 1) {
			n = n/2;
            if(pivos[n] == null ) pivos[n] = pivo(n);
            if(pivos[n+1] == null ) pivos[n+1] = pivo(n+1);
            pivo1 = pivos[n+1];
            pivo2 = pivos[n];
			return pivo1.multiply(pivo1).add(pivo2.multiply(pivo2));
		}else {
			n = n/2;
            if(pivos[n] == null ) pivos[n] = pivo(n);
            if(pivos[n-1] == null ) pivos[n-1] = pivo(n-1);
            pivo1 = pivos[n];
            pivo2 = pivos[n-1];
			return pivo1.multiply(pivo1).add(pivo1.multiply(pivo2).multiply(BigInteger.valueOf(2)));
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		/*
		 * pivo[2n+1] = pivo[n+1]^2 + pivo[n]^2;
		 * pivo[2n] = pivo[n]^2 + 2*pivo[n]*pivo[n-1];
		 */
		
		System.out.println(pivo(N));

	}
}
