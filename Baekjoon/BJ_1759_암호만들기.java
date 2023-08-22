package com.practice.makes.perfect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BJ_1759_암호만들기
 * 
 * 완전탐색 중 조합 이용 
 * 
 * Memory: 16228kb
 * Time: 184ms
 */

public class BJ_1759_암호만들기 {
	
	static int L, C;
	static char charArray[], selectedArray[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		charArray = new char[C];
		selectedArray = new char[L];
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<C; i++) {
			charArray[i] = st.nextToken().charAt(0);
		}
		Arrays.sort(charArray); // sorting
		comb(0, 0);

	}
	
	private static void comb(int cnt, int start) {
		// basis part 
		if (cnt == L) {
			int vowelCnt = 0;  // 모음 카운트 
			int consCnt = 0;   // 자음 카운트 
			for (int i=0; i<L; i++) {
				if (selectedArray[i]=='a'||selectedArray[i]=='e'||selectedArray[i]=='i'||
						selectedArray[i]=='o'||selectedArray[i]=='u') {
					vowelCnt++;
				} else consCnt++;
			}
			
			if (vowelCnt>=1&&consCnt>=2) {  // 모음이 1번 이상, 자음이 2번 이상 쓰였다면 
				for (int j=0; j<L; j++) {
					System.out.print(selectedArray[j]);  // 프린트 
				}
				System.out.println();
			}
			
			return;
		}
		
		// inductive part 
		for (int i=start; i<C; i++) {
			selectedArray[cnt] = charArray[i];
			comb(cnt + 1, i+1);
		}

	}

}
