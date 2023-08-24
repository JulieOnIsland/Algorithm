package live.algorithm.basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/** 
 * D4 1251 하나로
 * @author leejuhyun
 * 
 * 모든 섬들을 잇는 "최소" 환경 부담금 구하기.
 * Minimum Spanning Tree 문제
 * 그림에서 vertex 가 N개이면, edge가 N-1개로 MST에 대한 힌트를 주고 있다.
 * 최소 가중치를 구하는 건 Kruskal or Prim 알고리즘을 이용하면 된다
 * 
 * Memory: 112,220kb
 * Time: 840ms 
 * 
 */

public class D4_1251_하나로_이주현 {
	
	static class edge implements Comparable<edge>{
		int from, to;
		double weight; 

		public edge(int from, int to, double weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(edge o) {
			return Double.compare(this.weight, o.weight);
		}	

	}
	
	static int N;
	static int[] parents;
	static List<edge>[] graph;
	static int ans;
	static int[] selected;
	static List<int[]> combList;
	static edge[] edgeList;
		
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int test_case = Integer.parseInt(st.nextToken());
		for (int idx=1; idx <= test_case; idx++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());  // 섬의 개수 
			int[] xs = new int[N]; 
			int[] ys = new int[N]; 
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				xs[i] = Integer.parseInt(st.nextToken()); 
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				ys[i] = Integer.parseInt(st.nextToken()); 
			} // xs, ys 완성
			
			st = new StringTokenizer(br.readLine());
			
			double E = Double.parseDouble(st.nextToken()); 
			
			int e_num = N*(N-1)/2;  // 무향 그래프에서 필요한 간선의 개수
			edge[] edgeList = new edge[e_num];
			combList = new ArrayList<>();
			selected = new int[2];
			
			comb(0, 0); // 조합
			
			for (int i=0; i<e_num; i++) {
				int[] from_to = combList.get(i);
				int from = from_to[0];
				int to = from_to[1];
				double dist = Math.pow((xs[from]-xs[to]), 2) + Math.pow((ys[from]-ys[to]), 2);
				edgeList[i] = new edge(from, to, dist);
			}
			
			// edgelist 정렬 : weight를 기준으로.
			Arrays.sort(edgeList);
			
			// 최소 단위 서로소 집합 만들기
			makeSet();
			
			double result = 0;
			int count = 0; // 연결된 간선 개수
			for (edge Edge: edgeList) {
				if (union(Edge.from, Edge.to)) { // 합칠 수 있다면
					
					result += Edge.weight * E;
					if (++count == N-1) {
						break;
					}
				}
			}

			System.out.println("#"+idx+" "+(long)Math.round(result));
		}

	}
	
	private static void comb(int cnt, int start) { // 2개의 정점 뽑기 -> 조합
		if (cnt == 2) {
			combList.add(Arrays.copyOf(selected, 2));  // arrays copy해서 add (안 그러면 이상하게 됨)
			return;
		}
		
		for (int i=start; i<N; i++) {
			selected[cnt] = i;
			comb(cnt+1, i+1);
		}

	}
	
	// Kruskal ----------------------------------------------------
	
	private static void makeSet() {
		parents = new int[N];
		for (int i=0; i<N; i++) {
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
	
	//---------------------------------------------------------------

}

