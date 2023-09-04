package com.practice.makes.perfect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BJ 1026 보물
 * @author leejuhyun
 * 
 * Memory: 14428kb
 * Time: 124ms
 */

public class BJ_1026_보물 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[] A = new int[N];
		int[] B = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < A.length; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < B.length; i++) {
			B[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(A);
		Arrays.sort(B);
//		System.out.println(Arrays.toString(A));
//		System.out.println(Arrays.toString(B));
		
		int S = 0;
		for (int i = 0; i < N; i++) {
			S += A[i] * B[N-1-i];
		}
		System.out.println(S);

	}

}
