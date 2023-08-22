package live.algorithm.basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * D4 3289 서로소집합
 * @author leejuhyun
 *
 * Memory: 101,472kb
 * Time: 491ms
 */

public class D4_3289_서로소집합 {
	
	static int n, m;
	static int[] parents;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int test_case = Integer.parseInt(st.nextToken());
		for (int idx=1; idx<=test_case; idx++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());  // 원소의 개수
			m = Integer.parseInt(st.nextToken());  // 연산의 개수
			
			makeSet();
//			System.out.println(Arrays.toString(parents));
			sb.append("#"+idx+" ");
			for (int i=0; i<m; i++) {
				st = new StringTokenizer(br.readLine());
				int op = Integer.parseInt(st.nextToken());
				if (op == 0) {
					// 합집합 연산
					int a = Integer.parseInt(st.nextToken());
					int b = Integer.parseInt(st.nextToken());
					union(a, b);
				} else { 
					// 같은 집합에 포함되어 있는지 체크하는 연산 
					int a = Integer.parseInt(st.nextToken());
					int b = Integer.parseInt(st.nextToken());
					if (findSet(a) == findSet(b)) {   // 같은 집합에 속해있다면 1
						sb.append(1);
					} else {  // 아니면 0을 반환
						sb.append(0);
					}
					
				}
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());

	}
	
	private static void makeSet() { // 서로소 집합 만들기
		parents = new int[n+1];
		for (int i=1; i<=n; i++) {
			parents[i] = i;
		}

	}
	
	private static int findSet(int a) { // 부모 찾기
		if (parents[a] == a) return a;
		return parents[a] = findSet(parents[a]);
	}
	
	private static void union(int a, int b) { // 합집합 연산
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if (aRoot == bRoot) return;
		parents[bRoot] = aRoot;

	}

}













