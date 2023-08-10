package live.algorithm.basic;

import java.util.Arrays;
import java.util.Scanner;

/**
 * SWEA D3 6808 규영이와인영이의카드게임
 * @author leejuhyun
 * 
 * 순열
 *
 */

public class D3_6808_규영이와인영이의카드게임 {

	// next permutation 으로도 풀 수 있음.
	static int totalWin, totalLose;
	static int[] kyu, inyoung;
	static int[] numArray, selectedArray;
	static boolean[] visited;
	static boolean[] isSelected;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int test_case = sc.nextInt();
		
		for (int idx = 1; idx <= test_case; idx++) {
			visited = new boolean[19];
			isSelected = new boolean[9];
			
			kyu = new int[9];  // 1 3 5 7 9 11 13 15 17
			inyoung = new int[9]; 
			selectedArray = new int[9];
			
			for (int i = 0; i < 9; i++) { // 규영 카드 배열 채우기
				kyu[i] = sc.nextInt();
			}
			
			for (int i = 0; i < kyu.length; i++) {
				visited[kyu[i]] = true;  // 규영이의 카드는 true로 바꿔주기
			}
			
			
			// 인영이 카드 배열 만들기
			int cnt = 0;
			for (int i = 1; i < visited.length; i++) {
				if (visited[i] == false) {
					inyoung[cnt++] = i;
				}
			}
			
			perm(0);
			
			System.out.println("#"+idx+" "+totalWin+" "+totalLose);
			totalWin = 0; totalLose = 0;
		}

	}
	
	private static void perm(int cnt) {
		if (cnt == 9) {
//			System.out.println(Arrays.toString(selectedArray));
			int tempKyuScore = 0;
			int tempInScore = 0;
			for (int i = 0; i < 9; i++) {  // selectedArray (인영이의 순열)과 kyu (규영이 카드 배열) 비교
				if (selectedArray[i] > kyu[i]) {
					tempInScore += selectedArray[i] + kyu[i];
				} else {
					tempKyuScore += selectedArray[i] + kyu[i];
				}
			}
			if (tempInScore > tempKyuScore) {
				totalLose++;
			} else {
				totalWin++;
			}
			return;
		}
		
		for (int i = 0; i < 9; i++) {
			if (isSelected[i]) continue;
			
			selectedArray[cnt] = inyoung[i];
			isSelected[i] = true;
			perm(cnt + 1);
			isSelected[i] = false;
		}
		
	}

}
