import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * D3 3307 최장증가부분수열
 * @author leejuhyun
 * 
 * DP로 해결
 * 1. 현재 체크하는 원소 왼쪽에서 자기보다 작은 수 찾기
 * 2. 1번 원소 중에 LIS 값이 가장 큰 것+1로 LIS 설정
 * 
 * Memory: 24,176kb, Time: 155ms
 */

public class D3_3307_최장증가부분수열 {

	public static void main(String[] args) throws IOException {
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 StringTokenizer st = new StringTokenizer(br.readLine());
		 
		 int T = Integer.parseInt(st.nextToken());
		 for (int idx = 1; idx <= T; idx++) {
			 st = new StringTokenizer(br.readLine());
			 int N = Integer.parseInt(st.nextToken());
			 int[] array = new int[N+1];
			 int[] LIS = new int[N+1];
			 st = new StringTokenizer(br.readLine());
			 for (int i = 1; i <= N; i++) {
				array[i] = Integer.parseInt(st.nextToken());
			}
			 System.out.println(Arrays.toString(array));
			 
			 
			 for (int i = 1; i <= N; i++) { // 원소 하나씩 체크
				LIS[i] = 1; // 제일 처음에 1로 세팅
				for (int j = 1; j <= i-1; j++) { // i보다 왼쪽에 있는 수 체크
					if (array[j] < array[i] && LIS[i] < LIS[j] + 1) { // 배열에서 i번째보다 값이 작으면서 LIS 값 + 1이 더 크다면 
						LIS[i] = LIS[j]+1; // 값 업데이트
					}
				}
			}
			// System.out.println(Arrays.toString(LIS));
			 
			 // LIS 배열에서 max 값 찾기 => 최장 증가 부분 수열의 길이
			 int max = 0;
			 for (int i = 0; i < LIS.length; i++) {
				max = Math.max(max, LIS[i]);
			}
			 
			 System.out.println("#"+idx+" "+max);
		}
	}
}
