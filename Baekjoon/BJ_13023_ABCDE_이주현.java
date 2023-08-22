package live.algorithm.basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * BJ 13023 ABCDE
 * @author leejuhyun
 * 
 * DFS & backtracking
 * 
 * Memory: 16296kb
 * Time: 312ms
 */

public class BJ_13023_ABCDE_이주현 {
	
	static int N, M;
	static boolean visited[];
//	static int adjMatrix[][];
	static List<Integer>[] graph; 
	static boolean flag;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());  // 사람의 수 
		M = Integer.parseInt(st.nextToken());  // 친구 관계의 수
		
		visited = new boolean[N]; 
		
//		adjMatrix = new int[N][N];
		graph = new List[N];
		for (int i=0; i<N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		// Make a adjacent list
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			graph[from].add(to);
			graph[to].add(from);
		}

		// for문 돌려서 찾기
		
		for (int i=0; i<N; i++) {
			if (graph[i].size() > 0) {
				dfs(i, 1);
				if (flag) break;
			}
		}
		
		if (flag) {
			System.out.println(1);
		} else {
			System.out.println(0);
		}

	}
	
	private static void dfs(int current, int cnt) {
		if (cnt >= 5) {
			flag = true;  // backtracking
			return;  // 기저조건 
		}
		
		visited[current] = true;
		
		for (int i=0; i<graph[current].size(); i++) {
			if (flag == true) {
				return;
			}
			int next = graph[current].get(i);
			if (!visited[next]) {
				dfs(next, cnt+1);
				
			}
		}

		visited[current] = false;

	}

}
