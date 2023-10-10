import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * BJ 17144 �̼������ȳ�
 * @author leejuhyun
 * 
 * 1�� ���� ������ ���� ���� ������� �Ͼ��.
 * 1. �̼����� Ȯ��: �̼������� �ִ� ��� ĭ���� "���ÿ�" �Ͼ��.
 * 2. ���� û���� �۵�: ȸ��
 * 
 * MEMORY: 301832kb
 * TIME: 768ms
 * 
 */
public class BJ_17144_�̼������ȳ� {
	
	static int R, C, grid[][], MR;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken()); // T��
		
		MR = 0; // machine row
		grid = new int[R][C];
		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				grid[r][c] = Integer.parseInt(st.nextToken());
				if (grid[r][c] == -1) MR = r;
			}
		}
//		System.out.println(Arrays.deepToString(grid));
		
		for (int t = 0; t < T; t++) {
			// �̼������� Ȯ��� ����û���� ���ʸ��� �̷������.
			moveDust(); 
//			System.out.println("�̼����� Ȯ��� ��: ");
//			print();
//			System.out.println("-----------------------------");
			rotate();
//			System.out.println("����û���� ȸ��: ");
//			print();
		}
		
		// �̼� ������ �� ���
		int answer = 0;
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (grid[r][c] != 0 && grid[r][c] != -1) {
					answer += grid[r][c];
				}
			}
		}
		System.out.println(answer);
		
	}
	
	private static void moveDust() {
		// ���� �̼������� ��� �迭: list
		List<int[]> list = new ArrayList<>(); // �̼������� ��ǥ(row,col)�� �̼������� ��
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (grid[r][c] != 0 && grid[r][c] != -1) {
					list.add(new int[] {r, c, grid[r][c]});
				}
			}
		}
		
		// Ȯ��� �̼������� ��� �迭 : movingList
		List<int[]> movingList = new ArrayList<>(); 
		
		for (int[] e: list) {
			int r = e[0];
			int c = e[1];
			int amount = e[2];
			int dirCnt = 0;  // Ȯ��Ǵ� ������ ����
			for (int d = 0; d < 4; d++) {
				int nr = r+dr[d]; // next row
				int nc = c+dc[d]; // next col
				if (check(nr, nc)) { // ������ ����
					// ������ ���� ���� û���⸦ �����ٸ� ������ ������ Ŀ���� �� ��. ����û���� ��ǥ�� �ƴ� ������ dirCnt ����
					if (grid[nr][nc]!=-1) {
						dirCnt++;
						movingList.add(new int[] {nr, nc, amount/5}); // Ȯ��� �̼������� ��� �迭�� �߰�
					}
					
				} 
			}
			// 4������ ���� �� ���´ٸ�, �� �ڸ��� ���� �̼����� ������ֱ�.
			grid[r][c] -= (amount/5)*dirCnt;
		}

		// ���� �̼������� ��� �迭 ó���� �����ٸ�, Ȯ��� ������ ó��������.
		for (int[] e:movingList) {
			int r = e[0];
			int c = e[1];
			int addedAmount = e[2];
			grid[r][c] += addedAmount;
		}

	}
	
	private static void rotate() {
		// ���� û���� row ��ǥ�� ���� : MR-1, ��: MR
		
		int[] drc = {-1, 0, 1, 0}; // direction of row clockwise 
		int[] dcc = {0, 1, 0, -1}; // direction of cow clockwise 
		
		int dir = 0;
		int cr = MR-2, cc = 0; // current row, current col
		
		while (true) {
			int nr = cr + drc[dir];
			int nc = cc + dcc[dir];
			
			if (0<=nr && nr<MR && 0<=nc && nc<C) { // ���� �ȿ� ���ٸ�
				if (grid[nr][nc] == -1) { // �� ���� ��ǥ�� ����û���� ��ǥ���
					grid[cr][cc] = 0;
					break;
				}
				grid[cr][cc] = grid[nr][nc];
				cr = nr;
				cc = nc;
			
			} else {
				dir++;
			}
			
			if (nr == MR-1 && nc == 0) break;
		}
		
		// -------------------------------------------------------------------
		
		int[] drcc = {1, 0, -1, 0}; // direction of row counter-clockwise
		int[] dccc = {0, 1, 0, -1}; // direction of cow counter-clockwise
		
		dir = 0;
		cr = MR+1; cc = 0;
		while (true) {
			int nr = cr + drcc[dir];
			int nc = cc + dccc[dir];
			
//			if (nr == MR && nc == 0) break;
			
			if (MR<=nr && nr<R && 0<=nc && nc<C) { // ���� �ȿ� ���ٸ�
				if (grid[nr][nc] == -1) { // �� ���� ��ǥ�� ����û���� ��ǥ���
					grid[cr][cc] = 0;
					break;
				}
				grid[cr][cc] = grid[nr][nc];
				cr = nr;
				cc = nc;
			
			} else {
				dir++;
			}
			
			if (nr == MR && nc == 0) break;
		}
		
	}
	
	private static boolean check(int r, int c) {
		return 0<=r && r<R && 0<=c && c<C;

	}
	
	private static void print() {
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				System.out.print(grid[r][c]+" ");
			}
			System.out.println();
		}
	}

}

