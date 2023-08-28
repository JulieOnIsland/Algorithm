package live.algorithm.basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class SWEA_1767_프로세서연결하기 {
	
	static int N, max, totalCnt, min, map[][];
	static int[] dr = {-1, 0, 1, 0};  // up, left, down, right
	static int[] dc = {0, 1, 0, -1};
	static ArrayList<int[]> list; // 전선을 놓아야 할 코어리스트 (가장자리 코어는 저장x)

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int TC = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());  // 멕시노스 크기
			map = new int[N][N];
			// 변수 초기화 -----------------------------------
			max = 0; // 코어 개수
			min = Integer.MAX_VALUE; // 최소 전선 길이의 합
			totalCnt = 0;  // 연결해야 하는 코어 개수
			list = new ArrayList<int[]>();  // 연결해야하는 코어 리스트
			// -------------------------------------------
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					
					if ((i==0||i==N-1||j==0||j==N-1) && map[i][j]==1) continue;
					// 가장자리 코어 제외 (행이 0, N-1, 열이 0, N-1)
					
					if (map[i][j] == 1) {  // 코어
						list.add(new int[] {i, j});
						totalCnt++;
					}
				}
			}
			
			go(0, 0);
			System.out.println("#"+tc+" "+min);
			}
		}
	
	// 코어를 선택(4방향 시도)/비선택 -> 부분집합 (dfs)
	private static void go(int index, int coreCnt) { 
		// index: 고려해야 할 코어의 index, coreCnt: 연결된 코어 개수
		
		// 가지치기: 현재까지 연결된 코어수 + 남은 코어수 < 임시 최대 코어 연결 수
		if (coreCnt+(totalCnt-index) < max) return;
		
		// 기저조건 처리
		if (index == totalCnt) {
			int res = getLength(); // 놓아진 전선의 길이의 합
			if (max < coreCnt) { // 연결된 코어 개수가 max보다 크다면
				max = coreCnt; // max 값 업데이트
				min = res; // 전선 길의의 합 업데이트
			} else if (max == coreCnt) { // 연결된 코어 개수가 max와 같다면
				if (min > res) min = res; // 더 짧은 전선 길이의 합으로 업데이트
			}
			return;
		}
		
		int cur[] = list.get(index);
		int r = cur[0]; // row
		int c = cur[1]; // col
		// 현재 코어 선택(4방향 시도)
		for (int d = 0; d < 4; d++) {
			// 현재 코어의 위치에서 해당 방향으로 전선을 놓는 것이 가능한지 체크
			if (!isAvailable(r, c, d)) continue;
			
			// 가능하다며 전선 놓기
			setStatus(r, c, d, 2);
			
			// 다음 코어로 가기
			go(index+1, coreCnt+1);
			
			// 새로운 방향을 시도하기 위해 놓았던 전선 지우기
			setStatus(r, c, d, 0);
			
			// 전선을 놓는 코드, 다음 코어로 가는 코드, 전선을 지우는 코드 -> 대칭
		}
		
		// 현재 코어 비선택 
		go(index+1, coreCnt);

	}

	private static int getLength() { // 전선 길이를 구하는 메서드
		int lCnt = 0;
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if(map[i][j]==2) {
					lCnt++;
				}
			}
		}
		return lCnt;
	}

	private static void setStatus(int r, int c, int d, int status) { // 전선을 놓거나 지우는 메서드
		int nr = r;
		int nc = c;
		
		while (true) {
			nr += dr[d];
			nc += dc[d];
			if (!check(nr, nc)) break;
			map[nr][nc] = status;
		}
		
	}
	
	private static boolean check(int r, int c) { // 범위에 들어가는지 체크하는 메서드
		return 0<=r && r<N && 0<=c && c<N;

	}

	private static boolean isAvailable(int r, int c, int d) { 
		// 해당 진행 방향(d)으로 전선을 놓을 수 있는지 체크하는 메서드
		int nr = r;
		int nc = c;
		
		while (true) {
			nr += dr[d];
			nc += dc[d];
			if (!check(nr, nc)) break;
			if (map[nr][nc]!=0) return false;  // 빈칸이 아니라면, 즉 코어나 전선이 있다면, 가능하지 않음. 
		}
		return true;
	}

}
