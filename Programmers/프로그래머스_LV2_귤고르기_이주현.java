package com.practice.makes.perfect;

import java.util.Arrays;

public class 프로그래머스_LV2_귤고르기_이주현 {

	 public static int solution(int k, int[] tangerine) {
         int maxNum = 0;

        for (int i = 0; i < tangerine.length; i++) {
            if (tangerine[i] > maxNum) {
                maxNum = tangerine[i];
            }
        }

        int[] count = new int[maxNum + 1];

        for (int i = 0; i < tangerine.length; i++) {
            count[tangerine[i]] += 1;
        }

        Arrays.sort(count);
        int answer = 0;
        int temp = 0;

        for (int i = count.length - 1; i >= 0; i--) {
            temp += count[i];
            answer++;
            if (temp >= k) {
                break;
            }
        }

        return answer;
    }
	 
	 public static void main(String[] args) {
	        int k = 6;
	        int[] tangerine = { 1, 3, 2, 5, 4, 5, 2, 3 };
	        int result = solution(k, tangerine);
	        System.out.println("Answer: " + result);
	    }
	 

}
