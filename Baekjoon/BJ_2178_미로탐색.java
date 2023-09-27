package live.algorithm.basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BJ 2178 미로탐색
 * @author leejuhyun
 * 
 * BFS는 최단경로를 보장한다
 *
 * Memory: 14652kb
 * Time: 144ms
 */

public class BJ_2178_미로탐색 {
	
	static int n, m;
	static int maze[][];
	static boolean visited[][];
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		maze = new int[n][m];
		visited = new boolean[n][m];
		
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			char[] temp = st.nextToken().toCharArray();
			for (int j=0; j<temp.length; j++) {
				maze[i][j] = temp[j] - '0';
			}
		}
//		System.out.println(Arrays.deepToString(maze));
		
		ans = bfs();
		System.out.println(ans);
		
	}
	
	private static int bfs() {
		Queue<int[]> queue = new ArrayDeque<>();  // 큐에는 row, col, 이동 거리를 담는다
		int result = 0;
		
		// 탐색의 시작을 (1, 1)로 하자.
		queue.offer(new int[] {0, 0, 1});
		visited[0][0] = true;
		
		while (!queue.isEmpty()) {
			int[] current = queue.poll();  // 큐에서 하나 빼기
			int curr_row = current[0];
			int curr_col = current[1];
			result = current[2];
			
			if (curr_row == n-1 && curr_col == m-1) { // (n-1, m-1)에 도착하면 리턴
				return result;
			}
			
			// 위, 오른쪽, 아래, 왼쪽을 체크하면서 1이면 큐에 넣기
			for (int d=0; d<4; d++) {
				int nr = curr_row+dr[d];  // next row
				int nc = curr_col+dc[d];  // next col
				if (check(nr, nc) && !visited[nr][nc] && maze[nr][nc] == 1) {
					// 범위 안에 있고, 방문하지 않았으면서, 1이라면
					queue.offer(new int[] {nr, nc, result+1});  // 큐에 넣기 // 실수한 지점: result+1 vs. result++
					visited[nr][nc] = true;
				}
			}
		}
		return result;

	}
	
	private static boolean check(int r, int c) {
		return 0 <= r && r < n && 0 <= c && c < m; 

	}
}

