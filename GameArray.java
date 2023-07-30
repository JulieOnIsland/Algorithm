package com.practice.makes.perfect;

/**
 * Move 8 directions in 2D array: up, down, left, right, diagonal
 */

import java.util.Scanner;

public class GameArray {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int[][] directionList = {
				{0, 0}, {-1, 0}, {-1, 1},
				{0, 1}, {1, 1}, {1, 0},
				{1, -1}, {0, -1}, {-1, -1}};
			
		int test_case = sc.nextInt();
		
		for (int idx=1; idx<test_case+1; idx++) {
			
			// Make a game board
			int r = sc.nextInt(); int c = sc.nextInt(); int num_player = sc.nextInt();
			
			int[][] grid = new int[r][c];
			
			for (int i=0; i < r; i++) {
				for (int j=0; j < c; j++) {
					grid[i][j] = sc.nextInt();
				}
			}
			
			int total_money = 0;
			
			for (int i=0; i < num_player; i++) {
				int init_row =  sc.nextInt(); 
				int init_col =  sc.nextInt(); 
				int num_game =  sc.nextInt(); 
				
				int curr_row = 0;
				int curr_col = 0;
				
				for (int j=0; j < num_game; j++) {
					int temp = grid[init_row-1][init_col-1];
					int distance = temp % 10;
					int direction = (temp - distance) / 10;
					
					curr_row = init_row + directionList[direction][0] * distance;
					curr_col = init_col + directionList[direction][1] * distance;
					
					if (curr_row < 1 || curr_col < 1 || curr_row > r || curr_col > c) {
						break;
					}
					
					init_row = curr_row;
					init_col = curr_col;
				}
				
				if (curr_row < 1 || curr_col < 1 || curr_row > r || curr_col > c) {
					total_money -= 1000;
				} else {
					 total_money += grid[init_row-1][init_col-1] * 100 - 1000;
				}
			}
			
			System.out.printf("#%d %d", idx, total_money);
			System.out.println();
		}

	}

}



















