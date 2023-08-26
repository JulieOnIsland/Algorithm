package com.practice.makes.perfect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BJ 2798 블랙잭
 * @author leejuhyun
 * 
 * 완전탐색 - 조합 
 * Memory: 14176kb
 * Time: 124ms
 */

public class BJ_2798_블랙잭 {
	
	static int N, M;
	static int numArray[], selected[];
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		numArray = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			numArray[i] = Integer.parseInt(st.nextToken());
		}
		
		selected = new int[3];
		comb(0, 0, 0);
		System.out.println(ans);
		
	}
	
	private static void comb(int cnt, int start, int result) {
		if (result > M) return; // pruning
		
		if (cnt == 3) {
			if (result > ans) ans = result;
//			System.out.println(Arrays.toString(selected));
//			System.out.println(result);
			return;
		}
		
		for (int i=start; i<N; i++) {
			selected[cnt] = numArray[i];
			comb(cnt+1, i+1, result+numArray[i]);
		}

	}

}
