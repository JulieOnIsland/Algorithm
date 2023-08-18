package live.algorithm.basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BJ 1987 알파벳
 * @author leejuhyun
 * 
 * DFS
 *
 * Memory: 15248kb
 * Time: 840ms
 */

public class BJ_1987_알파벳 {
	
	static int R, C;
	static char grid[][];
	static boolean visited[];
	static int visitedIdx = 0; 
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static int result = 0;
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		grid = new char[R][C];
		visited = new boolean[26];
		
		for (int i=0; i<R; i++) {
			st = new StringTokenizer(br.readLine());
			char[] temp = st.nextToken().toCharArray();
			for (int j=0; j<C; j++) {
				grid[i][j] = temp[j];
			}
		}
		
//		System.out.println(Arrays.deepToString(grid));
		
		visited[grid[0][0]-'A'] = true;   // 가장 첫번째 알파벳 방문처리
		dfs(1, 0, 0);
		
//		System.out.println(Arrays.toString(visited));
		System.out.println(result);

	}
	
	private static void dfs(int cnt, int r, int c) {

		// cnt: 현재 이동 칸 수
		if (cnt > result) {
			result = cnt;
		}
		
		for (int d=0; d<4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if (check(nr, nc)) {
				if (!visited[grid[nr][nc]-'A']) { // 방문처리한다
					visited[grid[nr][nc]-'A'] = true;
					dfs(cnt + 1, nr, nc);
					visited[grid[nr][nc]-'A'] = false;
				}
			}
		}

	}
	
	private static boolean check(int rr, int cc) {
		return 0<=rr && rr<R && 0<=cc && cc<C;

	}

}
