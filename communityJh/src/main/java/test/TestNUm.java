package test;

public class TestNUm {
	public static void main(String[] args) {
		System.out.println(solution(235386));
		
//		String S = "abcdcba";
//		String S = "abcba";
//		StringBuffer sb = new StringBuffer();
//
//		for (int i = S.length(); i > 0; i--) { // 7
//			for (int j = 0; j + i <= S.length(); j++) { // 0
//				boolean f = true;
//				for (int k = 0; k < i / 2; k++) { // 0 1 2
//					if (S.charAt(j + k) != S.charAt(j + i - k - 1)) {
//						f = false;
//						break;
//					}
////					System.out.println(S.charAt(i-1));
//				}
//				if (f) {
//					System.out.println(i);
//					break;
//				}
//			}
////			sb.append(S.substring(i, i+1));
////			sb.reverse();
////			if( !sb.toString().contains(S.substring(i+1)) ) {
////				sb.setLength(0);
////			}
////			System.out.println(S.substring(i+1));
//		}
	}

	public static int solution(int num) {
		int answer = num;
		while (true) {
			int firstMulti = 1;
			int EndMulti = 1;
			String tmp = answer + "";
			if (tmp.length() % 2 != 0) {
				answer++;
				continue;
			}
			firstMulti = multiple(tmp.substring(0, (tmp.length() / 2)));
			EndMulti = multiple(tmp.substring((tmp.length() / 2), tmp.length()));
			if (firstMulti == EndMulti) {
				break;
			}
			answer++;
		}
		return answer;
	}

	static int multiple(String s) {
		int result = 1;
		for (int i = 0; i < s.length(); i++) {
			result *= Integer.parseInt(s.substring(i, i + 1));
		}
		return result;
	}
}
