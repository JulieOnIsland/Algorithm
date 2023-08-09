package bruteforce;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 
 * @author leejuhyun
 *
 * Priority Queue 이용.
 * 절댓값이 작은 순서에서 큰 순서로 정렬을 하는데
 * 출력을 할 때 절대값이 같아도 작은 수 (즉 음수)를 출력해야 한다.
 * 그러므로 우선순위큐에 저장을 할 때 크기가 2인 배열(절댓값, 원래값)을 저장해주었고,
 * 객체를 정렬하는 비교 기준을 재정의하였다. 
 *
 * Memory: 106980kb
 * Time: 1436ms
 */

public class BJ_11286_절대값힙 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		PriorityQueue<int[]> pqueue = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if (o1[0] == o2[0]) {  // 2차원 배열의 첫 번째 원소가 같다면, 
					return Integer.compare(o1[1], o2[1]);    // 2번째 원소를 기준으로 오름차순 정렬
				}
				return Integer.compare(o1[0], o2[0]); // 2차원 배열의 첫 번째 원소를 기준으로 오름차순 정렬
			}
		}); 
		
		int n = sc.nextInt();
		
		for (int i = 0; i < n; i++) {
			int temp = sc.nextInt();
			if (temp == 0) {
				if (pqueue.isEmpty()) {
					System.out.println(0);
				} else {
					int[] check = pqueue.poll(); 
					System.out.println(check[1]);
				}
				
			} else {
				pqueue.offer(new int[] {Math.abs(temp), temp});
			}
			
		}
		
	}

}
