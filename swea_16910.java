package algorithms;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * @author leejuhyun
 * SWEA no.16910
 * Count the number of lattice points in a circle with radius N 
 * 
 * memory:93,988kb
 * execution time:386ms
 * (better than Python in terms of memory and execution time) 
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

/* Better code by Minjeong Kang
 * memory: 23,108kb
 * execution time: 220ms
import java.util.Scanner;
 
class Solution
{
    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int T;              // 테스트 케이스 수
        T=sc.nextInt();
 
        for(int test_case = 1; test_case <= T; test_case++)
        {
            int n = sc.nextInt();
            int powN = (int) Math.pow(n, 2);
            int answer = 0;
            for(int x = -n; x <= n; x++){
                for(int y = -n; y <= n; y++){
                    if(Math.pow(x, 2) + Math.pow(y, 2) <= powN) {
                        answer += 1;
                    }
                }
            }
            System.out.println(String.format("#%d %d", test_case, answer));
        }
    }
}
 */



