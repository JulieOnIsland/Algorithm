import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * D4 7465 창용마을무리의개수
 * @author leejuhyun
 * 
 * 마을에 몇 개의 무리가 존재하는지 무리의 개수 구하기
 * union-find로 무리의 개수를 세보자.
 * 
 * Memory: 25,380kb
 * Time: 128ms
 */

public class D4_7465_창용마을무리의개수 {
	
	static int N, M, parents[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 창용 마을에 사는 사람의 수 
			M = Integer.parseInt(st.nextToken()); // 관계의 수
			
			makeSet(); // 그룹을 대표하는 숫자를 담는 parents[] 배열 만들기 (처음에는 자기가 자신을 대표)
			
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				union(from, to);
			}
			
//			System.out.println(Arrays.toString(parents));
			
			int groupCnt = 0;
			for (int i = 1; i <= N; i++) {
				if (i == parents[i]) groupCnt++;
			}
		
			System.out.println("#"+tc+" "+groupCnt);
		}

	}
	
	// UNION FIND --------------------------------------------------
	private static void makeSet() {
		parents = new int[N+1];
		for (int i = 1; i <= N; i++) {
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
		if (aRoot == bRoot) return false; // 사이클이 발생했으므로, 즉 이미 같은 그룹이므로 union 할 필요없음.
		parents[bRoot] = aRoot; // union 해주기
		return true;
	}
	
	//  ------------------------------------------------------------

}
