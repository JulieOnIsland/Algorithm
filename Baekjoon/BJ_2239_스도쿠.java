package com.obsession;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BJ 2239 스도쿠
 * @author leejuhyun
 * 
 * 0인 부분을 찾는다 (findEmpty() 메서드)
 * 0인 부분에 1부터 9까지의 값을 넣으면서 이 값을 넣어도 되는지를 체크할 것이다 (isValid() 메서드) 
 * solveSudoku() 메서드 안에서 isValid()로 특정 숫자를 넣어도 되는지 체크하면서 
 * valid하면 숫자를 넣고, 그 다음 진행. 만약 invalid하다면, 그 값을 0으로 돌려놓고 (backtrack) 다른 숫자 탐색 
 * 
 * Memory: 22616kb, Time: 696ms
 */

public class BJ_2239_스도쿠 {
	
	private static int[][] grid;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		grid = new int[9][9];
		for (int i = 0; i < grid.length; i++) {
			st = new StringTokenizer(br.readLine());
			char[] temp = st.nextToken().toCharArray();
			for (int j = 0; j < grid[0].length; j++) {
				grid[i][j] = (int) temp[j]-48;
				
			}
		}
//		print(grid);
		
		solveSudoku();
		
//		print(grid);
		
		// print the answer
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				System.out.print(grid[i][j]);
			}
			System.out.println();
		}


	}
	
	private static void print(int[][] grid) {
		
		for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                System.out.print(grid[i][j] + " ");
                if ((j + 1) % 3 == 0 && j != 8) {
                    System.out.print("| ");
                }
            }
            System.out.println();
            if ((i + 1) % 3 == 0 && i != 8) {
                System.out.println("---------------------");
            }
        }

	}

	private static int[] findEmpty() {
		int[] emptyCoordinate = new int[2];
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == 0) {
					 emptyCoordinate[0] = i;
					 emptyCoordinate[1] = j;
					 return emptyCoordinate;
				}
			}
		}
		return null;
		
	}
	
	private static boolean isValid(int row, int col, int num) {
		// => The element should not appear twice 
		// If it does, we'll return False. If it doesn't, we'll return true (valid).
		// Check row
		for (int i = 0; i < grid[0].length; i++) {
			if (grid[row][i] == num) return false;
		}
		
		// Check column
		for (int i = 0; i < grid.length; i++) {
			if (grid[i][col] == num) return false;
		}
		
		// Check 3 x 3 box
		int boxRow = row / 3;
		int boxCol = col / 3;
		for (int i = boxRow*3; i < boxRow*3+3; i++) {
			for (int j = boxCol*3; j < boxCol*3+3; j++) {
				if (grid[i][j] == num) return false;
			}
		}
		
		return true;
		
	}
	
	private static boolean solveSudoku() {
		
		int[] emptyCoordinate = findEmpty();
		if (emptyCoordinate == null) return true; // If there's no 0, then this means that we completed the sudoku!
	
		int row = emptyCoordinate[0];
		int col = emptyCoordinate[1];
		
		for (int num = 1; num <= 9; num++) {
			if (isValid(row, col, num)) {
				grid[row][col] = num;
				
				if (solveSudoku()) return true;   // recursively call solveSudoku function
				grid[row][col] = 0; // backtrack
			}
		}
		return false;
		

	}
	
}
