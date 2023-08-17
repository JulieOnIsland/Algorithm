package live.algorithm.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * D5 1247 최적경로
 * @author leejuhyun
 * 
 * Memory: 23,604kb
 * Time: 2,698ms
 */

public class D5_1247_최적경로 {
	
	static int n;
	static int[] company, home;
	static List<int[]> customer;
	static int[] selectedArray;
	static boolean[] isSelected;
	static int minDist;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int test_case = sc.nextInt();
		
		for (int idx = 1; idx <= test_case; idx++) {
			n = sc.nextInt(); // 고객의 수
			company = new int[2];
			for (int i=0; i<company.length; i++) {
				company[i] = sc.nextInt();
			}
			home = new int[2];
			for (int i=0; i<home.length; i++) {
				home[i] = sc.nextInt();
			}
			customer = new ArrayList<>();
			for (int i=0; i<n; i++) {
				int[] temp = new int[2];
				for (int j=0; j<2; j++) {
					temp[j] = sc.nextInt();
				}
				customer.add(temp);
			}

			// print customer
//			for (int[] element: customer) {
//				System.out.println(Arrays.toString(element));
//			}
			
			selectedArray = new int[n];
			isSelected = new boolean[n];
			minDist = Integer.MAX_VALUE;
			
			dfs(0);
			System.out.println("#"+idx+" "+minDist);
		}
	

	}
	
	private static void dfs(int cnt) {
		if (cnt == n) { 
			int dist = 0;
//			selectedArray {0 1 2 3 4} {0 1 2 4 3}
			dist += Math.abs(company[0] - customer.get(selectedArray[0])[0]) + Math.abs(company[1] - customer.get(selectedArray[0])[1]);
			for (int i=1; i<customer.size(); i++) {
				dist += Math.abs(customer.get(selectedArray[i-1])[0] - customer.get(selectedArray[i])[0]) + 
						Math.abs(customer.get(selectedArray[i-1])[1] - customer.get(selectedArray[i])[1]);
			}
			dist += Math.abs(home[0] - customer.get(selectedArray[n-1])[0]) + Math.abs(home[1] - customer.get(selectedArray[n-1])[1]);
			if (dist < minDist) {
				minDist = dist;
			}
			
			return;
		}
		
		for (int i=0; i<n; i++) {
			if (isSelected[i]) {
				continue;
			}
			
			isSelected[i] = true;
			selectedArray[cnt] = i;  // 0 1 2 3 4 or 0 1 2 4 3
			dfs(cnt + 1);
			isSelected[i] = false;
		}

	}

}



















