package com.practice.makes.perfect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BJ 2252 줄세우기
 * @author leejuhyun
 * Topological Sorting
 * "A가 B의 앞에 서야 한다" -> 선후 관계가 있음
 * 위상 정렬의 구현은 DFS보다는 BFS가 easier
 * 인접 행렬은 희소 그래프일 때 공간 낭비가 심하다 -> 메모리 초과 
 * 
 * Memory: 48256kb
 * Time: 960ms
 */

public class BJ_2252_줄세우기 {
	
	static int N, M;
	static int[] tsort;
	static List<Integer> list;
	static int cnt;
//	static int[][] adjMatrix;  // 유향 그래프, 인접 행렬
	static List<Integer>[] graph;  // 인접 리스트 
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 학생의 수 
		M = Integer.parseInt(st.nextToken()); // 키를 비교한 횟수
		
		tsort = new int[N+1];
		list = new ArrayList<>();
		
		graph = new List[N+1];
		for (int i=0; i<=N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			graph[from].add(to);
			tsort[to]++;
		}
		
		bfs();

		
		// print out
		for (int element: list) {
			System.out.print(element+" ");
		}

	}
	
	private static void bfs() {
		Queue<Integer> queue = new ArrayDeque<>();
		
		// 0인 걸 큐에 다 넣어준다 (제약 조건이 없는 학생들)
		for (int i=1; i<=N; i++) {
			if (tsort[i] == 0) {
				queue.offer(i);     // 큐에 넣어주기	
			}
		}
		
		while (!queue.isEmpty()) {
			int current = queue.poll();  // 큐에서 하나씩 꺼낸다
			list.add(current); // 큐에서 꺼낸 걸 list에 넣어준다
			for (int j=0; j<graph[current].size(); j++) {
				int temp = graph[current].get(j);
				if (tsort[temp] == 1) {
					queue.offer(temp);
				} else {
					tsort[temp]--;
				}
			}
		
		}
		

	}

}