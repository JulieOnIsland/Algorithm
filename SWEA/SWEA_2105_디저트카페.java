package com.practice.makes.perfect;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_2105_디저트카페 {
	private static int[][] m;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= TC; testCase++) {
			int N = Integer.parseInt(br.readLine()); // 지역 크기, 4 <= N <= 20
			m = new int [N][N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					m[i][j] = Integer.parseInt(st.nextToken()); // 디저트 번호, 1 <= <= 100
				}
			}
			int maxCnt = -1;
			for (int r = 0; r < N-2; r++) {
				for (int c = 1; c < N-1; c++) {
					for (int w = 1; r+w < N && c+w < N; w++) {
						for (int h = 1; r+h+w < N && 0 <= c-h; h++) {
							int cnt = go(r, c, w, h); 
							if (maxCnt < cnt) maxCnt = cnt;
						}
					}
				}
			}
			sb.append('#').append(testCase).append(' ').append(maxCnt).append('\n');
		} // end of for testCase
		System.out.print(sb);
	} // end of main
	private static boolean[] flag = new boolean[101];
	/** r,c 위치를 기준으로 폭w,높이h 인 사각형 길을 순회하기 return (w + h) * 2; 먹은 디저트 개수 */
	public static int go(int r, int c, int w, int h) {
		for (int i = 0; i < flag.length; i++) {
			flag[i] = false;
		}
		for (int i = 0; i <= w; i++) {
			if (flag[m[r+i][c+i]]) return -1;
			flag[m[r+i][c+i]] = true;
			if (flag[m[r+h+i][c-h+i]]) return -1;
			flag[m[r+h+i][c-h+i]] = true;
		}
		for (int i = 1; i < h; i++) { 
			if (flag[m[r+i][c-i]]) return -1;
			flag[m[r+i][c-i]] = true;
			if (flag[m[r+w+i][c+w-i]]) return -1;
			flag[m[r+w+i][c+w-i]] = true;
		}
		return (w + h) * 2;
	}
} // end of class