import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BJ 10971 외판원순회2
 * @author leejuhyun
 * 
 * Memory: 16340kb
 * Time: 368ms
 */

public class BJ_10971_외판원순회2 { // 순열
	
	static int N, grid[][], ans;
	static int order[];
	static boolean isSelected[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		grid = new int[N][N];
		order = new int[N+1];
		isSelected = new boolean[N+1];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		ans = Integer.MAX_VALUE;
		perm(0);
		System.out.println(ans);

	}
	
	private static void perm(int cnt) {
		if (cnt == N) { 
//			System.out.println(Arrays.toString(order));
			order[N] = order[0];
//			System.out.println(Arrays.toString(order));
			int temp_cost = 0;
			for (int i = 0; i < order.length-1; i++) {
				int from = order[i]; 
				int to = order[i+1];
				if (grid[from-1][to-1] == 0) return; // 문제에서 W[i][j] = 0 일 때는 갈 수 없는 경우라고 했으므로 리턴
				temp_cost += grid[from-1][to-1];
				if (temp_cost > ans) return; // 현재 정답(ans)보다 temp_cost가 크면 pruning
			}
			
			if (ans > temp_cost) ans = temp_cost;
			
			return;
		}
		
		for (int i = 1; i <= N; i++) {
			if (isSelected[i]) continue;
			
			isSelected[i] = true;
			order[cnt] = i;
			perm(cnt+1);
			isSelected[i] = false;
		}

	}

}
