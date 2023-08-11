package live.algorithm.basic;

import java.util.Scanner;

/**
 * BJ 2839 설탕배달
 * @author leejuhyun
 *
 * Memory: 17756kb
 * Time: 208ms
 */

public class BJ_2839_설탕배달_이주현 {
	
	static int N;
	static int minAns;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();       // 18
		
		while (N > 0) {
			if (N % 5 == 0) {
				minAns += N / 5;
				break;
			}
			N -= 3;
			minAns++;
		}
		
		if (N < 0) {
			System.out.println(-1);
		} else {
			System.out.println(minAns);
		}
	}

}


/*
 * 시간초과 풀이: Depth First Search로 접근

public class BJ_2839_설탕배달_이주현2 {
	
	static int N;
	static int minAns;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		minAns = Integer.MAX_VALUE;
		
		dfs(0, 0, 0, 0);
		
		if (minAns == Integer.MAX_VALUE) {
			minAns = -1;
			System.out.println(minAns);
		} else {
			System.out.println(minAns);
		}
	}
	
	private static void dfs(int cnt, int fivecnt, int threecnt, int sum) {
		if (sum > N) {
			return;
		}
		
		if (sum == N) {
			if (cnt < minAns) {
				minAns = cnt;
			}
		} 
		
		// 5kg
		dfs(cnt + 1, fivecnt + 1, threecnt, sum + 5);
		
		// 3kg
		dfs(cnt + 1, fivecnt, threecnt + 1, sum + 3);

	}

}
*/
