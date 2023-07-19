package algorithms;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * @author leejuhyun
 * SWEA no.16910
 * Count the number of lattice points in a circle with radius N 
 * 
 * memory:
 * execution time: 
 */
public class swea_16910 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for (int idx=1; idx<T+1; idx++) {
			int N = sc.nextInt(); // radius
			int count = 0;
			
			ArrayList<int[]> total_grid = new ArrayList<>();
			
			for (int i=-N; i<=N; i++) {
				for (int j=-N; j<=N; j++) {
					total_grid.add(new int[]{i,j});
				}
			}
			
			for (int i=0; i<total_grid.size(); i++) {
				int[] grid = total_grid.get(i);
				if (grid[0]*grid[0]+grid[1]*grid[1]<=N*N) {
					count++;
				}
			}
			System.out.printf("#%d %d", idx, count);
			System.out.println();
		}

	}

}
