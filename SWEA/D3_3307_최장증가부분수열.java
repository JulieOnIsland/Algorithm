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
			 
			 
			 for (int i = 1; i <= N; i++) {
				LIS[i] = 1;
				for (int j = 1; j <= i-1; j++) {
					if (array[j] < array[i] && LIS[i] < LIS[j] + 1) {
						LIS[i] = LIS[j]+1;
					}
				}
			}
			// System.out.println(Arrays.toString(LIS));
			 
			 int max = 0;
			 for (int i = 0; i < LIS.length; i++) {
				max = Math.max(max, LIS[i]);
			}
			 
			 System.out.println("#"+idx+" "+max);
		}
		

	}

}
