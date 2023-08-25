package live.algorithm.basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * BJ 1753 최단경로
 * @author leejuhyun
 * 방향 그래프가 주어지면 시작점에서 다른 모든 정점으로의 최단 경로 구하기 
 * 다익스트라
 * 
 * Memory: 107840kb
 * Time: 2244ms
 */
public class BJ_1753_최단경로 {
	
	static List<int[]>[] graph;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		
		final int INF = Integer.MAX_VALUE;
		int[] distance = new int[V+1];
		boolean[] visited = new boolean[V+1];
		
		// 거리 배열 초기화
		Arrays.fill(distance, INF);
		distance[start] = 0;
//		System.out.println(Arrays.toString(distance));
		
		// 인접 리스트 만들기
		graph = new ArrayList[V+1];
		for (int i = 1; i <= V; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			graph[from].add(new int[] {to, weight});
		}
		
		// 인접리스트 프린트
//		for (int i=1; i<=V; i++) {
//			if (graph[i].size()>0) {
//				System.out.println("graph: "+i);
//				for (int[] element: graph[i]) {
//					System.out.print(Arrays.toString(element)+" ");
//				}
//			}
//			System.out.println();
//		}
		
		// 다익스트라 
		int min = 0;
		int stopOver = 0;  // 경유지 변수
		
		for (int i = 1; i <= V; i++) {
			
			// 미방문 정점 중에 출발지에서 가장 가까운 정점을 선택
			stopOver = -1;
			min = INF;
			for (int j = 1; j <= V; j++) {
				if (!visited[j] && min > distance[j]) {
					min = distance[j];
					stopOver = j;
				}
			}
			if (stopOver == -1) break;
			
			// 방문처리
			visited[stopOver] = true;
			
			// 경유지를 이용하여 미방문 정점들의 출발지에서 자신으로의 최소 비용 고려 
			for (int[] next: graph[stopOver]) {
				if (!visited[next[0]] && distance[next[0]] > min+next[1]) {
					distance[next[0]] = min+next[1];
				}
			}
		}
		
		// 결과값 프린트
		for (int j = 1; j < distance.length; j++) {
			if (distance[j] == Integer.MAX_VALUE) {
				System.out.println("INF");
			} else {
				System.out.println(distance[j]);
			}
		
		}

	}

}