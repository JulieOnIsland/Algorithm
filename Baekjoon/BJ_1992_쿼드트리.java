package com.practice.makes.perfect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * BJ 1992 쿼드트리
 * @author leejuhyun
 * 
 * Divide and Conquer
 * Quad Tree: 흑백 영상을 압축하여 표현하는 데이터 구조 
 *
 * 주어진 영역이 모두 0으로만 되어 있으면 압축 결과는 0이 되고, 모두 1로만 되어 있으면 압축 결과는 1이 되고,
 * 0과 1이 섞여 있으면, 왼쪽 위, 오른쪽 위, 왼쪽 아래, 오른쪽 아래, 이렇게 4개의 영상으로 나누어 압축하는데 
 * 압축한 결과를 차례로 괄호 안에 묶어서 표현. 
 * 
 * Memory: 24248kb
 * Time: 332ms
 */

public class BJ_1992_쿼드트리 {
	
	static int n;
	static int[][] grid;
	static List<String> list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		grid = new int[n][n];
		
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			char[] temp = st.nextToken().toCharArray();
			for (int j=0; j<n; j++) {
				grid[i][j] = temp[j] - '0';
			}
		}
		
//		System.out.println(Arrays.deepToString(grid));
		
		list = new ArrayList<>();
		
		dc(0, 0, n);
		
		for (String element: list) {
			System.out.print(element);
		}
		
		
	}
	
	private static void dc(int r, int c, int size) {
		int sum = 0;
		for (int i=r; i<r+size; i++) {
			for (int j=c; j<c+size; j++) {
				sum += grid[i][j];
			}
		}
		
		// 영역의 합이 0이면, 그 부분은 모두 0으로만 이루어져 있음. 
		if (sum == 0) {
			list.add("0");
		} 
		// 영역의 합이 size*size라면, 그 부분은 모두 1로만 이루어져 있음. 
		else if (sum == size * size) {
			list.add("1");
		}
		// 그렇지 않다면, 영역을 반으로 쪼개서(divide) 문제를 해결한다(conquer) 
		else {
			int half = size/2;
			list.add("(");
			dc(r, c, half);
			dc(r, c+half, half);
			dc(r+half, c, half);
			dc(r+half, c+half, half);
			list.add(")");
		}

	}

}













