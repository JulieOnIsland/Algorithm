import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

/**
 * SWEA 5658 보물상자비밀번호
 * @author leejuhyun
 * 
 * 문제 : 회전으로 만들 수 있는 숫자를 모두 나열한 후 k번째로 큰 숫자 구하기
 * 각 변에는 동일한 개수의 숫자가 있다. N은 4의 배수이고, 8이상 28이하의 정수
 * 
 * Memory: 18392kb
 * Time: 110ms
 *
 */
public class SWEA_5658_보물상자비밀번호 {
	
	 private static class HexToIntConverter {
	        private static final String HEX_CHARACTERS = "0123456789ABCDEF";

	        private static int hexToInt(char hexChar) {
	            return HEX_CHARACTERS.indexOf(hexChar); // 인덱스로 숫자 나타내기
	        }
	    }

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int testCase = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= testCase; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); 
			int K = Integer.parseInt(st.nextToken()); 
			String str = br.readLine();

			HashSet<String> set = new HashSet<>(); // 숫자가 중복되면 안 되므로 집합 자료구조 사용.

			int rotateNum = N / 4; // 회전의 횟수. 몇 번 회전을 하면 제자리로 돌아오는지.
			
			for (int i = 0; i < rotateNum; i++) {
				for (int j = 0; j < N; j += rotateNum) {
					String substring = str.substring(j, j+rotateNum); // 문자열 쪼갠 후
					set.add(substring);  // 집합에 담기
				}
				str = rotate(str); // 회전 

			}
			
			List<Integer> list = new ArrayList<>();
			
			for (String e: set) { // 집합에 있는 문자열을 하나씩 꺼내서
				list.add(convertToInt(e)); // 정수로 바꾼 후에 list에 넣기
			}
			
			Collections.sort(list, Collections.reverseOrder()); // arraylist 정렬
			System.out.println("#"+tc+" "+list.get(K-1)); // K번째의 숫자가 답이다 (리스트에서 가져올 때는 K-1)
		}


	}
	
	private static Integer convertToInt(String inputString) {
		int value = 0;
		int length = inputString.length();
		
		for (int i = length-1; i>=0; i--) {
			char hexChar = inputString.charAt(i);
			int digitValue = HexToIntConverter.hexToInt(hexChar);
			value += digitValue * Math.pow(16, length-1-i);
		}
		return value;
	}

	private static String rotate(String string) { // 회전 -> 맨 뒤에 있는 문자를 가져오고, 그 다음에 나머지 문자열 붙이기
		int length = string.length();
		String rotated = string.substring(length-1) + string.substring(0, length-1);
		return rotated;

	}

}
