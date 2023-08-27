package com.practice.makes.perfect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 규칙: 
 * 1. 돌의 아랫칸이 벽으로 막혀있거나 가장 아랫줄이라면, 돌은 그대로 멈춰라 
 * 2. 돌의 아랫칸이 비어있다면, 돌은 아랫칸으로 이동 
 * 3. 돌의 아랫칸에 돌이 있다면, 돌은 다음과 같이 미끄러진다 
 * (1) 만약 돌의 왼쪽 칸과 돌의 왼쪽-아래 칸이 비어있다면, 돌은 왼쪽으로 미끄러진다 
 * (2) 만약 돌이 왼쪽으로 미끄러지지 않았고, 오른쪽 칸과 오른쪽-아래 칸이 비어있다면, 돌은 오른쪽으로 미끄러진다 
 * (3) 위의 두 경우가 아니면, 돌은 그 자리에 멈추고, 다시 움직이지 않는다 
 */

public class BJ_3025_돌던지기_시간초과 {
	
	static int R, C;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		char[][] grid = new char[R][C];
		
		for (int i=0; i<R; i++) {
			 st = new StringTokenizer(br.readLine());
			 char[] temp = st.nextToken().toCharArray();
			 for (int j = 0; j < C; j++) {
				grid[i][j] = temp[j]; // .는 빈칸, X는 벽으로 막힌 곳 
			}
		}
		
//		print(grid);
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());   // 돌을 던진 횟수 
		int[] cmd = new int[N];  // 돌을 던질 열의 위치 
		
		for (int i = 0; i < cmd.length; i++) {
			st = new StringTokenizer(br.readLine());
			cmd[i] = Integer.parseInt(st.nextToken())-1;
		}
		
		for (int i=0; i<cmd.length;i++) {
			int rr = 0;  // rock row
			int rc = cmd[i];  // rock col
			
			while (rr<R) { // 몇 번 진행될지 특정할 수 없으므로 while문 
				int nr = ++rr;
			
				if ((check(nr, rc) && grid[nr][rc]=='X')) {
					grid[nr-1][rc] = 'O';
					break;
				} else if (nr == R) {
					grid[nr-1][rc] = 'O';
					break;
					
				} else if (check(nr, rc) && grid[nr][rc]=='.') {
					continue;
				} 
				else if (check(nr, rc) && grid[nr][rc]=='O') {
					if (check(nr-1,rc-1) && check(nr,rc-1) && grid[nr-1][rc-1]=='.' && grid[nr][rc-1]=='.') {
						// 왼쪽과 왼쪽-아래가 범위 안에 들어 있으면서 비어있다면  
						rc-=1;
					} else if (check(nr-1,rc+1) && check(nr,rc+1) && grid[nr-1][rc+1]=='.' && grid[nr][rc+1]=='.') {
						// 오른쪽과 오른쪽-아래가 범위 안에 들어 있으면서 비어있다면 
						rc+=1;
					} else {
						grid[nr-1][rc] = 'O';
						break;
					}
					
				}
				
			}
		
		}
		
		print(grid);

	}
	
	private static boolean check(int r, int c) {
		return 0<=r && r<R && 0<=c && c<C;

	}
	
	private static void print(char[][] grid) {
		for (int i=0; i<R; i++) {
			 for (int j = 0; j < C; j++) {
				System.out.print(grid[i][j]);
			}
			 System.out.println();
		}

	}

}
