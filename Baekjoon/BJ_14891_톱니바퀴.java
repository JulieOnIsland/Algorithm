package com.practice.makes.perfect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * BJ_14891_톱니바퀴
 * @author leejuhyun
 * 
 * 알고리즘 문제 정독 3번 해야한다는 걸 뼈저리게 느낀 문제. 
 * 문제를 내 마음대로 이해해서 풀이가 산으로 갔다. 
 * 단순 구현인데 거의 5시간을 푼 것 같다ㅠㅠ 
 * 
 * Memory: 14328kb
 * Time: 132ms
 */

public class BJ_14891_톱니바퀴 {
	
	static int ans;
	static int[][] T;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = new int[5][8];
		
		for (int i=1; i<=4; i++) {
			 st = new StringTokenizer(br.readLine());
			 char[] temp = st.nextToken().toCharArray();
			 for (int j=0; j<temp.length; j++) {
				 T[i][j] = temp[j] - '0';
			 }
		}
		st = new StringTokenizer(br.readLine());
		int orderNum = Integer.parseInt(st.nextToken());
		
		List<int[]> list = new ArrayList<>();
		
		for (int i=0; i<orderNum; i++) {
			st = new StringTokenizer(br.readLine());
			int t_num = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			
			list.add(new int[] {t_num, dir});
		}
		
		
		for (int i=0; i<list.size(); i++) {
			int[] temp = list.get(i);
			int t_num = temp[0];
			int dir = temp[1];
			boolean[] rotate = new boolean[5];
			
			switch (t_num) {
			case 1: // 1st 톱니바퀴 : 연쇄적으로 2nd, 3rd, 4th에 영향을 준다 
				for (int j=1; j<T.length-1; j++) {  // j=1, 2, 3
					if (T[j][2] != T[j+1][6]) {
						rotate[j+1] = true;
					} 
					else {
						break;
					}
				}

				rotate[1] = true;
				for (int j=1; j<rotate.length; j++) { // j=1, 2, 3, 4
					if (rotate[j] == true) {
						if (dir == 1) { // 시계방향 
							rotateCW(j);
							dir = -1;  // 그 다음 톱니바퀴는 반대 방향으로 회전되어야 함 
						} else { // 반시계방향 
							rotateCCW(j);
							dir = 1;
						}
					}
				}
				break;
			
			case 2: // 2nd 톱니바퀴: 1st가 영향을 받고, 3rd이 4th에 영향을 준다 
				// 1st 톱니바퀴 확인 
				if (T[1][2] != T[2][6]) {
					if (dir == 1) { 
						rotateCCW(1);
					} else {
						rotateCW(1);
					}
				}
				// 3rd, 4th 확인 
				for (int j=2; j<rotate.length-1; j++) { // j=2, 3
					if (T[j][2] != T[j+1][6]) {
						rotate[j+1] = true;
					} else {
						break;
					}
				}

				rotate[2] = true;
				for (int j=2; j<rotate.length; j++) { // j=3, 4
					if (rotate[j] == true) {
						if (dir == 1) { 
							rotateCW(j);
							dir = -1;
						} else {
							rotateCCW(j);
							dir = 1;
						}
					}
				}
				
				break;
			
			case 3: // 3rd 톱니바퀴: 4th가 영향을 받고, 2nd가 1st에 영향을 준다 
				// 4th 확인 
				if (T[3][2] != T[4][6]) {
					if (dir == 1) {
						rotateCCW(4);
					} else { 
						rotateCW(4);
					}
				}
				// 2nd, 1st 확인 
				for (int j=3; j>1; j--) { // j=3, 2
					if (T[j][6] != T[j-1][2]) {
						rotate[j-1] = true;
					} else {
						break;
					}
				}
				
				rotate[3] = true;
				for (int j=3; j>=1; j--) { // j=3, 2, 1
					if (rotate[j] == true) {
						if (dir == 1) { 
							rotateCW(j);
							dir = -1;
						} else {
							rotateCCW(j);
							dir = 1;
						}
					}
				}
				break;
				
			case 4: //4th 톱니바퀴: 연쇄적으로 3rd, 2nd, 1st에 영향을 준다 
				for (int j=4; j>1; j--) {  // j=4, 3, 2
					if (T[j][6] != T[j-1][2]) {
						rotate[j-1] = true;
					} else {
						break;
					}
				}
				rotate[4] = true;
				for (int j=4; j>=1; j--) { // j=3, 2, 1
					if (rotate[j] == true) {
						if (dir == 1) { 
							rotateCW(j);
							dir = -1; 
						} else {
							rotateCCW(j);
							dir = 1;
						}
					}
				}
				break;
			}
			
		}
		
		// ans
		ans = 0;
		int[] ansArray = {0, 1, 2, 4, 8};
		for (int i=1; i<T.length; i++) {
			if (T[i][0] == 1) {
				ans += ansArray[i];
			}
		}
		System.out.println(ans);

	}
	
	private static void rotateCW(int t_num) {  // 시계방향 
		int[] temp = new int[8];
		int fin = T[t_num][7];
		for (int i=1; i<8; i++) {
			temp[i] = T[t_num][i-1];
		}
		temp[0] = fin;
		T[t_num] = Arrays.copyOf(temp, temp.length);
		
	}
	
	private static void rotateCCW(int t_num) {
		int[] temp = new int[8];
		int init = T[t_num][0];
		for (int i=0; i<7; i++) {
			temp[i] = T[t_num][i+1];
		}
		temp[7] = init;
		T[t_num] = Arrays.copyOf(temp, temp.length);
	}

}











