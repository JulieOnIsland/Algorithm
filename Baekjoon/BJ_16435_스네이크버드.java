package live.algorithm.basic;

import java.util.Arrays;
import java.util.Scanner;

/**
 * BJ 16435 스네이크버드
 * @author leejuhyun
 * 
 * 과일 높이의 배열을 정렬 후 스네이크버드의 초기 길이와 비교.
 * 
 * Memory: 20320kb
 * Time: 272ms
 *
 */

public class BJ_16435_스네이크버드 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt(); 
		int[] fruits = new int[n]; 
		int length = sc.nextInt();
		
		for (int i = 0; i < n; i++) {
			fruits[i] = sc.nextInt();
		}
		
		Arrays.sort(fruits);
//		System.out.println(Arrays.toString(fruits));
		
		for (int i = 0; i < fruits.length; i++) {
			if (length >= fruits[i]) {
				length++;
			}
		}
		
		System.out.println(length);
		
	}

}
