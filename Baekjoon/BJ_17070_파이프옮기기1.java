import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BJ_17070_파이프옮기기1
 * 
 * Memory: 14256kb
 * Time: 128ms
 *
 */

public class BJ_17070_파이프옮기기1 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int[][] grid = new int[N][N];
		for (int i = 0; i < grid.length; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < grid.length; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][][] count = new int[N][N][3];  // 3차원 배열, 0: 가로, 1: 세로, 2: 대각선 
		// 0~n행까지, 1~n열까지 반복
		for (int i = 0; i < N; i++) {
			for (int j = 1; j < count.length; j++) {
				// 0, 1 번째 가로에서 출발. 초기값 갱신
				if (i==0 && j==1) count[i][j][0] = 1;
				
				// 가로
				if (j+1<N && grid[i][j+1]==0) {
					count[i][j+1][0] += count[i][j][0] + count[i][j][2];
				}
				
				// 세로
				if (i+1<N && grid[i+1][j]==0) {
					count[i+1][j][1] += count[i][j][1] + count[i][j][2];
				}
				
				// 대각선
				if (i+1<N && j+1<N && grid[i][j+1]==0 && grid[i+1][j]==0 && grid[i+1][j+1]==0) {
					count[i+1][j+1][2] += count[i][j][0] + count[i][j][1] + count[i][j][2];
				}
			}
		}

		System.out.println((count[N-1][N-1][0] + count[N-1][N-1][1] + count[N-1][N-1][2]));

	}

}
