package com.practice.makes.perfect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_2468_안전영역 {
	
	static int N, grid[][];
	static boolean visited[][];
	static int dr[] = {-1, 0, 1, 0};
	static int dc[] = {0, 1, 0, -1};
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		grid = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
			}
		}
//		print(grid); // original array
		
		// 그리드에서 가장 큰 값 구하기 
		int max = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (grid[i][j] > max) {
					max = grid[i][j];
				}
			}
		}
//		System.out.println("max: "+max);
		
		for (int k=0; k<=max; k++) {  // k는 1부터가 아니라 0부터!
			int[][] tempArray = arrayCopy(grid); // 원래 배열을 바꾸면 안 되므로 카피 
//			print(tempArray);
			
			visited = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (tempArray[i][j] <= k) {
						tempArray[i][j] = 0;
					}
				}
			}
//			print(grid);
			int cnt=max;
			int start = cnt;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visited[i][j] && tempArray[i][j] !=0) {
						// 방문한 적이 없고, 0이 아니라면 
						cnt++;
						bfs(tempArray, i, j, cnt);  // flood fill
					}
				}
			}
//			print(tempArray);
			
			// group count
			int group_cnt = cnt - start;
			if (group_cnt > ans) ans = group_cnt;
			
		}
		System.out.println(ans);	

	}
	
	private static void bfs(int[][] grid, int sr, int sc, int cnt) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {sr, sc});
		visited[sr][sc] = true;
		grid[sr][sc] = cnt;
		
		while (!q.isEmpty()) {
			int[] current = q.poll();
			
			for (int d=0; d<4; d++) {
				int nr = current[0] + dr[d];
				int nc = current[1] + dc[d];
				
				if (check(nr, nc) && !visited[nr][nc] && grid[nr][nc] !=0) {
					q.offer(new int[] {nr, nc});
					visited[nr][nc] = true;
					grid[nr][nc] = cnt;
				}
			}
		}
		

	}
	
	private static int[][] arrayCopy(int[][] grid) {
		int[][] newArray = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				newArray[i][j] = grid[i][j];
			}
		}
		return newArray;

	}
	
	private static boolean check(int r, int c) {
		return 0<=r && r<N && 0<=c && c<N;

	}
	
	private static void print(int[][] grid) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(grid[i][j]+" ");
			}
			System.out.println();
		}

	}

}
