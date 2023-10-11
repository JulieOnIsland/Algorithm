import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BJ 1600 말이되고픈원숭이
 * @author leejuhyun
 * 
 * 말은 장애물을 뛰어넘을 수 있는데 총 K번만 말처럼 움직일 수 있다.
 * 그 외에는 인접한 칸으로만 움직일 수 있다(4방).
 * 원숭이가 최소한의 동작으로 시작지점에서 도착지점까지 갈 수 있는 방법. 최소 움직인 횟수 구하기.
 * 
 * visited[H][W][2] 이렇게 만들었는데(4방으로 움직이거나 나이트처럼 움직이거나) 이렇게 하면 안 되고 K를 기준으로 만들어야 한다.
 * 
 * Memory: 61,240kb
 * Time: 560ms
 *
 */
public class BJ_1600_말이되고픈원숭이 {
	
	static int K, W, H, grid[][], answer;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static int[] drh = {-1, -2, -2, -1, 1, 2, 2, 1}; // horse
	static int[] dch = {-2, -1, 1, 2, -2, -1, 1, 2};
	static boolean[][][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		grid = new int[H][W];
		visited = new boolean[H][W][K+1]; // 3차원 방문 배열. row, col, 말이 움직인 횟수
		answer = Integer.MAX_VALUE;
		
		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
			}
		}
//		System.out.println(Arrays.deepToString(grid));
		move();
		System.out.println(answer);
	}
	
	private static void move() {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {0, 0, 0, K}); // 시작row, 시작col, 이동횟수, K의 갯수
		visited[0][0][K] = true;  // K의 갯수
		
		while (!q.isEmpty()) {
			int[] current = q.poll();
			int cr = current[0]; // current row
			int cc = current[1]; // current col
			int num = current[2]; // 이동 횟수
			int kNum = current[3]; // k의 갯수
			
			if (cr == H-1 && cc == W-1) { // 맨 오른쪽 아래, 즉 끝점에 도달했다면
				if (answer > num) answer = num; // 최솟값 갱신
			}
			
			for (int d = 0; d < 4; d++) { // 4방 탐색
				int nr = cr + dr[d]; // next row
				int nc = cc + dc[d]; // next col
				
				if (check(nr, nc) && !visited[nr][nc][kNum] && grid[nr][nc] != 1) { // 범위 안에 들어가고 방문하지 않았고 장애물이 아니라면
					q.offer(new int[] {nr, nc, num+1, kNum});
					visited[nr][nc][kNum] = true;
				}
			}
			
			if (kNum > 0) { // K가 1개 이상일 때만 나이트처럼 이동할 수 있음
				for (int d = 0; d < 8; d++) { // 나이트처럼 이동
					int nr = cr + drh[d]; // next row
					int nc = cc + dch[d]; // next col
					
					if (check(nr, nc) && !visited[nr][nc][kNum-1] && grid[nr][nc] != 1) {  // 오답 부분 !visited[nr][nc][kNum]라고 함
						q.offer(new int[] {nr, nc, num+1, kNum-1});
						visited[nr][nc][kNum-1] = true; // 오답 부분 
					}
				}
			}
		}
		// bfs 탐색을 돌았는데도 answer가 갱신되지 않았다면, 도달할 수 없다는 것.
		if (answer == Integer.MAX_VALUE) answer = -1;

	}
	
	private static boolean check(int r, int c) {
		return 0<=r && r<H && 0<=c && c<W;

	}
}
