package com.practice.makes.perfect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BJ_1260_DFS와BFS
 * @author leejuhyun
 * 
 * 간선이 양방향 -> Undirected Graph
 * 그래프를 인접 행렬로 표현. 
 * 
 * Memory: 24904kb
 * Time: 388ms
 */

public class BJ_1260_DFS와BFS { // 인접 행렬 

	static int N, M, V;
	static int adjMatrix[][];
	static boolean bfs_visited[], dfs_visited[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		
		adjMatrix = new int[N][N];
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			adjMatrix[from][to] = adjMatrix[to][from] = 1;
		}
		
//		System.out.println(Arrays.deepToString(adjMatrix));
		
		dfs_visited = new boolean[N];
		bfs_visited = new boolean[N];
		
		dfs_visited[V-1] = true;  // dfs 시작 정점 방문처리 
		System.out.print(V+" ");
		dfs(0, V-1);
		System.out.println();
		bfs();

	}
	
	private static void dfs(int cnt, int start) {
		if (cnt == N) return; 
		
		for (int col=0; col<N; col++) {
			if (adjMatrix[start][col] == 1 && !dfs_visited[col]) {
				dfs_visited[col] = true;
				System.out.print(col + 1+" ");
				dfs(cnt + 1, col);
			}
		}

	}
	
	private static void bfs() {
		Queue<Integer> queue = new ArrayDeque<>();
		
		// 첫 시작 정점을 큐에 넣는다  
		queue.offer(V - 1);
		// 방문처리 
		bfs_visited[V-1] = true;
		
		while (!queue.isEmpty()) {
			int current = queue.poll();   // 큐에서 꺼낸다 
			System.out.print(current + 1+" ");
			
			for (int col=0; col<N; col++) {
				if (adjMatrix[current][col] == 1 && !bfs_visited[col]) {
					// 인접해있으면서 방문하지 않았다면 큐에 넣는다
					queue.offer(col);
					bfs_visited[col] = true;
				}
			}
		}

	}

}





