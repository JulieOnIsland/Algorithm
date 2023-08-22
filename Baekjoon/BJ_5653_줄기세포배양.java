package com.practice.makes.perfect;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class BJ_5653_줄기세포배양 {
	
	static class Cell{
		int r, c, life, cycle, status=SUSPEND;
		Cell(int r, int c, int life, int cycle){
			this.r=r;
			this.c=c;
			this.life=life;
			this.cycle=cycle;
		}
		public void passTime() {
			switch (status) {
			case SUSPEND:
				if(--cycle==life) status = ACTIVE;
				break;
			case ACTIVE:
				if(--cycle==0) status = DEATH;
				break;	
			}
		}
	}
	
	static final int SUSPEND = 0, ACTIVE=1, DEATH=2;
	static int[][] map;
	static int N, M, K, T;
	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };
	static int MAXN, MAXM, startM, startN;
	static boolean[][] v;
	static Queue<Cell> Q;
	
	public static void main(String[] args) throws FileNotFoundException {
		
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			Q = new ArrayDeque<>();
			N = sc.nextInt();
			M = sc.nextInt();
			K = sc.nextInt();
			// 지도크기
			MAXN = K * 2 + N + 2;
			MAXM = K * 2 + M + 2;
			startN = MAXN / 2 - N / 2;
			startM = MAXM / 2 - M / 2;
			map = new int[MAXN][MAXM];
			v = new boolean[MAXN][MAXM];

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					int input = sc.nextInt();
					if (input > 0) {
						map[startN + r][startM + c] = input;
						v[startN + r][startM + c] = true;
						Q.add(new Cell(startN + r, startM + c,input,input*2));
					}
				}
			}
			//print(map);
			//System.out.println(Q.size());
			solving();
			// k 번 텀이 지나고 나서도 Q.에  남아있는 셀이 바로 살아있는 cell
			System.out.printf("#%d %d\n",tc,Q.size());
		}
	}
	
	private static void spread(Cell cell) {
		for (int d = 0; d < dc.length; d++) {
			int nr = cell.r + dr[d];
			int nc = cell.c + dc[d];
			// 이미 번식한 위치라면 pass
			if(v[nr][nc]) continue;
			// 4방으로 번식한다
			if(cell.life > map[nr][nc]) map[nr][nc]= cell.life;
		}
	}
	
	private static void solving() {
		// k번 증식을 한다
		for (int k = 0; k < K; k++) {
			// 활성상태의 셀을 지도상에서 번식한다
			for (Cell cell : Q) {
				if(cell.status==ACTIVE) spread(cell);
			}
			// 한단계의 자식레벨 만큼씩을 처리하기 위해서 level을 구합니다
			int level = Q.size();
			for (int step = 0; step < level; step++) {
				Cell cell = Q.poll();
				//  활성화 상태라면 Q에 번식을 합니다
				if(cell.status==ACTIVE) {
					for (int d = 0; d < dc.length; d++) {
						int nr = cell.r + dr[d];
						int nc = cell.c + dc[d];
						// 이미 번식한 위치라면 pass
						if(v[nr][nc]) continue;
						// Q에 번식을 합니다
						Q.add(new Cell(nr, nc, map[nr][nc], map[nr][nc]*2));
						v[nr][nc]=true;
					}
				}
				// 시간이 한텀 지나갑니다
				cell.passTime();
				// 죽은 셀은 Q에 담지 않고 
				if(cell.status==DEATH) continue;
				Q.add(cell);
			}
		}
	}

	private static void print(int[][] map) {
		for (int r = 0; r < MAXN; r++) {
			for (int c = 0; c < MAXM; c++) {
				System.out.print(map[r][c] + " ");
			}
			System.out.println();
		}
	}
}
