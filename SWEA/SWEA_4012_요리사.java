package com.practice.makes.perfect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class SWEA_4012_요리사 {

	static int n;
	static int[][] grid;
	static int[] selected;
	static List<int[]> list;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int test_case = sc.nextInt();
		for (int idx = 1; idx <= test_case; idx++) {
			n = sc.nextInt();
			grid = new int[n+1][n+1];
			selected = new int[n/2];
			
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					grid[i][j] = sc.nextInt();
				}
			}
			
			list = new ArrayList<>();
			
			comb(0, 1);
			
			int synergyA = 0; int synergyB = 0;
			int minAns = Integer.MAX_VALUE;
			
			for (int i = 0; i < list.size() / 2; i++) {
				int[] tempA = list.get(i);
				for (int j = 0; j < tempA.length; j++) {  // 순열 
					for (int k = 0; k < tempA.length; k++) {
						if (k != j) {
							synergyA += grid[tempA[j]][tempA[k]];
						}
					}
				}
				
				int[] tempB = list.get(list.size() - 1 -i);
				for (int j = 0; j < tempB.length; j++) {  // 순열 
					for (int k = 0; k < tempB.length; k++) {
						if (k != j) {
							synergyB += grid[tempB[j]][tempB[k]];
						}
					}
				}
				
				int diff = Math.abs(synergyA - synergyB);
				if (diff < minAns) minAns = diff;
				synergyA = 0; synergyB = 0;
			
			}
			
			System.out.println("#"+idx+" "+minAns);
			

		}
		
	}
	
	private static void comb(int cnt, int start) {
		if (cnt == n/2) {
//			System.out.println(Arrays.toString(selected));
			list.add(Arrays.copyOf(selected, selected.length));
			return;
		}
		
		for (int i = start; i <= n; i++) {
			selected[cnt] = i;
			comb(cnt + 1, i + 1);
		}

	}

}
