package com.practice.makes.perfect;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

/**
 *  백준 16935 배열돌리기3
 *  @author leejuhyun
 *  
 *  Stack: LIFO
 *  Queue: FIFO
 *  
 *  추후 보완 필요. 좀 더 깔끔하게.
 *  
 *  Memory:120564kb
 *  Time: 1156ms
 */

public class 백준_16935_배열돌리기3 {


	static int n, m;
	static int[][] grid;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt(); 
		m = sc.nextInt(); 
		int num = sc.nextInt();  // 연산 몇 번 할 것인지?
	
		grid = new int[n][m];
		
		// Make a grid
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				grid[i][j] = sc.nextInt();
			}
		}
		
		for (int i = 0; i < num; i++) {
			int operatorNum = sc.nextInt();  // 몇 번째 연산할 것인지?
			
			if (operatorNum == 1) operator1();
			else if (operatorNum == 2) operator2();
			else if (operatorNum == 3) operator3();
			else if (operatorNum == 4) operator4();
			else if (operatorNum == 5) operator5();
			else if (operatorNum == 6) operator6();
			
		}
		
		for (int[] row: grid) {
			for (int e: row) {
				System.out.print(e+" ");
			}
			System.out.println();
		}

	}
	
	static private void operator1() {
		// stack
		Stack<int[]> stack = new Stack<>();
		n = grid.length;
		m = grid[0].length;
	
		for (int i = 0; i < n; i++) {
			stack.push(grid[i]);
		}
		
		int[][] newGrid = new int[n][m];
		for (int i = 0; i < n; i++) {
			int[] temp = stack.pop();
			 newGrid[i] = temp;
		}
		grid = newGrid;
		
	}
	
	static private void operator2() { 
		n = grid.length;
		m = grid[0].length;
		int[] temp = new int[m]; 
		int[][] newGrid = new int[n][m];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				temp[j] = grid[i][m-1-j];
			}
			newGrid[i] = temp;
			temp = new int[m];
		}
		grid = newGrid;

	}
	
	static private void operator3() {
		// queue
		Queue<int[]> queue = new ArrayDeque<>();
		n = grid.length;
		m = grid[0].length;
		int[] temp = new int[n]; 
		int[][] newGrid = new int[m][n];
		
		
		for (int j = 0; j < m; j++) {
			for (int i = 0; i < n; i++) {
				temp[i] = grid[n-1-i][j];
			}
			queue.offer(temp);
			temp = new int[n];
		}
		
		
		for (int i = 0; i < m; i++) {
			int[] tempRow = queue.poll();
			newGrid[i] = tempRow;
		}
		grid = newGrid;

	}
	
	static private void operator4() {
		// stack
		Stack<int[]> stack = new Stack<>();
		n = grid.length;
		m = grid[0].length;
		
		int[] temp = new int[n]; 
		
		for (int j = 0; j < m; j++) {
			for (int i = 0; i < n; i++) {
				temp[i] = grid[i][j];
			}
			stack.push(temp);
			temp = new int[n];
		}
		
		int[][] newGrid = new int[m][n];
		for (int i = 0; i < m; i++) {
			int[] tempRow = stack.pop();
			newGrid[i] = tempRow;
		}
		grid = newGrid;
	}
	
	static private void operator5() {
		// queue
		Queue<int[]> queue = new ArrayDeque<>();
		n = grid.length;
		m = grid[0].length;
		int[] temp = new int[m];
		int k = 0;
		
		
		// left
		for (int i = 0; i < n/2; i++) {
			for (int j = 0; j < m/2; j++) {
				temp[k++] = grid[i + n/2][j]; 
			}
			for (int j = 0; j < m/2; j++) {
				temp[k++] = grid[i][j];
			}
			queue.offer(temp);
			temp = new int[m];
			k = 0;
		}
		
		// right
		for (int i = 0; i < n/2; i++) {
			for (int j = m/2; j < m; j++) {
				temp[k++] = grid[i + n/2][j]; 
			}
			for (int j = m/2; j < m; j++) {
				temp[k++] = grid[i][j];
			}
			queue.offer(temp);
			temp = new int[m];
			k = 0;
		}
		
		int[][] newGrid = new int[n][m];
		for (int i = 0; i < n; i++) {
			int[] tempRow = queue.poll();
			newGrid[i] = tempRow;
		}
		grid = newGrid;
	
	}
	
	static private void operator6() {
		// queue
		Queue<int[]> queue = new ArrayDeque<>();
		n = grid.length;
		m = grid[0].length;
		int[] temp = new int[m];
		int k = 0;
		
		
		// left
		for (int i = 0; i < n/2; i++) {
			for (int j = m/2; j < m; j++) {
				temp[k++] = grid[i][j]; 
			}
			for (int j = m/2; j < m; j++) {
				temp[k++] = grid[i + n/2][j];
			}
			queue.offer(temp);
			temp = new int[m];
			k = 0;
		}
		
		// right
		for (int i = 0; i < n/2; i++) {
			for (int j = 0; j < m/2; j++) {
				temp[k++] = grid[i][j]; 
			}
			for (int j = 0; j < m/2; j++) {
				temp[k++] = grid[i + n/2][j];
			}
			queue.offer(temp);
			temp = new int[m];
			k = 0;
		}
		
		int[][] newGrid = new int[n][m];
		for (int i = 0; i < n; i++) {
			int[] tempRow = queue.poll();
			newGrid[i] = tempRow;
		}
		grid = newGrid;
		
	}

}






