import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BJ 4485 녹색옷입은애가젤다지
 * @author leejuhyun
 * 문제: 잃는 금액을 최소로 하여 동굴 건너편까지 이동하는데 링크가 잃을 수 있는 최소 금액 구하기.
 * visited 배열을 boolean이 아닌 int형으로 저장하는 게 새로웠음.
 * BFS, DFS, DP 중에 고민을 했음.
 * 
 * Memory: 275192kb
 * Time: 780ms
 */
public class BJ_4485_녹색옷입은애가젤다지 {
	
	static int N, grid[][], answer;
	static int visited[][];
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int idx = 1; 
		
		while (true) { 
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			if (N == 0) break; // 종료
			grid = new int[N][N];
			visited = new int[N][N];
			//visited 배열 만들기
			for (int i = 0; i < grid.length; i++) {
				for (int j = 0; j < grid.length; j++) {
					visited[i][j] = 1000;
				}
			}
			// grid 배열 만들기
			answer = Integer.MAX_VALUE;
			for (int i = 0; i < grid.length; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < grid.length; j++) {
					grid[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			bfs();
			System.out.println("Problem"+" "+idx+": "+visited[N-1][N-1]);
			idx++;
			
		}

	}
	
	private static void bfs() {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {0, 0, grid[0][0]}); // row, col, value
		visited[0][0] = grid[0][0];
		
		while(!q.isEmpty()) {
			int[] current = q.poll();
			int cr = current[0]; // current row
			int cc = current[1]; // current col
			int value = current[2];
			
			for (int d = 0; d < 4; d++) {
				int nr = cr + dr[d];
				int nc = cc + dc[d];
				
				if (check(nr, nc)) { // 범위에 들어가고
					if (visited[nr][nc] > value+grid[nr][nc]) { // visited 배열에서보다 value+grid[nr][nc] 값이 작으면
						visited[nr][nc] = value+grid[nr][nc]; // 최솟값 갱신
						q.offer(new int[] {nr, nc, value+grid[nr][nc]}); // 큐에 넣기
					}

				}
				
			}
		
		}
		
	}
	
	private static boolean check(int r, int c) {
		return 0<=r && r<N && 0<=c && c<N;
	}

}
