package live.algorithm.basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * D3 1873 상호의배틀필드
 * @author leejuhyun
 *
 * Memory: 19,784kb
 * Time: 128ms
 */

public class D3_1873_상호의배틀필드 {

	static int h, w;
	static char[][] grid;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static int d;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int test_case = Integer.parseInt(st.nextToken());
		
		for (int idx = 1; idx <= test_case; idx++) {
			st = new StringTokenizer(br.readLine());
			h = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			
			grid = new char[h][w];
			
			// Make a grid
			for (int i=0; i<h; i++) {
				st = new StringTokenizer(br.readLine());
				char[] temp = st.nextToken().toCharArray();
				for (int j=0; j<w; j++) {
					grid[i][j] = temp[j];
				}
			}
			
			st = new StringTokenizer(br.readLine());
			int orderNum = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			char[] order = st.nextToken().toCharArray(); 
			
//			System.out.println(Arrays.deepToString(grid));
			
			int curr_r = 0; int curr_c = 0;
			int next_r = 0; int next_c = 0;
			// 전차의 초기 위치 찾기
			for (int i=0; i<h; i++) {
				for (int j=0; j<w; j++) {
					if (grid[i][j] == '<') {
						d = 3;
						curr_r = i; curr_c = j;     // 초기 위치 설정
						break;
					} else if (grid[i][j] == '>') {
						d = 1;
						curr_r = i; curr_c = j;
						break;
					} else if (grid[i][j] == '^') {
						d = 0;
						curr_r = i; curr_c = j;
						break;
					} else if (grid[i][j] == 'v') {
						d = 2;
						curr_r = i; curr_c = j;
						break;
					}
					
				}
			}
			
			int temp_r = 0; int temp_c = 0; int cr = 0; int cc = 0;
			// order 배열대로 맵 만들기
			for (int i=0; i<order.length; i++) {
				if (order[i] == 'S') {   // 폭탄 발사
					cr = curr_r; cc = curr_c;
					for (int k=0; k<w; k++) {
						temp_r = cr + dr[d];
						temp_c = cc + dc[d];
						if (check(temp_r, temp_c)) {  // 범위 안에 있다면
							if (grid[temp_r][temp_c] == '#') {     // 강철 벽이라면 아무 일도 일어나지 않음 
								break;
							} else if (grid[temp_r][temp_c] == '*') {   // 벽돌 벽이라면 평지로 바꿔주기
								grid[temp_r][temp_c] = '.';
								break;
							}
						}
						
						cr = temp_r;
						cc = temp_c;
						
					}
					
				} else if (order[i] == 'U') {
					grid[curr_r][curr_c] = '^';
					d = 0;
					next_r = curr_r + dr[d];
					next_c = curr_c + dc[d];
					if (check(next_r, next_c) && grid[next_r][next_c] == '.') {  // 맵 안에 있고, 평지라면 위로 1칸 이동
						grid[next_r][next_c] = '^';
						grid[curr_r][curr_c] = '.';
						curr_r = next_r; 
						curr_c = next_c;
					}
				} else if (order[i] == 'D') {
					grid[curr_r][curr_c] = 'v';
					d = 2;
					next_r = curr_r + dr[d];
					next_c = curr_c + dc[d];
					if (check(next_r, next_c) && grid[next_r][next_c] == '.') {  // 맵 안에 있고, 평지라면 아래로 1칸 이동
						grid[next_r][next_c] = 'v';
						grid[curr_r][curr_c] = '.';
						curr_r = next_r; 
						curr_c = next_c;
					}
				} else if (order[i] == 'R') {
					grid[curr_r][curr_c] = '>'; 
					d = 1;
					next_r = curr_r + dr[d];
					next_c = curr_c + dc[d];
					if (check(next_r, next_c) && grid[next_r][next_c] == '.') {  // 맵 안에 있고, 평지라면 오른쪽으로 1칸 이동
						grid[next_r][next_c] = '>';
						grid[curr_r][curr_c] = '.';
						curr_r = next_r; 
						curr_c = next_c;
					}
				} else {
					grid[curr_r][curr_c] = '<';
					d = 3;
					next_r = curr_r + dr[d];
					next_c = curr_c + dc[d];
					if (check(next_r, next_c) && grid[next_r][next_c] == '.') {  // 맵 안에 있고, 평지라면 왼쪽으로 1칸 이동
						grid[next_r][next_c] = '<';
						grid[curr_r][curr_c] = '.';
						curr_r = next_r; 
						curr_c = next_c;
					}
				}
			} 
			
//			System.out.println(Arrays.deepToString(grid));
			sb.append("#"+idx+" ");
			// print out the final grid
			for (char[] element : grid) {
				for (char e: element) {
					sb.append(e);
				}
				sb.append("\n");
			}
		}
		
		// 여기에 최종 프린트
		System.out.println(sb.toString());
	}
	
	private static boolean check(int r, int c) {
		if (0 <= r && r < h && 0 <= c && c < w && grid[r][c] != '-') { // 범위에 들어가고 물이 아니라면
			return true;
		}
		return false;

	}

}
