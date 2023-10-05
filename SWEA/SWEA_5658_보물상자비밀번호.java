import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

/**
 * SWEA 5658 �������ں�й�ȣ
 * @author leejuhyun
 * 
 * ���� : ȸ������ ���� �� �ִ� ���ڸ� ��� ������ �� k��°�� ū ���� ���ϱ�
 * �� ������ ������ ������ ���ڰ� �ִ�. N�� 4�� ����̰�, 8�̻� 28������ ����
 * 
 * Memory: 18392kb
 * Time: 110ms
 *
 */
public class SWEA_5658_�������ں�й�ȣ {
	
	 private static class HexToIntConverter {
	        private static final String HEX_CHARACTERS = "0123456789ABCDEF";

	        private static int hexToInt(char hexChar) {
	            return HEX_CHARACTERS.indexOf(hexChar); // �ε����� ���� ��Ÿ����
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

			HashSet<String> set = new HashSet<>(); // ���ڰ� �ߺ��Ǹ� �� �ǹǷ� ���� �ڷᱸ�� ���.

			int rotateNum = N / 4; // ȸ���� Ƚ��. �� �� ȸ���� �ϸ� ���ڸ��� ���ƿ�����.
			
			for (int i = 0; i < rotateNum; i++) {
				for (int j = 0; j < N; j += rotateNum) {
					String substring = str.substring(j, j+rotateNum); // ���ڿ� �ɰ� ��
					set.add(substring);  // ���տ� ���
				}
				str = rotate(str); // ȸ�� 

			}
			
			List<Integer> list = new ArrayList<>();
			
			for (String e: set) { // ���տ� �ִ� ���ڿ��� �ϳ��� ������
				list.add(convertToInt(e)); // ������ �ٲ� �Ŀ� list�� �ֱ�
			}
			
			Collections.sort(list, Collections.reverseOrder()); // arraylist ����
			System.out.println("#"+tc+" "+list.get(K-1)); // K��°�� ���ڰ� ���̴� (����Ʈ���� ������ ���� K-1)
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

	private static String rotate(String string) { // ȸ�� -> �� �ڿ� �ִ� ���ڸ� ��������, �� ������ ������ ���ڿ� ���̱�
		int length = string.length();
		String rotated = string.substring(length-1) + string.substring(0, length-1);
		return rotated;

	}

}
