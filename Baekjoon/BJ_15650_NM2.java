package com.practice.makes.perfect;

/**
 * @author leejuhyun
 * 백준 15650: N과 M (2)
 * 중복없는 조합
 * 1부터 N까지 중복 없이 M개를 고른 수열 (순서가 의미 없으므로 조합문제)
 * 
 */

import java.util.Scanner;

public class BJ_15650_NM2 {

	static int N, M;
	static int[] numArray;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		numArray = new int[M];
		
		comb(0, 1);

	}
	
	// cnt: 뽑은 숫자의 개수, start: 배열의 시작점 
	private static void comb(int cnt, int start) {
		if (cnt == M) {
			for (int element: numArray) {
				System.out.print(element+" ");
			}
			System.out.println();
			return;
		}
		
		for (int i=start; i <= N; i++) {
			numArray[cnt] = i;
			comb(cnt+1, i+1);
		}
		
	}

}
