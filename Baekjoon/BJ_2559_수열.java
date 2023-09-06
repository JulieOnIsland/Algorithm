package com.practice.makes.perfect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BJ 2559 수열
 * 
 * Memory: 23720kb
 * Time: 272ms
 */

public class BJ_2559_수열 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] nums = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < nums.length; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		// 초기화
		int ans = 0;
		int total = 0;
		for (int i = 0; i < K; i++) {  // 0번째 인덱스부터 K개의 합을 구하고, 이것을 초기값으로 설정 
			total += nums[i];
		} 
		ans = total;
		
		for (int i = K; i < N; i++) { // K번째 인덱스부터 출발, N-1번째 인덱스까지. 
			total = total + nums[i] - nums[i-K]; // 그 다음 숫자를 더하고, 맨 앞 부분을 하나씩 빼서 
			ans = Math.max(ans, total); // 연속된 숫자들의 합 중 최댓값만 취한다 
		}

		System.out.println(ans);
	} 

}

