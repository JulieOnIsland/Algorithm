package com.practice.makes.perfect;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * BJ 2667 단지번호붙이기
 * @author leejuhyun
 * 
 * Flood Fill 문제는 DFS, BFS 둘 다 풀 수 있다 
 * Queue & BFS
 */

public class BJ_2667_단지번호붙이기 {

	static int N;
	static int[][] map;
	static int [] dr= {-1,0,1, 0};
	static int [] dc= { 0,1,0,-1};     // clockwise direction
	
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		map=new int[N][N];
		
		// Make a map
		for (int i = 0; i < N; i++) {
			String s=br.readLine();
			char[] cs=s.toCharArray();
			for (int j = 0; j < N; j++) {
				map[i][j]=cs[j]-'0';
			}
		}
		
		int cnt=0; // 몇 그룹인지 카운트하는 변수 
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j]==1) {       // 연결된 1들을 찾자. 
					cnt++;               // group의 개수 
					bfs(i, j, cnt+1);    // 숫자 2부터 시작하겠어. 
				}
			}
		}
		
		// 2, 3, 4그룹에 속하는 단지 카운트 
		int [] res=new int[cnt];
		for (int k = 2; k < 2+cnt; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(map[i][j]==k) {
						res[k-2]++;
					}
				}
			}
		}
		
		System.out.println(cnt);
		Arrays.sort(res);
		for (int a :res) {
			System.out.println(a);
		}

	}
	
	public static void bfs(int r, int c, int group) {
		Queue<int[]> queue = new LinkedList<int[]>();
		queue.offer(new int[] {r,c});        // add 보다는 offer 권장 
		map[r][c] = group;      // 시작점 
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			int cr = cur[0];
			int cc = cur[1];
			for (int d = 0; d < 4; d++) {
				int nr = cr + dr[d];
				int nc = cc + dc[d];
				if(!check(nr,nc)) continue;      // 범위 체크 
				if(map[nr][nc] == 1) {
					queue.offer(new int[] {nr,nc});
					map[nr][nc] = group;
				}
			}
		}
	}
	
	public static boolean check(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < N;
	}

}
