import java.util.Arrays;
import java.util.Scanner;

/**
 * BJ 1463 1로만들기
 * @author leejuhyun
 * 
 * Memory: 21696kb
 * Timne: 244ms
 */

public class BJ_1463_1로만들기 {
	
	static int n, memo[];

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		memo = new int[n+4];
		Arrays.fill(memo, -1);
		
		memo[0] = 0;
		memo[1] = 0;
		memo[2] = 1;
		memo[3] = 1;
		 
		if (n < 4) {
			System.out.println(memo[n]);
		} else {
			for (int i=4; i<=n; i++) {
				int a = memo[i-1]+1;
				int b = memo[i/2]+1+i%2;
				int c = memo[i/3]+1+i%3;
				memo[i] = Math.min(a, Math.min(b, c));
				
			}
			System.out.println(memo[n]);
		}
		
	}
	

}
