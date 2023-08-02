package com.practice.makes.perfect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BJ 11659 구간합구하기4
 * 1차원 누적합 문제
 * @author leejuhyun
 * 
 * Dynamic Programming
 * Sum[A, B] = Sum[B] - Sum[A-1]
 * 
 * Memory: 56684KB, Time: 740ms
 */

public class BJ_11659_구간합구하기4 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());  // 수의 개수 
		int M = Integer.parseInt(st.nextToken());  // 합을 구해야 하는 횟수 
		
		// Let's make a sumArray: accumulated sum array -> sumArray[i] = sumArray[i-1] + input[i]
		int[] sumArray = new int[N+1];
		
		st = new StringTokenizer(br.readLine());
		for (int i=1; i<=N; i++) {
			sumArray[i] = Integer.parseInt(st.nextToken());
			sumArray[i] += sumArray[i-1];
		}
		
		// Let's compute sum between A and B
		int A;
		int B;
		for (int i=1; i<=M; i++) {
			st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			sb.append(sumArray[B] - sumArray[A-1] + "\n");
		}
		
		System.out.println(sb.toString());
		
	}

}