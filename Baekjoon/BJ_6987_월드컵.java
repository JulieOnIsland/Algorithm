package com.practice.makes.perfect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BJ 6987 월드컵
 * @author leejuhyun
 * 
 * DFS
 * 
 * Memory: 14372kb
 * Time: 124ms
 */

public class BJ_6987_월드컵 {

	static int w, l, d;
	static int[] team_1, team_2, win, lose, draw;
	static boolean isPossible;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int cnt = 0;
		// 첫번째 팀과 두번째 팀 만들기 (6C2 = 15)
		team_1 = new int[15];
		team_2 = new int[15];
		for (int i=0; i<6; i++) {
			for (int j=i+1; j<6; j++) {
				team_1[cnt] = i;
				team_2[cnt] = j;
				cnt++;
			}
		}
		
		for (int i=0; i<4; i++) {
			st = new StringTokenizer(br.readLine()); 
			win = new int[6];   // 5 3 2 0 4 1
			lose = new int[6];  // 0 0 0 0 0 0
			draw = new int[6];  // 0 2 3 5 1 4
			isPossible = false;
			w = 0; l = 0; d = 0;
			
			for (int j=0; j<6; j++) {
				w += win[j] = Integer.parseInt(st.nextToken());
				d += draw[j] = Integer.parseInt(st.nextToken());
				l += lose[j] = Integer.parseInt(st.nextToken());
			}
			
			if (w+d+l != 30) {
				isPossible = false;
			} else {
				dfs(0);
			}
			
			// Print out
			if (isPossible) {
				System.out.print(1+" ");
			} else {
				System.out.print(0+" ");
			}

		}

	}
	
	private static void dfs(int cnt) {
		if (cnt == 15) {
			isPossible = true;
			return;
		}
		
		int a = team_1[cnt];    // 경기 테이블에서 순서 A-B team_1[0], team_2[0]
		int b = team_2[cnt];
		
		// team_1이 이기고, team_2가 졌을 경우
		if (win[a]>0 && lose[b]>0) {
			win[a]--;
			lose[b]--;
			dfs(cnt+1);
			win[a]++;    // 원상복귀 (dfs 대칭적인 구조)
			lose[b]++;
		}
		
		// 비겼을 경우 
		if (draw[a]>0 && draw[b]>0) {
			draw[a]--;
			draw[b]--;
			dfs(cnt+1);
			draw[a]++;   // 원상복귀 
			draw[b]++;
		}
		
		// team_2가 이기고, team_1이 졌을 경우
		if (lose[a]>0 && win[b]>0) {
			lose[a]--;
			win[b]--;
			dfs(cnt+1);
			lose[a]++;
			win[b]++;
		}

	}

}
