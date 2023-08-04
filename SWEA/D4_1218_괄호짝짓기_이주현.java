package javaBasic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * SWEA D4 1218 괄호짝짓기 이주현
 * @author leejuhyun
 *
 * Stack
 * Memory: 18600kb, Time: 111ms
 */

public class D4_1218_괄호짝짓기_이주현 {

	public static void main(String[] args) throws IOException {
		Stack<Character> stack = new Stack<>();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null; // 선언과 동시에 초기화
		for (int idx = 1; idx <= 10; idx++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			
			char[] arr = br.readLine().toCharArray(); // char 하나씩 가져올 수 있음 
			StringBuilder sb = new StringBuilder();

			int result = 1;
			for (int i=0; i<arr.length; i++) {
				if (arr[i] == '(' || arr[i] == '{' || arr[i] == '<' || arr[i] == '[') {
					stack.push(arr[i]);
				} else { // arr[i] == ')' || arr[i] == '}' || arr[i] == '>' || arr[i] == ']'
					if (arr[i] == ')' && stack.pop() != '(') {
						result = 0;
						break;
					} else if (arr[i] == '}' && stack.pop() != '{') {
						result = 0;
						break;
					} else if (arr[i] == '>' && stack.pop() != '<') {
						result = 0;
						break;
					} else if (arr[i] == ']' && stack.pop() != '[') {
						result = 0;
						break;
					}
					}
				}
			if (!stack.isEmpty()) {
				result = 0;
			}
			System.out.printf("#%d %d", idx, result);
			System.out.println();
			stack.clear();
		}
	}
}

