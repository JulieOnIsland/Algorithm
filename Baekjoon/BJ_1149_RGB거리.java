import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BJ 1149 RGB거리
 * @author leejuhyun
 *
 * DP
 * 맨 처음에 집을 색칠하고, 그 다음을 보는 것이 아니라
 * 두 번째 집을 선택하고, 첫 번째 집을 다른 색으로 색칠하는 방식으로 생각하기.
 * 순서대로 가는 게 아닌, 뒤에서 앞을 보는 방식으로. 거꾸로 생각하기!
 * SWEA D2 백만 장자 프로젝트 라는 문제가 떠오름.
 * 
 * Memory: 14564kb
 * Time: 144ms
 */

public class BJ_1149_RGB거리 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[][] color = new int[N][3];
		int[] memo = new int[3];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				color[i][j] = Integer.parseInt(st.nextToken());
			}
		}
//		System.out.println(Arrays.deepToString(color));
		
		// 초기값
		for (int i=0; i<3; i++) {
			memo[i] = color[0][i];
		}
//		System.out.println(Arrays.toString(memo));
		
		for (int i = 1; i < N; i++) {
			
			for (int j=0; j<3; j++) {
				if (j==0) { // 빨강색일 때 
					int temp = Math.min(color[i-1][1], color[i-1][2]); // 그 윗줄(i-1번째)은 초록 or 파랑색으로 칠해야 하는데 그 중 최솟값을 temp에 저장 
					color[i][j] = color[i][j] + temp; // 그 줄(i번째)에는 빨강색의 비용과 그 윗줄의 초록/파랑색 비용(tmep)을 더해준 값으로 갱신한다 
				} else if (j==1) {
					int temp = Math.min(color[i-1][0], color[i-1][2]);
					color[i][j] = color[i][j] + temp;
				} else if (j==2) {
					int temp = Math.min(color[i-1][0], color[i-1][1]);
					color[i][j] = color[i][j] + temp;
				}
			}
		} 
//		System.out.println(Arrays.toString(color[N-1]));
		
		int ans = Math.min(color[N-1][0], Math.min(color[N-1][1], color[N-1][2]));  // 맨 마지막 줄에서 최솟값을 뽑는다
		System.out.println(ans);
	}

}
