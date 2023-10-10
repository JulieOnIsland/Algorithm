import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * D4 7465 â�븶�������ǰ���
 * @author leejuhyun
 * 
 * ������ �� ���� ������ �����ϴ��� ������ ���� ���ϱ�
 * union-find�� ������ ������ ������.
 * 
 * Memory: 25,380kb
 * Time: 128ms
 */

public class D4_7465_â�븶�������ǰ��� {
	
	static int N, M, parents[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // â�� ������ ��� ����� �� 
			M = Integer.parseInt(st.nextToken()); // ������ ��
			
			makeSet(); // �׷��� ��ǥ�ϴ� ���ڸ� ��� parents[] �迭 ����� (ó������ �ڱⰡ �ڽ��� ��ǥ)
			
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
		if (aRoot == bRoot) return false; // ����Ŭ�� �߻������Ƿ�, �� �̹� ���� �׷��̹Ƿ� union �� �ʿ����.
		parents[bRoot] = aRoot; // union ���ֱ�
		return true;
	}
	
	//  ------------------------------------------------------------

}
