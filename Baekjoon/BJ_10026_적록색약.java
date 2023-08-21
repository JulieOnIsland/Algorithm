package live.algorithm.basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BJ 10026 적록색약
 * @author leejuhyun
 * Flood Fill
 * 
 * Memory: 15024kb
 * Time: 160ms
 */

public class BJ_10026_적록색약 {
	
	static int N;
	static char grid[][];
	static boolean visited[][];
	static int normal_ans, abnormal_ans;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		grid = new char[N][N];
		visited = new boolean[N][N];
		// Make a grid
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			char[] temp = st.nextToken().toCharArray();
			for (int j=0; j<N; j++) {
				grid[i][j] = temp[j];
			}
		}
		
//		System.out.println(Arrays.deepToString(grid));
		
		// 정상인 사람이 봤을 때의 구역 개수
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if (!visited[i][j]) { // 방문한 적이 없으면
					normal(i, j);
					normal_ans++;
				}
			}
		}
		System.out.print(normal_ans+" ");
		
		// 적록색약이 아닌 사람이 봤을 때의 구역 개수 
		visited = new boolean[N][N];  // 새로운 방문 배열 만들기 
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if (!visited[i][j]) { // 방문한 적이 없으면
					abnormal(i, j);
					abnormal_ans++;
				}
			}
		}

		System.out.print(abnormal_ans);
		
		
	}
	
	private static void normal(int r, int c) {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {r, c});
		visited[r][c] = true;
		
		while (!queue.isEmpty()) {
			int[] current = queue.poll();
			int cr = current[0];
			int cc = current[1];
			int color = grid[cr][cc];
			
			for (int d=0; d<4; d++) {
				int nr = cr + dr[d];
				int nc = cc + dc[d];
				if (check(nr, nc) && !visited[nr][nc] && grid[nr][nc] == color) {
					// 범위 안에 들어가고, 방문을 한 적이 없고, 컬러가 같으면
					queue.offer(new int[] {nr, nc});
					visited[nr][nc] = true;
				}
			}
			
		}
		
	}
	
	private static void abnormal(int r, int c) {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {r, c});
		visited[r][c] = true;
		
		while (!queue.isEmpty()) {
			int[] current = queue.poll();
			int cr = current[0];
			int cc = current[1];
			char color = grid[cr][cc];
			int colorINT = 0;  // R와 G이면 0으로, B이면 1로
			if (color == 'B')  colorINT = 1;
			
			if (colorINT == 1) { // 색이 파란색일 때
				for (int d=0; d<4; d++) {
					int nr = cr + dr[d];
					int nc = cc + dc[d];
					if (check(nr, nc) && !visited[nr][nc] && grid[nr][nc] == color) {
						queue.offer(new int[] {nr, nc});
						visited[nr][nc] = true;
					}
				}
			} else { // 색이 빨간색이거나 초록색일 때
				for (int d=0; d<4; d++) {
					int nr = cr + dr[d];
					int nc = cc + dc[d];
					if (check(nr, nc) && !visited[nr][nc]) {
						if (grid[nr][nc] == 'R' || grid[nr][nc] == 'G') {
							// 범위 안에 들어가고, 방문을 한 적이 없고, 색이 빨간색이거나 초록색이면
							queue.offer(new int[] {nr, nc});
							visited[nr][nc] = true;
						}
						
					}
				}
			}

		}

	}
	
	private static boolean check(int r, int c) {
		return 0<=r && r<N && 0<=c && c<N;

	}

}













