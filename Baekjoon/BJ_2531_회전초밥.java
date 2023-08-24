package com.practice.makes.perfect;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

/**
 * BJ_2531_회전초밥
 *  
 */

public class BJ_2531_회전초밥 {
	
	// 접시의 수, 초밥의 종류(1<= 초밥 번호 <=d), 연속해서 먹는 접시의 수, 쿠폰 번호 
	static int N, d, k, c;
	static int temp_max, ans;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		d = sc.nextInt();
		k = sc.nextInt();
		c = sc.nextInt();
		
		int[] sushi = new int[N];
		for (int i=0; i<N; i++) {
			sushi[i] = sc.nextInt();
		}
//		System.out.println(Arrays.toString(sushi));

		int[] ate = new int[d+1];
		
		// 0번부터 k개의 초밥을 먹었을 때의 초기화 
		for (int i = 0; i < k; i++) {
			if (ate[sushi[i]] == 0) {  // 처음 먹는 스시(종류)라면 
				temp_max++;  // temp_max 1 증가 
			}
			// 처음 먹는 스시가 아니라면 
			ate[sushi[i]]++;  // 먹은 개수 1 증가 
		}
		
		ans = temp_max;
		
		// 1번부터 n-1까지 start를 이동시키기  
		for (int i=1; i<N; i++) { // i = start
			
			if (ans <= temp_max) {
				if (ate[c] == 0) {  // 아직 쿠폰초밥을 먹지 못했다면 
					ans = temp_max+1;  // 쿠폰초밥을 먹었다고 가정하고 1 증가 
				} else {
					ans = temp_max;
				}
			}
			
			// end 이동 
			int end = (i+k-1) % N;
			if (ate[sushi[end]] == 0) {
				temp_max++;
			}
			ate[sushi[end]]++;
			
			// start 이동 
			ate[sushi[i-1]]--;
			if (ate[sushi[i-1]] == 0) {
				temp_max--;
			}
			
		}
		
		System.out.println(ans);
		
	}

}

// code reference: https://maivve.tistory.com/311
