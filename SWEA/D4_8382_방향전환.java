import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * D4 8382 방향전환
 * @author leejuhyun
 * 
 * 가로 이동, 세로 이동을 번갈아 가면서 (x1, y1) -> (x2, y2) 최소 이동 횟수
 * bfs로 풀되 3차원 방문배열을 사용. row, col, 가로/세로이동여부
 * 
 * Memory : 89,704kb
 * Time: 444ms
 */
public class D4_8382_방향전환 {
	
	static boolean visited[][][];
	static int x1, y1, x2, y2, answer;
	static int[][] dirh = {{1, 0}, {-1, 0}}; // horizontal
	static int[][] dirv = {{0, 1}, {0, -1}}; // vertical

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		
		for (int idx = 1; idx <= tc; idx++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			List<Integer> list = new ArrayList<>();
			
			answer = Integer.MAX_VALUE;
			
			for (int i = 0; i < 4; i++) {
				list.add(Integer.parseInt(st.nextToken()));
			}
			
			x1 = list.get(0)+100;
			y1 = list.get(1)+100;
			x2 = list.get(2)+100;
			y2 = list.get(3)+100;
			
			move();
			System.out.println("#"+idx+" "+answer);
		}
		

	}
	
	private static void move() {
		Queue<int[]> q = new ArrayDeque<>(); // x1, y1, dir(0/1:horizontal/vertical), distance 
		
		for (int j = 0; j < 2; j++) { // 제일 처음 가로 방향으로 시작해보고, 그 다음 세로 방향으로 시작해보기.
			visited = new boolean[201][201][2];  // 방문배열 만들기  // 오답 지점: new boolean[200][200][2]
			q.offer(new int[] {x1, y1, j, 0}); // 큐에 넣고
			visited[x1][y1][j] = true;  // 방문 처리
			
			while (!q.isEmpty()) {
				int[] current = q.poll();
				int dir = current[2]; 
				int dist = current[3];
				
				if (current[0] == x2 && current[1] == y2) { // (x2, y2)에 도달했다면
					if (answer > dist) answer = dist; // 최솟값 갱신
//					break; // 오답 지점: break 하면 안 됨. 더 작은 길이가 있을 수도 있으니 큐 안에 남아있는 걸 다 봐야 함.
				}
				
				if (dir == 0) { // 이전 움직임이 가로 이동이었다면, 그 다음엔 세로 이동하기
					for (int i = 0; i < 2; i++) { // vertical move
						int nx = current[0] + dirv[i][0];
						int ny = current[1] + dirv[i][1];
						if (check(nx, ny) && !visited[nx][ny][1]) {
							q.offer(new int[] {nx, ny, 1, dist+1});
							visited[nx][ny][1] = true;
						}
					}
				} else { // 이전 움직임이 세로 이동이었다면, 그 다음엔 가로 이동하기
					for (int i = 0; i < 2; i++) { // horizontal move
						int nx = current[0] + dirh[i][0];
						int ny = current[1] + dirh[i][1];
						if (check(nx, ny) && !visited[nx][ny][0]) {
							q.offer(new int[] {nx, ny, 0, dist+1});
							visited[nx][ny][0] = true;
						}
					}
				}
			}
		}
		

	}
	
	private static boolean check(int x, int y) {
		return 0<=x && x<=200 && 0<=y && y<=200;

	}

}
