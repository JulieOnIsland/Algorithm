/**
 * 
 */
package algorithms;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author leejuhyun
 * SWEA no.1208: Flatten
 * 
 * memory: 33,788kb
 * execution time: 247ms
 * 
 */
public class swea_1208 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for(int idx=1; idx < 11; idx++) {
			int n = sc.nextInt(); // number of dumping
			int cnt = 0;
			int[] heights = new int[100];
			for (int i=0; i<100; i++) {
				heights[i] = sc.nextInt();
			}
			
			int max_height = Arrays.stream(heights).max().getAsInt();
			int min_height = Arrays.stream(heights).min().getAsInt();
			
			while (cnt < n) {
				if (max_height - min_height <= 1) {
					break;
				}
				int max_index = findIndex(heights, max_height);
				heights[max_index] -= 1;
				int min_index = findIndex(heights, min_height);
				heights[min_index] += 1;
				cnt += 1;
				
				max_height = Arrays.stream(heights).max().getAsInt();
				min_height = Arrays.stream(heights).min().getAsInt();		
			}
			int diff = max_height - min_height;
			System.out.printf("#%d %d", idx, diff);
			System.out.println();
		}
		
	

	}public static int findIndex(int[] arr, int target) {
		for (int i=0; i < arr.length; i++) {
			if (arr[i] == target) {
				return i;
			}
		}
		return -1;

	}

}
