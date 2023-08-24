package com.practice.makes.perfect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.StringTokenizer;

/**
 * D4 3234 준환이의양팔저울
 * @author leejuhyun
 * 
 * Permutation & Pruning 
 * 
 */

public class D4_3234_준환이의양팔저울 {

	static int ans; // 총 경우의 수 세는 변수 
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int test_case = Integer.parseInt(st.nextToken());
		for (int idx = 1; idx <= test_case; idx++) {
			ans = 0;
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // N도 static으로 선언하지 않고 함수의 매개변수로 넘겨주었다 
			int[] weights = new int[N]; // static 변수로 선언하는 것보다 함수의 매개변수로 넘기는 것이 더 빠르다 
			st = new StringTokenizer(br.readLine());
			for (int i=0; i<N; i++) {
				weights[i] =  Integer.parseInt(st.nextToken());
			}
			
			perm(0, weights, new boolean[N], 0, 0, N);
//			perm2(0, weights, new boolean[N], 0, 0, N);
			
			System.out.println("#"+idx+" "+ans);
		}
	}


	// permutation 
	// Memory: 19,940kb, Time: 1,689ms
	private static void perm(int cnt, int[] weights, boolean[] selected, int left, int right, int N) {
		
		// basis part
		if (cnt == N) {
			ans++;
			return;
		}
		
		// inductive part
		for (int i = 0; i < N; i++) {
			if (selected[i]) continue;
			selected[i] = true;
			// 왼쪽 저울에 추 올리기 
			perm(cnt+1, weights, selected, left+weights[i], right, N);
			// 오른쪽 저울에 i번째 추를 추가했을 때 왼쪽 저울 무게를 넘지 않으면 추 올리기 
			if (right+weights[i] < left) {  // pruning
				perm(cnt+1, weights, selected, left, right+weights[i], N);
			}
			selected[i] = false;
		}
	}
	
	// permutation : pruning 코드 살짝 다르게 
	// Memory: 20,036kb, Time: 1,725ms
	private static void perm2(int cnt, int[] weights, boolean[] selected, int left, int right, int N) {
		
		if (left < right) return;  // pruning 
		
		// basis part
		if (cnt == N) {
			ans++;
			return;
		}
		
		// inductive part
		for (int i = 0; i < N; i++) {
			if (selected[i]) continue;
			selected[i] = true;
			// 왼쪽 저울에 추 올리기 
			perm2(cnt+1, weights, selected, left+weights[i], right, N);
			// 오른쪽 저울에 i번째 추를 추가했을 때 왼쪽 저울 무게를 넘지 않으면 추 올리기 
			perm2(cnt+1, weights, selected, left, right+weights[i], N);
			selected[i] = false;
		}
	}
}
