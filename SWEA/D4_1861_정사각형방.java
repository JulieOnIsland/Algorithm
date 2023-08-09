package com.practice.makes.perfect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * @author leejuhyun
 * 
 * 어떤 방에서 출발을 하는데 상하좌우에 있는 방이 지금의 방보다 "정확히" 1 크다면,
 * 다른 방으로 이동을 할 수 있다.
 * 가장 많은 개수의 방을 이동하는 방 번호와 최대 이동 수를 구하는 문제이다.
 * 그런데 최대 이동 수가 같다면 작은 방을 출력해야 한다.
 * => 큐와 BFS로 활용해 문제를 해결하였다. 
 * 
 * 생각해볼 것: visited 배열이 있으면 더 효율적이지 않을까? 
 * 
 * Memory: 91036kb
 * Time: 545ms
 */

public class D4_1861_정사각형방 {
	
	static int[][] grid;
	static Queue<int[]> queue;  // {row, col}
	static int roomNum, ans;
	static int[] row = {0, 1, 0, -1};
	static int[] col = {1, 0, -1, 0};

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int test_case = Integer.parseInt(st.nextToken());
		
		for (int idx = 1; idx <= test_case; idx++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			grid = new int[N][N];

			// Make a grid
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					grid[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// print grid ---------------------
//			for (int[] element: grid) {
//				for (int e: element) {
//					System.out.print(e+" ");
//				}
//				System.out.println();
//			}
			// --------------------------------
			
			queue = new ArrayDeque<>();
			
			// 최대값인 ans가 나왔을 때 방 번호를 저장해야 한다. 
			// 기존의 ans와 같은 값이 나왔을 때 방 번호를 비교한 다음, 작은 방 번호를 저장해야 한다.
			int tempAns = 0;
			int tempRoomNum = N*N;
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					roomNum = grid[r][c];
					queue.offer(new int[] {r, c});
					bfs();
					if (ans > tempAns) { // tempAns보다 더 큰 ans 값이 나왔다면 
						tempAns = ans;   // 갱신 
						tempRoomNum = roomNum;   // 방 번호 저장 
					} else if (ans == tempAns) {   // tempAns와 같은 ans 값이 나왔다면 
						if (roomNum < tempRoomNum) {   // 방 번호 비교해서 더 작은 방 번호 저장 
							tempRoomNum = roomNum;
						}
					}
					ans = 0;
				}
			}
			sb.append("#"+idx+" "+tempRoomNum+" "+tempAns+"\n");
		}
		System.out.println(sb.toString());
	
	}
	
	
	private static void bfs() {

		if (queue.isEmpty()) return;
				
		while (!queue.isEmpty()) {
			int[] current = queue.poll();
			
			ans++;
			
			// 상하좌우를 확인해서 정확히 1 차이 나면 큐에 넣기
			for (int i = 0; i < 4; i++) {
				if (0 <= current[0]+row[i] && current[0]+row[i] <= grid.length - 1 && 
						0 <= current[1]+col[i] && current[1]+col[i] <= grid.length - 1) { // 범위 체크 
					int temp = grid[current[0]+row[i]][current[1]+col[i]] - grid[current[0]][current[1]];
					if (temp == 1) {
						queue.offer(new int[] {current[0]+row[i], current[1]+col[i]});
					}
				} 
				
			}
			
		}
		
	}

}
