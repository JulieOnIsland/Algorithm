package com.practice.makes.perfect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BJ 1926 그림
 * 그림의 개수와 가장 넓이가 클 때의 값 출력하기 
 * 
 * Flood Fill
 * 
 * Memory: 46508kb
 * Time: 408ms
 */

public class BJ_1926_그림 {
	
	static int n, m, grid[][];
	static boolean visited[][];
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static int cnt, area;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		grid = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
//		System.out.println(Arrays.deepToString(grid));
		
		visited = new boolean[n][m];
		cnt = 1;
		int start = cnt;
		area = 0; // 넓이 
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (!visited[i][j] && grid[i][j] == 1) {
					cnt++;
					bfs(i, j);  // flood fill
				}
			}
		}
		
//		print(grid);
		
		int pic_num = cnt - start; // 그림의 개수 
		
		System.out.println(pic_num);
		System.out.println(area);

	}

	private static void bfs(int sr, int sc) { // start row, start col
		int area_cnt = 0;
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {sr, sc});
		visited[sr][sc] = true;  // 방문 처리 
		grid[sr][sc] = cnt;  // grid 값 바꿔주기 
		area_cnt++;  
		
		while (!q.isEmpty()){
			int[] current = q.poll();
			
			for (int d=0; d<4; d++) {
				int nr = current[0] + dr[d];  // next row
				int nc = current[1] + dc[d];  // next col
				
				if (check(nr, nc) && !visited[nr][nc] && grid[nr][nc]==1) {
					// 범위에 들어가고, 방문한 적이 없으면서, 값이 1이라면 
					q.offer(new int[] {nr, nc});  // 큐에 넣어주고 
					visited[nr][nc] = true;  // 방문처리하고
					grid[nr][nc] = cnt;  // grid 값 바꿔주고 
					area_cnt++;  // area_cnt 값 1 증가 
				}
				
			}
			
		}
		
		if (area_cnt > area) area = area_cnt;  // 현재 구한 area가 멤버 변수 area보다 크다면, 업데이트 
		
	}
	
	private static boolean check(int r, int c) {  // 범위 체크하는 메서드 
		return 0<=r & r<n && 0<=c && c<m;

	}
	
	private static void print(int[][] grid) {  // 2차원 배열 프린트하는 메서드 
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				System.out.print(grid[i][j]+" ");
			}
			System.out.println();
		}

	}

}
