package com.practice.makes.perfect;

import java.util.Arrays;
import java.util.Scanner;

/**
 * BJ 2630 색종이만들기
 * @author leejuhyun
 * 
 * Divide and Conquer
 * 전체 종이(가로, 세로 N)가 같은 색으로 칠해져 있지 않으면, 가로와 세로의 중간부분으로 잘라서
 * 4개의 N/2, N/2으로 만든다. 
 * 잘라진 종이가 모두 하얀색 또는 파란색으로 칠해져 있거나 하나의 정사각형이 될 때까지 반복한다. 
 * 
 * Memory: 34408kb
 * Time: 420ms
 */

public class BJ_2630_색종이만들기 {

	static int[][] grid;
	static int white, blue;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		grid = new int[N][N];
		
		// Make a grid
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				grid[i][j] = sc.nextInt();
			}
		}
		
//		System.out.println(Arrays.deepToString(grid));
	
		dc(0, 0, N);
		System.out.println(white);
		System.out.println(blue);

	}
	
	private static void dc(int r, int c, int size) { // 영역의 좌상단 r, c, 영역크기 size 
		int sum = 0;
		for (int i = r; i < r + size; i++) {
			for (int j = c; j < c + size; j++) {
				sum += grid[i][j];
			}
		}
		
		if (sum == 0) {  // 더한 값이 0이라면, 그 부분은 모두 하얀색 색종이임을 뜻한다. 기저 조건이 포함됨.
			white++;
		} else if (sum == size * size) {  // 더한 값이 size와 같다면, 파란색 색종이. 기저 조건 포함.
			blue++;
		} else {         // 두 색이 섞여있는 공간 
			int half = size / 2;
			dc(r, c, half);
			dc(r, c + half, half);
			dc(r + half, c, half);
			dc(r + half, c + half, half);
		}

	}

}
