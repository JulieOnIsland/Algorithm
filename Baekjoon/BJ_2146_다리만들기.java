import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BJ 2146 �ٸ������
 * @author leejuhyun
 * �� ����� �����ϴ� ���� ª�� �ٸ��� ���� ���ϱ� (BFS 2��)
 * Step 1. flood fill �˰������� ���� ǥ���ϱ�
 * Step 2. 0�� �پ� �ִ� ��ǥ �߿��� �ٸ� ���� ����� ���� ª�� ���� ���ϱ�
 * 
 * Memory: 79012kb
 * Time: 248ms
 */

public class BJ_2146_�ٸ������ {
	
	static int N, grid[][], length;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static boolean visited[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		grid = new int[N][N];
		for (int i = 0; i < grid.length; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < grid.length; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
			}
		}
//		System.out.println(Arrays.deepToString(grid));
		visited = new boolean[N][N];
		
		// floodfill �˰��� �̿��ؼ� ������ ������� ������
		int cnt = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (grid[i][j] == 1 && !visited[i][j]) { // 1�̰�, �湮�� ���� ���ٸ�
					floodfill(i, j, cnt++);
				}
			}
		}
		
//		print();
		
		// ª�� �ٸ� ���� ã��
		int num = cnt - 1; // ���� ����
		length = Integer.MAX_VALUE;
		// 0�� �ƴ� ��ȣ�� ���� �� �߿� �ϳ�, �׷��� 0�� �ϳ��� ��ġ�� �κ��� �־�� ��. 
		// �׷� �� �߿��� �� ���ڰ� �ƴ� �ٸ� ������� �ִ� �Ÿ� ���ϱ� 
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (grid[i][j] != 0 && isAvailable(i, j)) { // 0�� �ƴϰ�, ���̸� ���� �� �ִ� ���� ��ǥ���
					visited = new boolean[N][N];
					bfsLength(i, j, grid[i][j]);
				}
			}
		}
		
		System.out.println(length);

	}
	
	private static void bfsLength(int r, int c, int value) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {r, c, 0});
		visited[r][c] = true; 
		
		while (!q.isEmpty()) {
			int[] current = q.poll();
			int cr = current[0];
			int cc = current[1];
			int len = current[2];
			for (int d = 0; d < 4; d++) {
				int nr = cr + dr[d];
				int nc = cc + dc[d]; 
				if (check(nr, nc) && grid[nr][nc] != value && !visited[nr][nc]) { 
					// ���� �ȿ� ����, value�� �ƴϸ鼭, �� ���� �湮�� ���� ���ٸ�
					if (grid[nr][nc] == 0) { // �ٸ��̸�
						q.offer(new int[] {nr, nc, len+1}); // ť�� �ְ�
						visited[nr][nc] = true; // �湮ó��
					} else { // �ٸ� ���̶��
						if (length > len) length = len;
						return;
					}
			
				}
			}
		}

	}
	
	private static boolean isAvailable(int r, int c) { // ���̸� ���� �� �ִ°�
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if (check(nr, nc) && grid[nr][nc] == 0) { // ���� �ȿ� ���鼭 0�� �ϳ��� ��ġ�� �κ��� �ִٸ�
				return true;
			}
		}
		return false;

	}
	
	private static void floodfill(int r, int c, int fill) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {r, c});
		visited[r][c] = true;
		grid[r][c] = fill;
		
		while (!q.isEmpty()) {
			int[] current = q.poll();
			int cr = current[0]; // current row
			int cc = current[1]; // current col
			for (int d = 0; d < 4; d++) {
				int nr = cr + dr[d]; // next row
				int nc = cc + dc[d]; // next col
				if (check(nr, nc) && grid[nr][nc]==1 && !visited[nr][nc]) { // ������ ����, 1�̸鼭 �湮���� �ʾҴٸ�			
					q.offer(new int[] {nr, nc}); // ť�� �ְ�
					visited[nr][nc] = true; // �湮ó��
					grid[nr][nc] = fill; // �� �ٲٱ�
				}
			}
		}

	}
	
	private static boolean check(int r, int c) {
		return 0<=r && r<N && 0<=c && c<N;

	}
	
	private static void print() {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length; j++) {
				System.out.print(grid[i][j]+" ");
			}
			System.out.println();
		}

	}

}



















