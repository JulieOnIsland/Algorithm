import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * BJ 17144 미세먼지안녕
 * @author leejuhyun
 * 
 * 1초 동안 다음과 같은 일이 순서대로 일어난다.
 * 1. 미세먼지 확산: 미세먼지가 있는 모든 칸에서 "동시에" 일어난다.
 * 2. 공기 청정기 작동: 회전
 * 
 * MEMORY: 301832kb
 * TIME: 768ms
 * 
 */
public class BJ_17144_미세먼지안녕 {
	
	static int R, C, grid[][], MR;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken()); // T초
		
		MR = 0; // machine row
		grid = new int[R][C];
		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				grid[r][c] = Integer.parseInt(st.nextToken());
				if (grid[r][c] == -1) MR = r;
			}
		}
//		System.out.println(Arrays.deepToString(grid));
		
		for (int t = 0; t < T; t++) {
			// 미세먼지의 확산과 공기청정이 매초마다 이루어진다.
			moveDust(); 
//			System.out.println("미세먼지 확산된 후: ");
//			print();
//			System.out.println("-----------------------------");
			rotate();
//			System.out.println("공기청정기 회전: ");
//			print();
		}
		
		// 미세 먼지의 양 출력
		int answer = 0;
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (grid[r][c] != 0 && grid[r][c] != -1) {
					answer += grid[r][c];
				}
			}
		}
		System.out.println(answer);
		
	}
	
	private static void moveDust() {
		// 원래 미세먼지가 담긴 배열: list
		List<int[]> list = new ArrayList<>(); // 미세먼지의 좌표(row,col)와 미세먼지의 양
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (grid[r][c] != 0 && grid[r][c] != -1) {
					list.add(new int[] {r, c, grid[r][c]});
				}
			}
		}
		
		// 확산될 미세먼지가 담긴 배열 : movingList
		List<int[]> movingList = new ArrayList<>(); 
		
		for (int[] e: list) {
			int r = e[0];
			int c = e[1];
			int amount = e[2];
			int dirCnt = 0;  // 확산되는 방향의 개수
			for (int d = 0; d < 4; d++) {
				int nr = r+dr[d]; // next row
				int nc = c+dc[d]; // next col
				if (check(nr, nc)) { // 범위에 들어가면
					// 범위에 들어가도 공기 청정기를 만났다면 방향의 개수가 커지면 안 됨. 공기청정기 좌표가 아닐 때에만 dirCnt 증가
					if (grid[nr][nc]!=-1) {
						dirCnt++;
						movingList.add(new int[] {nr, nc, amount/5}); // 확산될 미세먼지가 담긴 배열에 추가
					}
					
				} 
			}
			// 4방향을 보는 걸 끝냈다면, 그 자리에 남은 미세먼지 계산해주기.
			grid[r][c] -= (amount/5)*dirCnt;
		}

		// 원래 미세먼지가 담긴 배열 처리가 끝났다면, 확산될 먼지를 처리해주자.
		for (int[] e:movingList) {
			int r = e[0];
			int c = e[1];
			int addedAmount = e[2];
			grid[r][c] += addedAmount;
		}

	}
	
	private static void rotate() {
		// 공기 청정기 row 좌표의 시작 : MR-1, 끝: MR
		
		int[] drc = {-1, 0, 1, 0}; // direction of row clockwise 
		int[] dcc = {0, 1, 0, -1}; // direction of cow clockwise 
		
		int dir = 0;
		int cr = MR-2, cc = 0; // current row, current col
		
		while (true) {
			int nr = cr + drc[dir];
			int nc = cc + dcc[dir];
			
			if (0<=nr && nr<MR && 0<=nc && nc<C) { // 범위 안에 들어간다면
				if (grid[nr][nc] == -1) { // 그 다음 좌표가 공기청정기 좌표라면
					grid[cr][cc] = 0;
					break;
				}
				grid[cr][cc] = grid[nr][nc];
				cr = nr;
				cc = nc;
			
			} else {
				dir++;
			}
			
			if (nr == MR-1 && nc == 0) break;
		}
		
		// -------------------------------------------------------------------
		
		int[] drcc = {1, 0, -1, 0}; // direction of row counter-clockwise
		int[] dccc = {0, 1, 0, -1}; // direction of cow counter-clockwise
		
		dir = 0;
		cr = MR+1; cc = 0;
		while (true) {
			int nr = cr + drcc[dir];
			int nc = cc + dccc[dir];
			
//			if (nr == MR && nc == 0) break;
			
			if (MR<=nr && nr<R && 0<=nc && nc<C) { // 범위 안에 들어간다면
				if (grid[nr][nc] == -1) { // 그 다음 좌표가 공기청정기 좌표라면
					grid[cr][cc] = 0;
					break;
				}
				grid[cr][cc] = grid[nr][nc];
				cr = nr;
				cc = nc;
			
			} else {
				dir++;
			}
			
			if (nr == MR && nc == 0) break;
		}
		
	}
	
	private static boolean check(int r, int c) {
		return 0<=r && r<R && 0<=c && c<C;

	}
	
	private static void print() {
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				System.out.print(grid[r][c]+" ");
			}
			System.out.println();
		}
	}

}

