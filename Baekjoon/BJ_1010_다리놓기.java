import java.util.Arrays;
import java.util.Scanner;

/**
 * BJ 1010 다리놓기
 * @author leejuhyun
 * 
 * 다리를 놓는 "경우의 수" 문제이고, 시간 제한이 tight하므로 DP를 떠올릴 수 있다.
 * N과 M이 주어졌을 때 M개 중에 N개를 뽑는 조합으로 볼 수 있다.
 * 
 * Memory: 19492kb
 * Time: 288ms
 */

public class BJ_1010_다리놓기 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int idx = 0; idx < T; idx++) {
			int N = sc.nextInt();
			int M = sc.nextInt();
			int[][] memo = new int[M+1][N+1]; 
			
			for (int i = 0; i <= M; i++) {
				for (int j = 0, end = Math.min(i, N); j <= end; j++) {
					if (j==0 || i==j) memo[i][j] = 1;
					else memo[i][j] = memo[i-1][j-1] + memo[i-1][j];
				}
			}
//			System.out.println(Arrays.deepToString(memo));
			System.out.println(memo[M][N]);
		}
	
	}

}
