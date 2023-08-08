package com.practice.makes.perfect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SWEA 1952 [모의 SW 역량테스트] 수영장 
 * @author leejuhyun
 * 
 * 1일, 1달, 3달, 1년의 비용이 주어졌을 때 가장 적은 비용 찾기 
 * 깊이우선탐색(DFS)를 이용해 접근하였다.
 * 
 * Memory: 19636kb
 * Time: 117ms
 */


public class swea_1952_수영장 {
	
	static int minCost;
	static int priceDay, priceMonth, price3Month, priceYear;
	static int[] plan;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int test_case = Integer.parseInt(st.nextToken());
		
		for (int idx = 1; idx <= test_case; idx++) {
			
			// Input
			st = new StringTokenizer(br.readLine());
			priceDay = Integer.parseInt(st.nextToken());
			priceMonth = Integer.parseInt(st.nextToken());
			price3Month = Integer.parseInt(st.nextToken());
			priceYear = Integer.parseInt(st.nextToken());
			
			plan = new int[14];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 12; i++) {
				plan[i] = Integer.parseInt(st.nextToken());;
			}
			
			minCost = priceYear;
			
			dfs(0, 0);    // Find the minimum cost
			
			sb.append("#"+idx+" "+minCost+"\n");
		}
		
		System.out.println(sb.toString());

	}
	
	public static void dfs(int month, int cost) {
		if (month > 11) {
			minCost = Math.min(minCost, cost);
			return;
		}
		
		// 3 month
		dfs(month + 3, cost + price3Month);
		
		// 1 month
		dfs(month + 1, cost + priceMonth);
		
		// 1 day
		dfs(month + 1, cost + priceDay * plan[month]);

	}

}
