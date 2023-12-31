import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BJ 11401 이항계수3
 * @author leejuhyun
 * 
 * N Combination R을 1000000007로 나눈 나머지 출력하기
 * 정수론, Fermat Little Theorem
 * 
 * Memory: 45104kb
 * Time: 204ms
 */
public class BJ_11401_이항계수3 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		
		System.out.println(nCr(N, R, 1000000007));
		
	}
	
	static long nCr(int n, int r, int p) {
		if (r == 0) return 1L;
		
		long[] fac = new long[n+1];
		fac[0] = 1;
		
		for (int i = 1; i <= n; i++) {
			fac[i] = fac[i-1]*i % p;
		}
		
		return (fac[n]*power(fac[r], p-2, p)%p*power(fac[n-r], p-2, p) %p) % p;
	}

	private static long power(long x, long y, long p) {
		long res = 1L;
		x = x % p;

		while (y>0) {
			if (y%2==1) res = (res*x)%p;
			y = y >> 1; // y = y/2
			x = (x*x) % p;
		}
		return res;
	}

}
