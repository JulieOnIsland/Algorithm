package com.practice.makes.perfect;

import java.util.Arrays;
import java.util.Scanner;

/**
 * BJ 1074 Z
 * @author leejuhyun
 * 
 * Divide and Conquer
 * 
 * 2차원 배열을 활용했는데 메모리 초과.
 * 배열을 활용하지 않고 풀었는데 시간 초과. 
 * 결국 4개의 사분면으로 나눠서 풀었다. 
 * 
 * Memory: 17772 kb
 * Time: 212 ms
 * 
 */


public class BJ_1074_Z {
	
	static int N, input_r, input_c, cnt;
	static int[][] grid;


	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		input_r = sc.nextInt();
		input_c = sc.nextInt();	
		
		dc(0, 0, 1<<N);
		System.out.println(cnt);


	}
	
	private static void dc(int r, int c, int size) {

		// 크기가 1인 정사각형
		if (size == 1) {    
			if (r == input_r && c == input_c) {   // 주어진 r과 c와 같다면 
				return; 
			}
			cnt++;    // 주어진 r과 c와 같지 않다면 
			return;
		} 
		// 크기가 1이 아닐 때 
		else {  
			int half = size / 2;
			// 1사분면 
			if (input_r < r + half && input_c < c + half) {   
				dc(r, c, half);
			} 
			// 2사분면 
			else if (input_r < r + half && c + half <= input_c) {  
				cnt += half * half;
				dc(r, c + half, half);
			} 
			// 3사분면 
			else if (r + half <= input_r && input_c < c + half) {
				cnt += half * half * 2;
				dc(r + half, c, half);
			} 
			// 4사분면 
			else {
				cnt += half * half * 3;
				dc(r + half, c + half, half);
			}
		}


	}

}











