package com.practice.makes.perfect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *  BJ 11660 구간합구하기5
 *  2차원 배열 누적합 문제
 *  @author leejuhyun
 *  
 *  Dynamic Programming
 *  Sum btwn (x1, y1) ~ (x2, y2)
 *  = sumArray[x2][y2] - sumArray[x1-1][y2] - sumArray[x2][y1-1] + sumArray[x1-1][y1-1]
 *  
 *  Memory:125196KB , Time: 920ms
 */

public class BJ_11660_구간합구하기5 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(st.nextToken());  // 2차원 배열의 크기 
		int M = Integer.parseInt(st.nextToken());  // 합을 구해야 하는 횟수 
		
		// Make a 2D array
		int[][] sumArray = new int[N+1][N+1];
		
		// 데이터 받으면서 가로 누적합 
		for (int row = 1; row <= N; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 1; col <= N; col++) {
				sumArray[row][col] = Integer.parseInt(st.nextToken());
				sumArray[row][col] += sumArray[row][col-1];
			}
		}
		
		// 세로 누적합 
		for (int col = 1; col <= N; col++) {
			for (int row = 1; row <= N; row++) {
				sumArray[row][col] += sumArray[row-1][col];
			}
		}
		
		int x1, y1, x2, y2, sum;
		// Compute sum
		for (int m=1; m<=M; m++) {
			st = new StringTokenizer(br.readLine());
			x1 = Integer.parseInt(st.nextToken());
			y1 = Integer.parseInt(st.nextToken());
			x2 = Integer.parseInt(st.nextToken());
			y2 = Integer.parseInt(st.nextToken());
			
			sum = sumArray[x2][y2] - sumArray[x1-1][y2] - sumArray[x2][y1-1] + sumArray[x1-1][y1-1];
			
			sb.append(sum + "\n");
		}
		
		System.out.println(sb.toString());
	}

}
