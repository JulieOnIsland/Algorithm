package com.practice.makes.perfect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * SWEA D3 1234 비밀번호
 * @author leejuhyun
 * 
 * 좌우의 번호가 같으면 번호쌍을 소거해서 비밀번호를 만드는 문제.
 * 스택 자료구조 사용. 
 * 
 * Memory: 17440kb
 * Time: 103ms
 */

public class D3_1234_비밀번호 {

	static Stack<Character> stack;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		for (int idx = 1; idx <= 10; idx++) {
			stack = new Stack<>();
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			String input = st.nextToken();
			
			stack.add(input.charAt(0));
			for (int i = 1; i < n; i++) {
				if (stack.isEmpty()) { // 만약 스택이 비어있다면, 
					stack.add(input.charAt(i)); // 원소를 추가해준다 
				} else if (stack.peek() == input.charAt(i)) {  // 스택을 슬쩍 봐서 맨 위에 있는 숫자와 이제 들어올 숫자가 같으면 
					stack.pop(); // 스택에 있는 그 숫자를 꺼낸다. 이렇게 하면 붙어있는 같은 번호쌍을 삭제할 수 있다. 
				} else {
					stack.add(input.charAt(i)); // 그렇지 않으면, 스택에 넣는다. 
				}
			}
			
			sb.append("#"+idx+" ");
			for (char e: stack) {
				sb.append(e);
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
		

	}

}
