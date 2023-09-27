import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


/**
 * D6 1263 사람네트워크2
 * @author leejuhyun
 * 플로이드-워샬 알고리즘
 * 
 * CC (Closeness Centrality): 한 사용자가 다른 모든 사람에게 얼마나 가까운지를 나타냄
 * 
 * Memory: 97,452kb, Time: 2,894ms
 */

public class D6_1263_사람네트워크2 {
	
	static int[][] D;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken()); // 테스트 케이스 개수
		for (int idx = 1; idx <= T; idx++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			D = new int[N][N];
			
			// 인접행렬 만들기
			for (int i = 0; i < D.length; i++) {
				for (int j = 0; j < D.length; j++) {
					D[i][j] = Integer.parseInt(st.nextToken()); 
				}
			}
			
			for (int i = 0; i < D.length; i++) {
				for (int j = 0; j < D.length; j++) {
					if (i == j) continue;
					if (D[i][j] == 0) D[i][j] = 1000;
				}
			}
//			System.out.println(Arrays.deepToString(D));
			
			AllPairsShortest(D);
			
			int min = Integer.MAX_VALUE;
			for (int i = 0; i < D.length; i++) {
				int temp = 0;
				for (int j = 0; j < D.length; j++) {
					temp += D[i][j];
				}
				if (min > temp) min = temp;
			}
			
			System.out.println("#"+idx+" "+min);
		}
		
		
	}
	
	private static void AllPairsShortest(int[][] D) {
		for (int k = 0; k < D.length; k++) { // 경유지
			for (int i = 0; i < D.length; i++) { // 출발점
				if (i == k) continue;
				for (int j = 0; j < D.length; j++) { // 도착점
					if (j==k || j==i) continue;
					D[i][j] = Math.min(D[i][k] + D[k][j], D[i][j]);
				}
			}
		}

	}

}
