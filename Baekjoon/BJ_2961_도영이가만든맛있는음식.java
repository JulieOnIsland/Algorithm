package com.practice.makes.perfect;

import java.util.Scanner;

/**
 * BJ 2961 도영이가만든맛있는음식
 * @author leejuhyun
 * 
 * 신맛은 곱을 해주고, 쓴맛은 합을 해준다. 신맛과 쓴맛의 차이가 가장 작을 때의 차이를 출력해야 한다.
 * n개의 데이터가 주어졌을 때 넣고, 넣지 않고를 판단하면서 가장 작을 때를 찾아야 한다. -> Power Set
 * 
 * Memory: 17636kb, Time: 208ms
 * 
 */

public class BJ_2961_도영이가만든맛있는음식 {

	static int n;
	static int diff;
	static int[] sour, bitter;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		diff = Integer.MAX_VALUE;
		sour = new int[n];
		bitter = new int[n];
		for (int i = 0; i < n; i++) {
			sour[i] = sc.nextInt();
			bitter[i] = sc.nextInt();
		}
		
		recur(0, 1, 0, 0);
		System.out.println(diff);

	}
	
	public static void recur(int cnt, int mul, int sum, int selectedCount) { 
		// cnt: 현재 처리해야 할 인덱스, mul: (누적)곱, sum: (누적)합, selectedCount: 선택된 재료의 개수 
		if (cnt == n) {
			if (selectedCount > 0 && diff > Math.abs(sum - mul)) { // 적어도 한 가지 재료는 선택해야 한다 
				diff = Math.abs(sum - mul);
			}
			return;
		}
		
		recur(cnt + 1, mul * sour[cnt], sum + bitter[cnt], selectedCount + 1);
		recur(cnt + 1, mul, sum, selectedCount);
		
	}

}







