package live.algorithm.basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * D4 3124 최소스패닝트리
 * @author leejuhyun
 *
 * MST는 Kruskal or Prim Algorithm으로 풀 수 있다.
 * 
 * Memory: 113,392kb
 * Time: 2148ms
 */

public class D4_3124_최소스패닝트리 {
	
	static class Edge implements Comparable<Edge>{
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
		
	}
	
	static int V, E;
	static Edge[] EdgeList;
	static int[] parents;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		for (int idx = 1; idx <= T; idx++) {
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());  // 정점의 개수
			E = Integer.parseInt(st.nextToken());  // 간선의 개수
			
			EdgeList = new Edge[E];
			// 간선리스트 만들기
			for (int i=0; i<E; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				EdgeList[i] = new Edge(from, to, weight);
			}
			
			// 간선리스트 정렬
			Arrays.sort(EdgeList);
			
			// 서로소 집합 만들기
			makeSet();
			
			long totalWeight = 0;
			int count = 0; // 연결된 간선의 수: V-1개가 되어야 함
			
			// MST 만들기
			for (Edge edge: EdgeList){
				if (union(edge.from, edge.to)) {
					// 사이클이 발생하지 않았으므로 가중치 누적
					totalWeight += edge.weight;
					if (++count == V-1) break;  // 간선의 수가 V-1개가 되면, STOP
				}
			}
			
			System.out.println("#"+idx+" "+totalWeight);
		}
		
		

	}
	
	private static void makeSet() {
		parents = new int[V+1];
		for (int i=1; i<=V; i++) {
			parents[i] = i;
		}

	}
	
	private static int findParent(int a) {
		if (parents[a] == a) return a;
		return parents[a] = findParent(parents[a]);
	}
	
	private static boolean union(int a, int b) {
		int aRoot = findParent(a);
		int bRoot = findParent(b);
		if (aRoot == bRoot) return false;
		parents[bRoot] = aRoot;
		return true;

	}

}
