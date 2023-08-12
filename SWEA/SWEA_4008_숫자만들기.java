package com.practice.makes.perfect;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 
 * @author leejuhyun
 * 
 * DFS
 * 
 * Memory:25,200kb
 * Time: 173ms
 */

public class SWEA_4008_숫자만들기 {
	
	static int n;
	static int[] numArray, opArray;
	static int ansMax, ansMin;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int test_case = sc.nextInt();
		
		for (int idx = 1; idx <= test_case; idx++) {
			n = sc.nextInt();
			
			opArray = new int[5];
			for (int i = 1; i < 5; i++) {
				opArray[i] = sc.nextInt();
			}
		
			numArray = new int[n];
			for (int i = 0; i < n; i++) {
				numArray[i] = sc.nextInt();
			}

			ansMax = Integer.MIN_VALUE;
			ansMin = Integer.MAX_VALUE;
			
			dfs(0, opArray[1], opArray[2], opArray[3], opArray[4], numArray[0]);  // 0, 2, 1, 0, 1, 0

			System.out.println("#"+idx+" "+ (ansMax - ansMin));
			
		}
		
		

	}
	
	private static void dfs(int cnt, int plus, int minus, int mul, int div, int result) {
		if (plus < 0 || minus < 0 || mul < 0 || div < 0) {  // pruning
			return;    
		}
		
		if (cnt == n - 1) {
			ansMax = Math.max(ansMax, result);
			ansMin = Math.min(ansMin, result);
			return;
		}
		
		// add
		dfs(cnt + 1, plus - 1, minus, mul, div, result + numArray[cnt + 1]);
		// subtract
		dfs(cnt + 1, plus, minus - 1, mul, div, result - numArray[cnt + 1]);
		// multiply
		dfs(cnt + 1, plus, minus, mul - 1, div, result * numArray[cnt + 1]);
		// divide
		dfs(cnt + 1, plus, minus, mul, div - 1, result / numArray[cnt + 1]);

	}

}












