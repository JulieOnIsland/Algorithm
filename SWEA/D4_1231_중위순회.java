package solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 중위 순회 문제
 * 
 * Time: 101ms
 * Memory: 18,592kb
 */

public class D4_1231_중위순회 {

	private static int N;
	private static String[] ex;
	private static String ans;
 
	public static void main(String[] args) throws Exception {
		int testcase = 10;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int tc = 1; tc <= testcase; tc++) {
			N = Integer.parseInt(br.readLine());
			ex = new String[N+1];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int index = Integer.parseInt(st.nextToken());
				String element = st.nextToken();
				ex[index] = element;
			}
			
			ans = "";
			inorder(1);
			System.out.println("#"+tc+" "+ans);
		}

	}
	
	private static void inorder(int i) {
		
		if (i <= N) {
			inorder(i * 2); // L
			ans += ex[i]; // V
			inorder(i * 2 + 1); // R
		}

	}

}
