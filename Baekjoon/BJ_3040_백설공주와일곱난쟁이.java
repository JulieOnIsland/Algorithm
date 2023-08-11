package live.algorithm.basic;

import java.util.Arrays;
import java.util.Scanner;

/**
 * BJ 3040 백설공주와일곱난쟁이
 * @author leejuhyun
 * 
 * Combination: 9 난쟁이 중 7 난쟁이 선택. 모자의 합이 100이 되는 조합.
 * 
 * Memory: 17772kb
 * Time: 208ms
 */

public class BJ_3040_백설공주와일곱난쟁이 {

	static int total;
	static int[] hats, ans, selected;
	
	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		hats = new int[9];
		selected = new int[7];
		ans = new int[7];
		
		for (int i = 0; i < 9; i++) {
			hats[i] = sc.nextInt();
		}
		
		comb(0, 0);
		
		// print
		for (int element: ans) {
			System.out.println(element);
		}
		
	}
	
	private static void comb(int cnt, int start) {
		if (cnt == 7) {
//			System.out.println(Arrays.toString(selected));
			int temp = 0;
			for (int i = 0; i < 7; i++) {
				temp += selected[i];
			}
			if (temp == 100) {
				ans = Arrays.copyOf(selected, selected.length);
			}
			return;
		}
		
		for (int i = start; i < 9; i++) {
			selected[cnt] = hats[i];
			comb(cnt + 1, i + 1);
		}

	}

}
