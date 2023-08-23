package com.practice.makes.perfect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * D4 1238 Contact
 * 
 * 그래프 탐색. BFS로 해결. 
 * 
 * Memory: 19656kb
 * Time: 114ms
 */

public class D4_1238_Contact {
// 가장 나중에 연락을 받게 되는 사람 중 번호가 가장 큰 사람을 구하라. 
	
	static int start;
	static List<Integer>[] graph;
	static Queue<Integer> queue;
	static boolean visited[];
	static List<Integer> last;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		for (int idx=1; idx<=10; idx++) {
			st = new StringTokenizer(br.readLine());
			int data_num = Integer.parseInt(st.nextToken());
			start = Integer.parseInt(st.nextToken());
			
			// 그래프를 인접 리스트로 표현. 
			graph = new ArrayList[101]; // 1 ~ 100 -> 101개 
			for (int i=0; i<=100; i++) {
				graph[i] = new ArrayList<>();
			}
			visited = new boolean[101];
			
			// 데이터 받기. 
			st = new StringTokenizer(br.readLine());
			for (int i=0; i<data_num/2; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				graph[from].add(to);
			}
			
			bfs();
			int max = 0;
			for(int element: last) {
				if (element > max) max = element;
			}
			sb.append("#"+idx+" "+max+"\n");
		}
		System.out.println(sb.toString());
	
	}
	
	private static void bfs() {
		queue = new ArrayDeque<>();
		queue.offer(start);     // 시작점 큐에 넣기 
		visited[start] = true;  // 시작점 방문 
		
		while (!queue.isEmpty()) {
			int size = queue.size();
//			System.out.println("size: "+size);
			last = new ArrayList<>();
			for (int i=0; i<size; i++) {  // breadth level을 체크할 수 있음 
				int current = queue.poll();
//				System.out.println("current: "+current);
				last.add(current);
				
				for (int vt: graph[current]) {  // 시작점과 연결된 정점 찾기 
					if (!visited[vt]) {  // 방문하지 않았다면 
						queue.offer(vt); // 큐에 넣기 
						visited[vt] = true; // 방문 처리 
					}
				}
			}
			
		}

	}

}
