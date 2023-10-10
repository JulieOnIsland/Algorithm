import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 
 * @author leejuhyun
 * 맨 위에 있는 벽돌이 깨지는데 벽돌의 숫자에 의해 연쇄적으로 영향을 준다 (벽돌 숫자-1만큼 사방으로 제거). 
 * N개의 구슬을 떨어트려 "최대한" 많은 벽돌을 제거하려고 한다 -> 남은 벽돌의 "최소" 개수 구하기.
 * => 최대한 많은 벽돌을 제거하도록 구슬을 N번 어느 위치에 투하할까? 1. 위치 결정 : (중복이 가능하고, 순서가 의미가 있으므로) 중복순열 
 * 2. 투하한 구슬이 제거하게 될 맨 윗 벽돌 찾기
 * 3. 벽돌 제거하기 (배열 복사)
 * 	3-1. 인접 벽돌 찾기: Flood Fill (DFS or BFS)로 인접 벽돌 마킹
 *  3-2. 제거하고 내리기
 * 
 * 구현
 * 
 * Memory: 38,708kb
 * Time: 240ms
 */

public class SWEA_5656_벽돌깨기 {

	static class Point{
		int r,c,cnt; // 벽돌의 위치, 크기
		public Point(int r, int c, int cnt) {
			super();
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	static int N,W,H,min;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(in.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			N = Integer.parseInt(st.nextToken());// 구슬 던지는 횟수
			W = Integer.parseInt(st.nextToken());// 가로(열크기)
			H = Integer.parseInt(st.nextToken());// 세로(행크기)
			int[][] map = new int[H][W];
			
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			} // 2차원 map 입력
			
			min = Integer.MAX_VALUE;
			drop(0,map);
			System.out.println("#"+tc+" "+min);
		}
	}

	// 구슬 던지기: 중복 순열
	private static boolean drop(int cnt,int[][] map) { // 구슬 떨어뜨리기  cnt : 직전까지 떨어뜨린 구슬 수
																	//map : 직전상태까지의 map
		// 구슬을 던지기 전에 현 상태로 남은 벽돌수 체크
		int result = getRemain(map);
		// 남은 벽돌 수가 0이면 모든 벽돌이 제거된 가장 최적의 상태이므로 최소값 0으로 갱신후 return true
		if(result == 0) {
			min = 0;
			return true;
		}
		// 모든 구슬을 다 던졌다면 남은 벽돌수로 최소값 갱신 후 return false
		if(cnt == N) {
			if(min>result) min = result;
			return false;
		}
		
		int[][] newMap = new int[H][W]; 
		for (int c = 0; c < W; c++) { // 모든 열에 대해 시도
			
			// 해당 열에 떨어뜨릴 경우 제거되게 되는 맨 윗벽돌 찾기
			int r=0;
			while(r<H && map[r][c]==0) ++r;
			// 벽돌이 존재하지 않는다면(해당 열은 모두 빈칸) 다음 열로 건너뛰기
			if(r==H) continue;
			
			// 벽돌이 존재한다면 기존 상태에서 복사
				copy(map, newMap);
				// 함께 제거될 인접벽돌 연쇄 찾기
				// 디버깅 출력
				boom(newMap,r,c);
				// 제거처리(벽돌 내리기)
				// 디버깅 출력
//				down(newMap);
				downStack(newMap);
				// 디버깅 출력
				// 다음 구슬 던지러 가기 : 재귀 호출 ==> 재귀호출의 결과가 true이면 가장 최적해의 상황이므로 return true
				if(drop(cnt+1, newMap)) return true;
		}
		return false;
	}
	

	// 인접한 제거 벽돌 찾기 : Flood Fill(4방 BFS)
	private static void boom(int[][] map, int r, int c) {
		Queue<Point> queue = new ArrayDeque<Point>();
		
		if(map[r][c]>1) queue.offer(new Point(r, c, map[r][c]));
		map[r][c] = 0; // 방문 처리(벽돌 제거 표시)
		
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			
			for (int d = 0; d < 4; d++) {
				int nr = cur.r;
				int nc = cur.c;
				for (int i = 1; i < cur.cnt; i++) {// cnt-1만큼 주변 벽돌 찾기
					nr += dr[d];
					nc += dc[d];
					if(nr>=0 && nr<H && nc>=0 && nc<W && map[nr][nc]>0) {
						if(map[nr][nc]>1) queue.offer(new Point(nr, nc, map[nr][nc]));
						map[nr][nc] = 0; // 방문 처리
					}
				}
			}
		}
	}

	// 벽돌 내리기1 : 빈자리 위쪽 벽돌 찾아 내리기 
	private static void down(int[][] map) {
		// 매 열 기준으로 내리기
		for (int c = 0; c < W; c++) {
			int r = H-1, nr = -1;
			while(r>0) {
				if(map[r][c] == 0) { // 빈칸이면 내릴 벽돌 찾기
					nr = r-1; // 바로 윗행부터 찾기
					
					while(nr>0 && map[nr][c] == 0) --nr; 
					
					map[r][c] = map[nr][c];
					map[nr][c] = 0; // 빈칸 처리 
				}
				
				if(nr == 0) break;
				--r;
			}
		}		
	}
	
	// 벽돌 내리기2 : 매 열마다 맨 윗행부터  벽돌칸 모두 스택에 넣고 빈칸 만들기 
	private static void downStack(int[][] map) {
		Stack<Integer> stack = new Stack<>();
		for (int c = 0; c < W; c++) {
			for (int r = 0; r < H; r++) {
				if (map[r][c] != 0) {
					stack.push(map[r][c]); // 스택에 넣고
					map[r][c] = 0; // 그 자리는 0으로 바꾸기
				}
			}
			
			int pointer = H-1; // 가장 아래 행부터 시작
			while (!stack.isEmpty()) {
				int value = stack.pop();
				map[pointer][c] = value;
				pointer--;
			}
		}
	}

	
	// 배열 복사하기
	private static void copy(int[][] map, int[][] newMap) {
		for (int r = 0; r < H; r++) {
			for (int c = 0; c < W; c++) {
				newMap[r][c] = map[r][c];
			}
		}
	}
	
	// 남은 벽돌 개수 구하기 : 매번 구슬 던지기 전에 사용할 목적 
	private static int getRemain(int[][] map) {
		int cnt = 0;
		for (int r = 0; r < H; r++) {
			for (int c = 0; c < W; c++) {
				if(map[r][c] != 0) cnt++;
			}
		}
		return cnt;
	}
	
	// 디버깅용 : 상태 출력
}