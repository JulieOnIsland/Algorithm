package com.practice.makes.perfect;

import java.util.Scanner;

/**
 * @author leejuhyun
 * 
 * N*N maze
 * 1, 2, 3, 4의 방향으로 이동할 수 있다. 1은 위쪽, 2는 오른쪽, 3은 아래쪽, 4는 왼쪽.
 * 
 * [제한조건]
 * 1. N은 자연수 3~20의 값이다.
 * 2. 출발점 좌표에서 이동 지시의 방향으로 제시된 이동 칸 수 만큼 반복적으로 이동한다.
 * 3. 이동 시 점퍼에 의해 밖으로 나오거나, N*N 칸을 벗어났을 경우 좌표는 (0,0)을 갖고, 이후 명령 무시.
 * 
 * [입력]
 * 1. 첫 줄에는 테스트 케이스 T가 주어진다.
 * 2. 두 번째 줄에는 배열의 크기인 N과 출발점의 좌표(행, 열), 점퍼의 개수(1~N)가 주어진다.
 * 3. 세 번째 줄에는 점퍼의 개수만큼 점퍼의 좌표(행, 열)가 주어진다.
 * 4. 네 번째 줄에는 방향 지시의 개수(0~50)가 주어진다.
 * 5. 다섯 번째 줄에는 방향 지시의 개수만큼 (빵향, 이동칸수)*방향지시개수가 차례로 주어진다.
 * 6. 테스트 케이스가 반복적으로 주어진다.
 * 
 * [출력]
 * #(테스트케이스번호) 좌표를 (행, 열) 순서로 출력한다
 * 
 * [예시]
	3
	8 5 3 4
	1 8 5 5 1 2 6 7
	5
	1 3 2 1 3 1 4 1 1 2
	7 4 4 4
	1 7 5 5 1 2 6 3
	4
	4 3 3 1 2 2 3 2 
	5 1 1 1
	1 2
	5
	3 2 2 1 3 3 2 4 3 1
	
	#1 1 3 
	#2 0 0
	#3 0 0

 * 
 */
public class MazeExit {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int testCase = sc.nextInt();
		
		for (int i=1; i<testCase+1 ; i++) {
			int n = sc.nextInt();
			int initial_row = sc.nextInt();
			int initial_col = sc.nextInt();
			
			// jumper coordinate
			int jumper_num = sc.nextInt();
			int[] jumper_row = new int[jumper_num];
			int[] jumper_col = new int[jumper_num];
			
			for (int j=0; j<jumper_num; j++) {
				jumper_row[j] = sc.nextInt();
				jumper_col[j] = sc.nextInt();
			}
			
			// moving
			int moving_order_num = sc.nextInt();
			int[] movingDirectionList = new int[moving_order_num];
			int[] movingDistanceList = new int[moving_order_num];
			
			for (int j=0; j < moving_order_num; j++) {
				movingDirectionList[j] = sc.nextInt();
				 movingDistanceList[j] = sc.nextInt();
			}
			
			// set four directions [ 1: up, 2: right, 3: down, 4: left ]
			int[] col = {0, -1, 0, 1, 0};
			int[] row = {0, 0, 1, 0, -1}; 
			
			int current_row = 0;
			int current_col = 0;
			
			for (int j=0; j < moving_order_num; j++) {
				for (int k=1; k < movingDistanceList[j]+1; k++) {
					current_row = initial_row + col[movingDirectionList[j]];
					current_col = initial_col + row[movingDirectionList[j]];
					
					if (current_row > n || current_col > n || current_col < 1 || current_row < 1) {
						current_row = 0; current_col = 0;
						break;
					}
					
					for (int m=0; m < jumper_num; m++) {
						if (current_row == jumper_row[m] && current_col == jumper_col[m]) {
							current_row = 0; current_col = 0;
							break;
						}
					}
					
					initial_row = current_row;
					initial_col = current_col;
				}
			}
			System.out.printf("#%d %d %d %n", i, current_row, current_col);
		}
	}
	
	
}



















