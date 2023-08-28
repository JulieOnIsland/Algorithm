package live.algorithm.basic;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

/**
 * BJ 1697 숨바꼭질
 * @author leejuhyun
 *
 * BFS
 */

public class BJ_1697_숨바꼭질 {

	static int N, K, ans;
	static boolean visited[];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		visited = new boolean[100001];
		bfs(N, 0);
		System.out.println(ans);
	}
	
	
	private static void bfs(int x, int cnt) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {x, cnt});
		visited[x] = true;
		
		while (!q.isEmpty()) {
			int[] current = q.poll();
			int temp_cnt = current[1];
			if (current[0] == K) {
				ans = current[1];
				break;
			}
			
			// 순간이동 
			int temp = current[0] * 2;
			if (0<= temp && temp <= 100000 && !visited[temp]) {
				q.offer(new int[] {temp, temp_cnt+1});
				visited[temp] = true;
			}
			// +1
			temp = current[0]+1;
			if (0<= temp && temp <= 100000 && !visited[temp]) {
				q.offer(new int[] {temp, temp_cnt+1});
				visited[temp] = true;
			}
			// -1
			temp = current[0]-1;
			if (0<= temp && temp <= 100000 && !visited[temp]) {
				q.offer(new int[] {temp, temp_cnt+1});
				visited[temp] = true;
			}
		}
	}
}
