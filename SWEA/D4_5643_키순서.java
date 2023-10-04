import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

/**
 * D4_5643_키순서_이주현
 * @author leejuhyun
 * 
 * 플로이드-워샬 알고리즘 이용.
 * 
 * Memory: 91,064kb
 * Time: 1,706ms
 */

public class D4_5643_키순서 {

	static int N, M;
	static int graph[][];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int testCase = Integer.parseInt(st.nextToken());
		for (int idx = 1; idx <= testCase; idx++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 학생들의 수 
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken()); // 학생들의 키를 비교한 횟수
			graph = new int[N+1][N+1];
		
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				graph[from][to] = 1;
			}
			
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (i==j) continue;
					if (graph[i][j] != 1) graph[i][j] = 1000;  // 연결 안 되어 있는 경우 inf를 대신하는 큰 값으로 초기화.
				}
			}

//			print();
			AllPairsShortest(graph);
//			System.out.println("------------------");
//			print(); // 결과로 나온 행과 열의 의미를 잘 파악하자!
			
			// 자신의 키가 몇 번째인지 정확히 알 수 있는 경우의 수 세기
			int cnt = 0;
			for (int i = 1; i <= N; i++) {
				boolean flag = true;
				for (int j = 1; j <= N; j++) {
					if (i==j) continue;
					if (graph[i][j] == 1000) {
						 if (graph[j][i] == 1000) {
							 flag = false;
							 break;
						 }
						 
					}
				}
				if (flag) cnt++;
			}
			System.out.println("#"+idx+" "+cnt);
			
		}
		
	}
	
	private static void AllPairsShortest(int[][] D) {
		for (int k = 1; k < D.length; k++) { // 경유지
			for (int i = 1; i < D.length; i++) { // 출발점
				if (i == k) continue;
				for (int j = 1; j < D.length; j++) { // 도착점
					if (j==k || j==i) continue;
					D[i][j] = Math.min(D[i][k] + D[k][j], D[i][j]);
				}
			}
		}

	}
	
	private static void print() {
		for (int i = 0; i < graph.length; i++) {
			for (int j = 0; j < graph.length; j++) {
				System.out.print(graph[i][j]+" ");
			}
			System.out.println();
		}

	}
	
}
