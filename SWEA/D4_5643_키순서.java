import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

/**
 * D4_5643_Ű����_������
 * @author leejuhyun
 * 
 * �÷��̵�-���� �˰��� �̿�.
 * 
 * Memory: 91,064kb
 * Time: 1,706ms
 */

public class D4_5643_Ű���� {

	static int N, M;
	static int graph[][];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int testCase = Integer.parseInt(st.nextToken());
		for (int idx = 1; idx <= testCase; idx++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // �л����� �� 
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken()); // �л����� Ű�� ���� Ƚ��
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
					if (graph[i][j] != 1) graph[i][j] = 1000;  // ���� �� �Ǿ� �ִ� ��� inf�� ����ϴ� ū ������ �ʱ�ȭ.
				}
			}

//			print();
			AllPairsShortest(graph);
//			System.out.println("------------------");
//			print(); // ����� ���� ��� ���� �ǹ̸� �� �ľ�����!
			
			// �ڽ��� Ű�� �� ��°���� ��Ȯ�� �� �� �ִ� ����� �� ����
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
		for (int k = 1; k < D.length; k++) { // ������
			for (int i = 1; i < D.length; i++) { // �����
				if (i == k) continue;
				for (int j = 1; j < D.length; j++) { // ������
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
