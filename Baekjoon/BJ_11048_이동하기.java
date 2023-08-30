import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BJ 11048 이동하기
 * @author leejuhyun
 *
 * Memory: 73152kb
 * Time: 544ms
 */
public class BJ_11048_이동하기 {
	
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int grid[][] = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
			}
		}
//		print(grid);
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (check(i, j) && check(i-1, j-1) && check(i-1, j) && check(i, j-1)) {
					// 현재 좌표, left up 좌표, up 좌표, left 좌표가 범위에 들어간 다면
					int max = Math.max(grid[i-1][j-1], Math.max(grid[i-1][j], grid[i][j-1]));
					grid[i][j] = max + grid[i][j];
				}
				// 가장자리 체크
				else if (check(i-1, j)) { 
					grid[i][j] = grid[i-1][j] + grid[i][j];
				} else if (check(i, j-1)) { 
					grid[i][j] = grid[i][j-1] + grid[i][j];
				} 
			}
//			System.out.println();
//			print(grid);
		}
		System.out.println(grid[N-1][M-1]);

	}
	
	private static boolean check(int r, int c) {
		return 0<=r && r<N && 0<=c && c<M;
	}
	
	private static void print(int[][] grid) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(grid[i][j]+" ");
			}
			System.out.println();
		}
	}
	
}
