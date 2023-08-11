package live.algorithm.basic;

import java.util.Arrays;
import java.util.Scanner;

/**
 * BJ 15686 치킨배달
 * @author leejuhyun
 *
 * Memory: 24164kb,
 * Time: 428ms
 */

public class BJ_15686_치킨배달_이주현 {
	
	static int N, M;
	static int ckCount;
	static int totalDist;
	static int[] minDist;
	static int minDistIdx;
//	static int totalDist = Integer.MAX_VALUE;
	static int[][] map;
	static int[] selected;
	static int[][] ckArray;

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][N];
		selected = new int[M];
		minDist = new int[10000];  // 우선 10개로 설정.
		minDistIdx = 0;
		
		// Make a map
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		// 치킨 집 개수
		ckCount = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 2) {
					ckCount++;
				}
			}
		}
//		System.out.println(ckCount);
		
		// chicken coordinate array
		ckArray = new int[ckCount][2];
		int idx = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 2) {
					ckArray[idx++] = new int[]{i, j};
				}
			}
		}
		
		// Print out
//		for (int[] elements: ckArray) {
//			for (int e: elements) {
//				System.out.print(e+" ");
//			}
//			System.out.println();
//		}
		
		// 거리 최솟값 구하기
		totalDist = 0;
		if (ckCount == M) {// 만약 치킨집 개수와 M이 같다면, 단순하게 치킨집과 집과의 거리를 구해주면 된다
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == 1) { // 집이라면, 치킨집과의 거리를 구해야 한다
						// 어떤 치킨 집과 가까울까?
						int tempDist = Integer.MAX_VALUE;
						for (int k = 0; k < ckCount; k++) {
							int dist = Math.abs(i - ckArray[k][0]) + Math.abs(j - ckArray[k][1]);
							if (dist < tempDist) {
								tempDist = dist;
							}
						}
						totalDist += tempDist;
					}
				}
			}
		} else { // 최대 M개의 치킨집을 조합으로 고르고, 거리를 구해줘야 한다.
//			System.out.println("hello");
			
			comb(0, 0);
			
			int init = Integer.MAX_VALUE;
			for (int i = 0; i < minDist.length; i++) {
				if (minDist[i] == 0) break;
				if (minDist[i] < init) {
					init = minDist[i];
				}
			}
			totalDist = init;
		}
		
		System.out.println(totalDist);
//		System.out.println(Arrays.toString(minDist));

	}
	
	private static void comb(int cnt, int start) {
		if (cnt == M) {
			// selected의 배열이 만들어짐. 
//			System.out.println(Arrays.toString(selected));
			// selected가 [0, 1], [0, 2] , ... , [3, 4]
			
			// selected chicken coordinate array
			int[][] selectedCkArray = new int[M][2];
			int idx = 0;
			for (int i = 0; i < selected.length; i++) {
				idx = selected[i];   // 치킨집 좌표 ckArray   
				selectedCkArray[i] = ckArray[idx];
			}
			
			totalDist = Integer.MAX_VALUE;
			int totalTempDist = 0;
			// 고른 치킨집의 좌표를 가지고 이제 거리를 구한다. 
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == 1) {
						int tempDist = Integer.MAX_VALUE;
						for (int k = 0; k < M; k++) {
							int dist = Math.abs(i - selectedCkArray[k][0]) + Math.abs(j - selectedCkArray[k][1]);
							if (dist < tempDist) {
								tempDist = dist;
							}
						}
//						System.out.print("tempDist: "+tempDist);
						totalTempDist += tempDist;
//						System.out.println(", totaltempDist: "+totalTempDist);
					}
				}
			}
			if (totalTempDist < totalDist) {
				totalDist = totalTempDist;  
				minDist[minDistIdx++] = totalDist;
//				System.out.println("totalDist: "+totalDist);
			}
			
			
			return;
		}
		
		for (int i = start; i < ckCount; i++) {
			selected[cnt] = i;          // selected에 인덱스 번호를 넣어줌.
			comb(cnt + 1, i + 1);
		}

	}

}
