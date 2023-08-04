package javaBasic;

import java.util.Deque;
import java.util.ArrayDeque;
import java.util.Scanner;

/**
 * BJ 2164 카드2
 * @author leejuhyun
 *
 * Queue / Deque / ArrayDeque 
 */

public class BJ_2164_카드2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Deque<Integer> deque = new ArrayDeque<>();

		int n = sc.nextInt();

		for (int i=1; i <= n; i++) {
			deque.add(i);
		}

		
		while (deque.size() > 1) {
			deque.pollFirst();
			deque.offerLast(deque.getFirst());
			deque.pollFirst();
		}
		
		System.out.println(deque.getFirst());
		
	}  

}  
 