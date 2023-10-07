package com.obsession;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * BJ 1786 찾기 (패턴매칭 문제) 
 * @author leejuhyun
 * 
 * KMP 알고리즘 이용 
 * Memory: 89316kb
 * Time: 3076ms
 */

public class BJ_1786_찾기 {

	static String T, P;
	static int M, N;
	static List<Integer> list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = br.readLine();
//		System.out.println(T);
		
		P = br.readLine();
//		System.out.println(P);

		list = new ArrayList<>();
		
		kmp(T, P);
		
		// 정답 출력 
		System.out.println(list.size()); // T 중간에 P가 몇 번 나타나는지 
		for (int element: list) {
			System.out.print(element+" ");
		}
		

	}
	
	public static int[] getPi(String pt) {
		// 접두사와 접미사가 일치하는 최대 길이를 담은 배열 만들기 
		int[] pi = new int[pt.length()]; // 0 ~ i까지의 부분 문자열에서 접두사와 접미사가 일치하는 길이를 담는다 
		
		int j = 0; // 현재 여기까지는 같다는 의미 
		
		for (int i = 1; i < pt.length(); i++) { // i는 무조건 증가 	// i, j가 가리키는 값이 같다면 둘 다 증가 
			// i, j가 다르다면 
			while (j>0 && pt.charAt(i) != pt.charAt(j)) {
				j = pi[j-1];
			}
			
			// i, j가 같다면 
			if (pt.charAt(i) == pt.charAt(j)) pi[i] = ++j; // i번째 최대 길이는 ++j값을 저장한다  
		}
		
		return pi;
	}
	
	public static void kmp(String text, String pt) {
		int[] pi = getPi(pt); // 점프 테이블 
//		System.out.println(Arrays.toString(pi));
		
		int j = 0; // j : 패턴 내에서 움직이는 인덱스 
		for (int i = 0; i < text.length(); i++) {  // i : 본문의 인덱스 (i는 하나씩 쭉 증가, j가 점프를 뛰면서 본문과 패턴이 일치하는지 체크)
			// 패턴 위치와 텍스트의 값이 다르다면 점프!
			while (j>0 && text.charAt(i) != pt.charAt(j)) {
				j = pi[j-1];
			}
			
			// 내가 보고 있는 패턴 위치와 텍스트의 값이 같다면 
			if (text.charAt(i) == pt.charAt(j)) {
				if (j == pt.length()-1) {
					// 패턴을 찾았다
//					System.out.println((i-j)+"번째 인덱스에서 패턴을 찾았습니다.");
					list.add(i-j+1); 
					j = pi[j]; // 그 다음 매칭되는 패턴을 찾기 위해 j 이동시키기 
				} else {
					// 패턴의 길이까지 오지 않았다면 계속 진행시켜!
					j++;
				}
			}
		}
	}

}
