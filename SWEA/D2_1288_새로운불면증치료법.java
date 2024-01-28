package solving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * D2 1288 새로운 불면증 치료법
 * @author leejuhyun
 * 
 * 0-9까지의 숫자가 나왔다는 것을 배열이 아닌 비트를 이용해서 해결.
 * 특히, 나온 숫자에 대해서 OR 연산을 활용해 bit 상태를 저장. 
 * 
 * Memory: 18,588kb, Time: 113ms
 * 
 */

public class D2_1288_새로운불면증치료법 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testcase = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= testcase; tc++) {
			int N = Integer.parseInt(br.readLine());
			int fullyVisited = (1<<10)-1; // 11111 11111 which represents 0 to 9
			int visited = 0;
			int count = 0;
			for (count = 1;; count++) {
				char[] result = String.valueOf(N * count).toCharArray(); // 1295 * 1 = 1295 -> "1295" -> ['1', '2', '9', '5']
				for (char c : result) {
					int number = c - '0';
					visited = visited | (1<<number); // OR operator: bit set
				}
				
				if (visited == fullyVisited) break;
			}
			
			System.out.println("#"+tc+" "+N*count);
		}

	}
	
}
