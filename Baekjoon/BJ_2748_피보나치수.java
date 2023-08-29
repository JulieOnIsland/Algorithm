import java.util.Arrays;
import java.util.Scanner;

/**
 * BJ 2748 피보나치수
 * @author leejuhyun
 *
 * DP
 * 
 * Memory: 17724kb
 * Time: 228ms
 */

public class BJ_2748_피보나치수 {

	static int N;
	static long memo[];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		memo = new long[N+1];
		Arrays.fill(memo, -1);  // -1로 초기화
		memo[0] = 0;
		memo[1] = 1;
		recursion(N); 
//		System.out.println(Arrays.toString(memo)); 
		System.out.println(memo[N]);

	}
	
	private static long recursion(int n) {	
		if (memo[n] == -1) {
			memo[n] = recursion(n-1) + recursion(n-2);
		} 
		return memo[n];
	}

}
