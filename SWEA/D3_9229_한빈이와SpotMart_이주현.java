package com.practice.makes.perfect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * SWEA D3 9229 한빈이와 Spot Mart
 * @author leejuhyun
 * 
 * M 이라는 제한 조건 하에서 최대 무게를 구해야 하는데
 * 오직 2개를 고를 수 있으므로 완전탐색 중 조합으로 접근하였다.
 * 
 * Memory: 25,140kb
 * Time: 169ms
 */


public class D3_9229_한빈이와SpotMart_이주현 {

	static int N, M, totalWeight;
	static int[] weightArray, selectedArray;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int test_case = Integer.parseInt(st.nextToken());
		
		for (int idx = 1; idx <= test_case; idx++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			totalWeight = -1;
			
			weightArray = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				weightArray[i] = Integer.parseInt(st.nextToken());
			}
			selectedArray = new int[2];
			
			comb(0, 0);
			
			sb.append("#"+idx+" "+totalWeight+"\n");
		}
		
		System.out.println(sb.toString());
	}
	
	private static void comb(int cnt, int start) {
		int temp = 0;
		if (cnt == 2) {   // 기저 조건 
			for (int j = 0; j < selectedArray.length; j++) {
				temp += selectedArray[j];
			}
			
			if (temp > totalWeight && temp <= M) {
				totalWeight = temp;
			}
			return;
		}
		
		for (int i = start; i < weightArray.length; i++) {
			selectedArray[cnt] = weightArray[i];
			comb(cnt + 1, i + 1);
		}
	}
}










