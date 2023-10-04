import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BJ 2146 다리만들기
 * @author leejuhyun
 * 두 대륙을 연결하는 가장 짧은 다리의 길이 구하기 (BFS 2번)
 * Step 1. flood fill 알고리즘으로 섬을 표시하기
 * Step 2. 0과 붙어 있는 좌표 중에서 다른 섬과 연결된 가장 짧은 길이 구하기
 * 
 * Memory: 79012kb
 * Time: 248ms
 */

public class BJ_2146_다리만들기 {
	
	static int N, grid[][], length;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static boolean visited[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		grid = new int[N][N];
		for (int i = 0; i < grid.length; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < grid.length; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
			}
		}
//		System.out.println(Arrays.deepToString(grid));
		visited = new boolean[N][N];
		
		// floodfill 알고리즘 이용해서 인접한 대륙으로 나누기
		int cnt = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (grid[i][j] == 1 && !visited[i][j]) { // 1이고, 방문한 적이 없다면
					floodfill(i, j, cnt++);
				}
			}
		}
		
//		print();
		
		// 짧은 다리 길이 찾기
		int num = cnt - 1; // 섬의 개수
		length = Integer.MAX_VALUE;
		// 0이 아닌 번호가 붙은 섬 중에 하나, 그런데 0과 하나라도 겹치는 부분이 있어야 함. 
		// 그런 섬 중에서 그 숫자가 아닌 다른 섬들로의 최단 거리 구하기 
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (grid[i][j] != 0 && isAvailable(i, j)) { // 0이 아니고, 길이를 구할 수 있는 점의 좌표라면
					visited = new boolean[N][N];
					bfsLength(i, j, grid[i][j]);
				}
			}
		}
		
		System.out.println(length);

	}
	
	private static void bfsLength(int r, int c, int value) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {r, c, 0});
		visited[r][c] = true; 
		
		while (!q.isEmpty()) {
			int[] current = q.poll();
			int cr = current[0];
			int cc = current[1];
			int len = current[2];
			for (int d = 0; d < 4; d++) {
				int nr = cr + dr[d];
				int nc = cc + dc[d]; 
				if (check(nr, nc) && grid[nr][nc] != value && !visited[nr][nc]) { 
					// 범위 안에 들어가고, value가 아니면서, 한 번도 방문한 적이 없다면
					if (grid[nr][nc] == 0) { // 다리이면
						q.offer(new int[] {nr, nc, len+1}); // 큐에 넣고
						visited[nr][nc] = true; // 방문처리
					} else { // 다른 섬이라면
						if (length > len) length = len;
						return;
					}
			
				}
			}
		}

	}
	
	private static boolean isAvailable(int r, int c) { // 길이를 구할 수 있는가
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if (check(nr, nc) && grid[nr][nc] == 0) { // 범위 안에 들어가면서 0과 하나라도 겹치는 부분이 있다면
				return true;
			}
		}
		return false;

	}
	
	private static void floodfill(int r, int c, int fill) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {r, c});
		visited[r][c] = true;
		grid[r][c] = fill;
		
		while (!q.isEmpty()) {
			int[] current = q.poll();
			int cr = current[0]; // current row
			int cc = current[1]; // current col
			for (int d = 0; d < 4; d++) {
				int nr = cr + dr[d]; // next row
				int nc = cc + dc[d]; // next col
				if (check(nr, nc) && grid[nr][nc]==1 && !visited[nr][nc]) { // 범위에 들어가고, 1이면서 방문하지 않았다면			
					q.offer(new int[] {nr, nc}); // 큐에 넣고
					visited[nr][nc] = true; // 방문처리
					grid[nr][nc] = fill; // 값 바꾸기
				}
			}
		}

	}
	
	private static boolean check(int r, int c) {
		return 0<=r && r<N && 0<=c && c<N;

	}
	
	private static void print() {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length; j++) {
				System.out.print(grid[i][j]+" ");
			}
			System.out.println();
		}

	}

}



















