package live.algorithm.basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BJ 7562 나이트의이동
 * @author leejuhyun
 *
 * 나이트가 "최소" 몇 번 만에 이동할 수 있는지 구해야 하므로
 * BFS로 해결!
 * 
 * Memory: 49968kb
 * Time: 308ms
 */

public class BJ_7562_나이트의이동 {
	
	static int I;
	static int sr, sc, fr, fc;
	static boolean visited[][];
	static int[] dr = {-1, -2, -2, -1, 1, 2, 2, 1};
	static int[] dc = {-2, -1, 1, 2, 2, 1, -1, -2};
	static int ans;
 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken()); 
		for (int idx=1; idx<=T; idx++) {
			st = new StringTokenizer(br.readLine());
			I = Integer.parseInt(st.nextToken());
			visited = new boolean[I][I];
			
			sr = 0; sc = 0;  // start row, start col
			fr = 0; fc = 0;  // final row, final col
		
			st = new StringTokenizer(br.readLine());
			sr = Integer.parseInt(st.nextToken());
			sc = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			fr = Integer.parseInt(st.nextToken());
			fc = Integer.parseInt(st.nextToken());
			
			bfs();
			
			System.out.println(ans);
		}

	}
	
	private static void bfs() {
		
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {sr, sc, 0});  // start row, start col을 큐에 넣어주고 
		visited[sr][sc] = true;  // 방문 처리
		
		while (!q.isEmpty()) {  // 큐가 비어있을 때까지 반복
			int[] current = q.poll(); 
			if (current[0] == fr && current[1] == fc) {  // 큐에서 꺼낸 좌표가 final row, final col과 같을 때
				ans = current[2];  // ans에 이동한 칸수를 저장 
				break;
			}
			
			for (int d=0; d<8; d++) {  // 나이트가 갈 수 있는 좌표 8방 탐색
				int nr = current[0] + dr[d];
				int nc = current[1] + dc[d];
				
				if (check(nr, nc) && !visited[nr][nc]) {
					// 범위 안에 들어있으면서 방문하지 않았으면 
					q.offer(new int[] {nr, nc, current[2]+1});  // 큐에 넣어주고
					// current[2]를 하지 않고, move라는 static 변수에 1 더해주니까 계속 답이 안 나옴.
					// move+1 하면 안 되고, 큐에서 꺼낸 current[2]에 1을 더해서 넣어줘야 함!
					visited[nr][nc] = true; // 방문 처리
				}
			}
			
		}

	}
	
	private static boolean check(int r, int c) {  // 범위에 들어가는지 체크하는 메소드
		return 0<=r && r<I && 0<=c && c<I;

	}

}
