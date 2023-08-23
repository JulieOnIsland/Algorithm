package live.algorithm.basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BJ 1012 유기농배추
 * @author leejuhyun
 * 
 * Flood Fill
 * BFS
 * 
 * Memory: 15884kb
 * Time: 164ms
 */

public class BJ_1012_유기농배추 {
	
	static int M, N, grid[][];
	static boolean[][] visited;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		for (int idx=1; idx<=T; idx++) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());  // 배추밭의 가로길이
			N = Integer.parseInt(st.nextToken());  // 배추밭의 세로길이
			int K = Integer.parseInt(st.nextToken());  // 배추의 개수
			
			grid = new int[M][N];
			visited = new boolean[M][N];
			for (int i=0; i<K; i++) {
				st = new StringTokenizer(br.readLine());
				int temp_row = Integer.parseInt(st.nextToken());
				int temp_col = Integer.parseInt(st.nextToken());
				grid[temp_row][temp_col] = 1;
			}
			
//			System.out.println(Arrays.deepToString(grid));
			
			int num = 1;
			
			for (int i=0; i<M; i++) {
				for (int j=0; j<N; j++) {
					if (grid[i][j] == 1 && !visited[i][j]) {
						num++;
						bfs(i, j, num);
					}
				}
			}
			
//			System.out.println(Arrays.deepToString(grid));
			
			System.out.println(num-1);
		}
		
	}
	
	private static void bfs(int r, int c, int mark) {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {r, c});
		visited[r][c] = true;
		grid[r][c] = mark;
		
		while (!queue.isEmpty()) {
			int[] current = queue.poll();
			
			for (int d=0; d<4; d++) {  // 4방 탐색
				int nr = current[0] + dr[d];
				int nc = current[1] + dc[d];
				
				if (check(nr, nc) && !visited[nr][nc] && grid[nr][nc] == 1) {
					// 범위에 들어가고, 방문하지 않았고, 0이 아닌 1이라면
					grid[nr][nc] = mark;  
					queue.offer(new int[] {nr, nc}); 
					visited[nr][nc] = true;
					
				}
			}
		}

	}
	
	private static boolean check(int r, int c) {
		return 0<=r && r<M && 0<=c && c<N;

	}
	
	

}









