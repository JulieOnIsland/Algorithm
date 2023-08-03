package com.practice.makes.perfect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SWEA D2 2001 파리퇴치
 * @author leejuhyun
 * 
 * N*N 그리드에서 M*M 크기의 영역의 숫자 합을 탐색하면서 
 * 합의 최대를 구하는 문제 
 * 
 * Memory: 20472kb, Time: 120ms
 * 
 */

public class D2_2001_파리퇴치 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int test_cases = Integer.parseInt(st.nextToken());
		
		for (int test_case=1; test_case <= test_cases; test_case++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			int[][] grid = new int[n][n];
			// Make a grid
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					grid[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int sum = 0;
			for (int row = 0; row < n - m + 1; row++) {
				for (int col = 0; col < n - m + 1; col++) {
					int temp_sum = 0;
					for (int i = row; i < row + m; i++) {
						for (int j = col; j < col + m; j++) {
							temp_sum += grid[i][j];
						}
					}
					
					if (temp_sum > sum) {
						sum = temp_sum;
					}
				}
			}
			
			System.out.printf("#%d %d", test_case, sum);
			System.out.println();
		}
		
		
	}

}
