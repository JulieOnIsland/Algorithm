package com.practice.makes.perfect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * BJ 11729 하노이 탑 이동 순서 
 * @author leejuhyun
 * 
 * Memory: 128084KB, Time: 468ms
 * 
 * 재귀: "Flat"하게 봐야 한다! 
 * N개의 원판을 A (시작 기둥)에서 C (목적 기둥)으로 옮겨야 한다.
 * 1. 제일 큰 원판 위에 있는 N-1개의 원판을 임시 기둥으로 이동 
 * 2. 시작 기둥의 N번 원판을 목적 기둥으로 이동 
 * 3. 임시 기둥에 있는 N-1개의 원판을 목적 기둥으로 이동 
 */

public class BJ_11729_하노이탑이동순서 {

	static int totalMovingCnt;
	static List<Integer> movingList;
	static int n;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		movingList = new ArrayList<>();
		
		hanoi(n, 1, 2, 3);
		
		printResults();
	}
	
	public static void hanoi(int N, int source, int spare, int dest) {
		// 가변적으로 변하는 값을 매개변수로 설정 
		// N: 이동시켜야 하는 원판의 수
		if (N == 0) {
			totalMovingCnt++;		
			return;
		}
		
		hanoi(N-1, source, dest, spare);		
		movingList.add(source); movingList.add(dest); 
		hanoi(N-1, spare, source, dest);
		
	}
	
	
	public static void printResults() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(totalMovingCnt-1).append("\n");
		
		for (int i = 0; i < movingList.size(); i++) {
			if (i != 0 && i % 2 == 0) {
				sb.append("\n");
			}
			sb.append(movingList.get(i)).append(" ");
		}
		
		System.out.println(sb.toString());
	}
}
