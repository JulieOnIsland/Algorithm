package javaBasic;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.Scanner;

/**
 * D3 1225 암호생성기 이주현
 * @author leejuhyun
 *
 * Using ArrayDeque
 * 
 * Memory: 24524kb, Time: 157ms
 */

public class D3_1225_암호생성기_이주현 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Deque<Integer> deque = new ArrayDeque<>();

		for (int idx = 0; idx < 10; idx++) { // test_case
			int test_case = sc.nextInt();
			int cnt = 1;  
			
			// Make a deque
			for (int i=0; i<8; i++) {
				int n = sc.nextInt();
				deque.add(n);
			}
			
			int first = 0; // First number in a deque
			
			while (true) {
				first = deque.getFirst();
				if (cnt % 5 == 0) { // 5 감소
					if (first-(cnt%5+5) > 0) { // 양수라면
						deque.pollFirst();   // Remove the first element of the deque
						deque.addLast(first-5);   // Insert the element at the end of the deque
						cnt++;
					} else { // 음수가 되면 프로그램 종료
						deque.pollFirst();
						deque.addLast(0);
						break;
					}
					
				} else {  // 1 ~ 4 감소
					if (first-(cnt%5) > 0) {
						deque.pollFirst();
						deque.addLast(first-(cnt%5));
						cnt++;
					} else {
						deque.pollFirst(); 
						deque.addLast(0);
						break;
					}
				}
			}
			
			// Print out using iterator
			System.out.printf("#%d ", test_case);
			Iterator<Integer> iterator = deque.iterator();
			while (iterator.hasNext()) {
				System.out.print(iterator.next()+" ");
			}
		
			deque.clear();
		}
	}

}
