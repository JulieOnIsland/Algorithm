package live.algorithm.basic;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;

/**
 * BJ 11866 요세푸스문제
 * @author leejuhyun
 * 
 * Queue
 */

public class BJ_11866_요세푸스문제 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int n = sc.nextInt();
		int k = sc.nextInt();
		int[] result = new int[n];
		int resultIdx = 0;
		int pos = 0;
		int num = 1;
		
		Queue<Integer> queue = new ArrayDeque<>();
		
		for (int i=1; i<=n; i++) {
			queue.offer(i);
		}
		
		while (!queue.isEmpty()) {
			pos++; 
			if (pos % k == 0) {
				result[resultIdx++] = queue.poll();
		
			} else {
				queue.offer(queue.poll());
				
			}
		}
		
//		System.out.println(Arrays.toString(result));
		sb.append("<");
	
		for (int i=0; i<result.length;i++) {
			if (i==result.length-1) {
				sb.append(result[i]);
			} else{
				sb.append(result[i]+", ");
			}
		}
		sb.append(">");
		System.out.println(sb.toString());
		
	}

}
