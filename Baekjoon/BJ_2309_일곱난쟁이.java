package live.algorithm.basic;

import java.util.Arrays;
import java.util.Scanner;

/**
 * BJ 2309 일곱난쟁이
 * @author leejuhyun
 * 
 * 조합
 *
 */

public class BJ_2309_일곱난쟁이 {
	
	static int[] heights;
	static int[] temp;
	static int[] sevenDwarfs;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		heights = new int[9];
		temp = new int[7];
		sevenDwarfs = new int[7];
		
		// 9명의 난쟁이 키가 주어짐.
		for (int i = 0; i < 9; i++) {
			heights[i] = sc.nextInt();
		}
		
		comb(0, 0);
		Arrays.sort(sevenDwarfs);
		for (int dwarf: sevenDwarfs) {
			System.out.println(dwarf);
		}

	}
	
	private static void comb(int cnt, int start) {
		if (cnt == 7) {
			int tempHeightSum = 0;
//			System.out.println(cnt +" "+ start+" " + Arrays.toString(temp));
			for (int i = 0; i < 7; i++) {
				tempHeightSum += temp[i];
			}
			if (tempHeightSum == 100) {
				sevenDwarfs = Arrays.copyOf(temp, 7);
			}
			return;
		}
		
		for (int i = start; i < 9; i++) {
			temp[cnt] = heights[i];
			comb(cnt + 1, i + 1);
			
		}

	}

}

