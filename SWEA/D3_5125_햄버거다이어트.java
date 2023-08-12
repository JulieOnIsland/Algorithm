package com.practice.makes.perfect;

import java.util.Scanner;

/**
 * SWEA D3 5125 햄버거다이어트
 * @author leejuhyun
 * 
 * Depth First Search & Backtracking
 * 
 * Memory: 24,004kb
 * Time: 211ms
 */

public class D3_5125_햄버거다이어트 {

	static int[] score, kcal;
	static int n, limit, maxScore;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int test_case = sc.nextInt();
		
		for (int idx = 1; idx <= test_case; idx++) {
			n = sc.nextInt();
			limit = sc.nextInt();
			
			score = new int[n];
			kcal = new int[n];
			
			maxScore = 0;
			
			for (int i = 0; i < n; i++) {
				score[i] = sc.nextInt();
				kcal[i] = sc.nextInt();
			}
			
			subset(0, 0, 0);
			
			System.out.println("#"+idx+" "+maxScore);
		}
		
	}
	
	private static void subset(int cnt, int sc, int cal) {
		if (cal > limit) {  // pruning
			return;
		}
		
		if (cnt == n) {
			if (sc > maxScore) {
				maxScore = sc;
			}
			return;
		}
		
		subset(cnt + 1, sc + score[cnt], cal + kcal[cnt]);
		subset(cnt + 1, sc, cal);
	
	}

}


/* Code w/o pruning
Memory: 24992kb
Time: 792ms

import java.util.Arrays;
import java.util.Scanner;
 
public class Solution {
     
    static int n, limit, finalScore;
    static int[] ingreScore;
    static int[] calories;
    static boolean[] isSelected;
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
         
        int test_case = sc.nextInt();
         
        for (int idx = 1; idx <= test_case; idx++) {
            n = sc.nextInt(); // 재료의 개수
            limit = sc.nextInt(); // 제한 칼로리
             
            ingreScore = new int[n];
            calories = new int[n];
            isSelected = new boolean[n];
            finalScore = 0;
             
            for (int i = 0; i < n; i++) {
                ingreScore[i] = sc.nextInt();
                calories[i] = sc.nextInt();
            }
             
            subset(0);
             
            System.out.println("#"+idx+" "+finalScore);
             
        }
 
    }
     
    private static void subset(int cnt) {
        if (cnt == n) {
            int score = 0; int kcal = 0;
            for (int i = 0; i < n; i++) {
                if (isSelected[i]) {
                    score += ingreScore[i];
                    kcal += calories[i];
                }
            }
            if (kcal <= limit && score > finalScore) {
                finalScore = score;
            } 
            return;
        }
         
        isSelected[cnt] = true;
        subset(cnt + 1);
        isSelected[cnt] = false;
        subset(cnt + 1);
         
    }
 
}

 */









