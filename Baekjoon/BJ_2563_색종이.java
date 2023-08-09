package com.practice.makes.perfect;

import java.util.Scanner;

/**
 * BJ 2563 색종이
 * @author leejuhyun
 * 
 * 겹치는 영역 찾기 
 * 색종이의 위치가 주어지면, 색종이의 크기만큼 1로 표시를 해준다.
 * 단, 색종이를 1로 표시되지 않았을 때에만 1로 배열을 바꿔준 후
 * 마지막에 1의 개수를 세면, 영역의 넓이를 구할 수 있다. 
 * 
 * Memory: 17964kb
 * Time: 232ms
 */

public class BJ_2563_색종이 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int[][] grid = new int[100][100];
		int n = sc.nextInt();
		int count = 0;
		
		for (int i = 0; i < n; i++) {
			int x = sc.nextInt();   
			int y = sc.nextInt();  
			
			for (int j = 0; j < 10; j++) {
				for (int k = 0; k < 10; k++) {
					if (grid[100 - 1 - y - j][x + k] == 0) {  // 영역이 아직 마킹되지 않았을 때에만 
						grid[100 - 1 - y - j][x + k] = 1;     // 1로 표시를 해준다 
					}
				}
			}
		}
		
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j< 100; j++) {
				if (grid[i][j] == 1) {
					count++;   // 1의 개수를 세면, 색종이 영역의 넓이를 구할 수 있다 
				}
			}
		}
		
		System.out.println(count);

	}

}
