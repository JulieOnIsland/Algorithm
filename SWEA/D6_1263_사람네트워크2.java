import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


/**
 * D6 1263 �����Ʈ��ũ2
 * @author leejuhyun
 * �÷��̵�-���� �˰���
 * 
 * CC (Closeness Centrality): �� ����ڰ� �ٸ� ��� ������� �󸶳� ��������� ��Ÿ��
 * 
 * Memory: 97,452kb, Time: 2,894ms
 */

public class D6_1263_�����Ʈ��ũ2 {
	
	static int[][] D;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken()); // �׽�Ʈ ���̽� ����
		for (int idx = 1; idx <= T; idx++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			D = new int[N][N];
			
			// ������� �����
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
		for (int k = 0; k < D.length; k++) { // ������
			for (int i = 0; i < D.length; i++) { // �����
				if (i == k) continue;
				for (int j = 0; j < D.length; j++) { // ������
					if (j==k || j==i) continue;
					D[i][j] = Math.min(D[i][k] + D[k][j], D[i][j]);
				}
			}
		}

	}

}
