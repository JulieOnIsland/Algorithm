package com.practice.makes.perfect;

/**
 * 백준 15649: N과 M (1)
 * 중복없는 순열
 * 1부터 N까지 중복 없이 M개를 고른 수열 
 * 재귀 활용 
 */

import java.util.Scanner;

public class 15649_NM1 {

	static int n, m;
	static int[] result;
	static boolean[] isSelected;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		
		result = new int[m];
		isSelected = new boolean[n+1];
		
		permutation(0);

	}
	
	private static void permutation(int x) {
		if (x==m) {
			for (int element: result) {
				System.out.print(element+" ");
			}
			System.out.println();
			return;
		}
		
		for (int i = 1; i < n+1; i++) {
			if (isSelected[i]) { // 이미 사용했다면 pass
				continue;
			}
			result[x] = i;
			isSelected[i] = true;
			permutation(x+1);
			isSelected[i] = false;
		}
	}
}














