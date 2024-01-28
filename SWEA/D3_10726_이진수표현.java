package solving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * D3_10726_이진수표현
 * 정수 과 이 주어질 때 M의 이진수 표현의 마지막 N 비트가 모두 1로 켜져 있는지 아닌지를 판별하기 
 * 
 * AND 연산자 활용 
 * 
 * Memory: 36,216kb, Time: 206ms
 */

public class D3_10726_이진수표현 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testcase = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= testcase; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			String ans = "";
			int judge = (1<<N)-1; // 맨 오른쪽서부터 N개의 비트를 1로 set
			if ((M & (judge)) == judge) { 
				// judge와 M을 AND 연산자로 비교하는데 이것이 judge와 같다면, 마지막 N개의 비트가 모두 1이다  
				ans = "ON";
			} else {
				ans  = "OFF";
			}
			System.out.println("#"+tc+" "+ans);
			
		}
		
	}

}
