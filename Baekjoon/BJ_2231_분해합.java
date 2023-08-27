package com.practice.makes.perfect;

import java.util.Arrays;
import java.util.Scanner;

/**
 * BJ 2231 분해합
 * 어떤 자연수 N이 있을 때, 그 자연수 N의 분해합은 N과 N을 이루는 각 자리 수의 합을 의미한다. 
 * 256 (=245+2+4+5) -> 245는 256의 생성자 
 * N의 가장 작은 생성자 찾기 
 * 
 * Memory: 18300kb
 * Time: 3208ms
 */

public class BJ_2231_분해합 {
	
	static int n, selected[];
	static int numArray[];
	static boolean flag;
	static int ans;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
//		int N = sc.nextInt(); // 1<=N<=1,000,000
		String N = sc.next();  // 자릿수를 구할 수 있음.
		n = Integer.parseInt(N);  // 문자로 바꾼 N을 다시 정수로 
		flag = false;
		
		numArray = new int[10];
		for (int i=0; i<=9; i++) {
			numArray[i] = i;
		}
		
		if (N.length()==1) {  // 한자리 수 일 때는 따로 처리 
			if (n%2==0) {
				System.out.println(n/2);
			} else {
				System.out.println(0);
			}
			
		} else { 
			for (int i=2; i<=N.length(); i++) {
				selected = new int[i];
				perm(0, i);
			}
			
			if (flag) {
				System.out.println(ans);
			} else {
				System.out.println(0);
			}
		}
	}
	
	// 중복 순열 
	private static void perm(int cnt, int R) { 
		if (cnt == R) {
			int sum = 0;
			int temp_ans = 0;
//			System.out.println(Arrays.toString(selected));
			for (int j=0; j<selected.length; j++) {
				sum += selected[j];
				sum += (int)(Math.pow(10, R-j-1)) * selected[j];
				temp_ans += (int)(Math.pow(10, R-j-1)) * selected[j];
			}
//			System.out.println("sum: "+sum);
//			System.out.println("temp answer: "+temp_ans);
			
			if (n == sum) {
				ans = temp_ans;
				flag = true;
			} 
			
			return;
		}
		
		if (!flag) {  // flag가 false일 때만 중복 순열 구하기 
			for (int i=0; i<numArray.length;i++) {
				selected[cnt] = numArray[i];
				perm(cnt+1, R);
			}
		}
	}
}
