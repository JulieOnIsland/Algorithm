package live.algorithm.basic;

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
 * BJ 17471 게리맨더링
 * @author leejuhyun
 *
 * 부분집합 + 그래프 연결 확인 
 * 
 * Memory: 16748kb
 * Time: 140ms
 */
public class BJ_17471_게리맨더링 {
	
	static int ans;
	static int N, pop[];
	static List<Integer>[] graph;
	static boolean selectedA[], selectedB[];
	static List<Integer> grpA, grpB;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 구역의 개수 
		pop = new int[N+1]; // 인구 수 담는 배열 
		st = new StringTokenizer(br.readLine());
		for (int i=1; i<=N; i++) {
			pop[i] = Integer.parseInt(st.nextToken());
		}
		
		// Make an adjacent list
		graph = new ArrayList[N+1];
		for (int i=1; i<=N; i++) {
			graph[i] = new ArrayList<Integer>();
		}
		
		for (int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			for (int j=0; j<num; j++) {
				int temp = Integer.parseInt(st.nextToken());
				graph[i].add(temp);
			}
		}
		
//		printGraph();
		selectedA = new boolean[N+1];
		ans = Integer.MAX_VALUE;
		subset(1);
//		System.out.println(ans);
		if (ans == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(ans);
		}

	}
	
	private static void subset(int cnt) {
		if (cnt == N+1) { // 부분집합이 만들어짐 
			
//			System.out.println("A: "+Arrays.toString(selectedA));
			// selectedA = [false, true, true, false, false, true, false] -> group A 
			selectedB = new boolean[N+1];
			int false_cnt = 0; 
			for (int i=1; i<=N; i++) {
				if (!selectedA[i]) { // selectedA에서 false이면, A의 선거구에 포함되지 않으므로 
					selectedB[i] = true;   // B의 선거구에 포함시킨다 
					false_cnt++;
				}
			}
//			System.out.println("B: "+Arrays.toString(selectedB));
			
			if (isConnected(selectedA) && isConnected(selectedB)) {
				int popA = computePopulation(selectedA);
				int popB = computePopulation(selectedB);
				int diff = Math.abs(popA - popB);
				if (diff < ans) {
					ans = diff;
				}
			}
			
//			if (false_cnt == N || false_cnt == 0) { // A의 선거구에 포함되지 않거나 모두 A 선거구에 포함되었다면, 리턴 
//				return;
//			} else {
//				if (isConnected(selectedA) && isConnected(selectedAB)) {
//					int popA = computePopulation(selectedA);
//					int popB = computePopulation(selectedAB);
//					int diff = Math.abs(popA - popB);
//					if (diff < ans) {
//						ans = diff;
//					}
//				}
//			}
			
			return;
		}
		
		// 부분집합 만들기 
		selectedA[cnt] = true;
		subset(cnt + 1);
		selectedA[cnt] = false;
		subset(cnt + 1);

	}
	
	private static boolean isConnected(boolean[] se) {
		
		boolean[] visited = new boolean[N+1];
		Queue<Integer> q = new ArrayDeque<Integer>();
		
		// 처음 정점 찾기 
		int start = -1;
		for (int i=1; i<=N; i++) {
			if (se[i]) {
				start = i;
				q.offer(start);
				break;
			}
		}
		
		if (start == -1) return false;
				
		while (!q.isEmpty()) {
			int current = q.poll();
			visited[current] = true;
			
			for (int next: graph[current]) { // current 정점의 연결된 정점 탐색 
				if (se[next] && !visited[next]) {
					// 선거구에 포함되어야 할 정점이면서 아직 방문하지 않은 정점이라면 
					q.offer(next);
					visited[next] = true;
				}
			}
		}
		
		// 선거구에 포함되어야 하는 정점들이 방문되었는지 확인 
		for (int i=1; i<=N; i++) {
			if (se[i] && !visited[i]) {
				// 선거구에 포함되어야 하는데 방문하지 않았다면 
				return false;
			}
		}
		return true;

	}
	
	private static int computePopulation(boolean[] se) {
		int totalPop = 0;
		for (int i=1; i<=N; i++) {
			if (se[i]) {
				totalPop += pop[i];
			}
		}
		return totalPop;
		
	}

}
