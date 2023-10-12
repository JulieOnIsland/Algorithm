import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BJ 2566 최댓값
 * @author leejuhyun
 * Memory: 16040kb, Time: 152ms
 */

public class BJ_2566_최댓값 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[][] grid = new int[10][10];
		int max = -1;
		int r = 0; 
		int c = 0;
		for (int i = 1; i < grid.length; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j < grid.length; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
				if (grid[i][j] > max) {
					max = grid[i][j];
					r = i;
					c = j;
				}
			}
		}
//		System.out.println(Arrays.deepToString(grid));
		System.out.println(max);
		System.out.println(r+" "+c);

	}

}
