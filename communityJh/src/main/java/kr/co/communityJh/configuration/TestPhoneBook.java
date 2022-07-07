package kr.co.communityJh.configuration;

import java.util.HashMap;

public class TestPhoneBook {
	public static void main(String[] args) {
		System.out.println(phone());
	}
	
	
	static boolean phone() {
		String[] phone_book = {"123","456","789"};
//		String[] phone_book = {"119", "97674223", "1195524421"};
//		String[] phone_book = {"12","123","1235","567","88"};
//		System.out.println(Arrays.binarySearch(phone_book, "881"));
		boolean answer = true;
		HashMap<String, Integer> map = new HashMap<>();
		for (int i = 0; i < phone_book.length; i++) {
			map.put(phone_book[i], i);
		}
		for (String number : phone_book) {
			for (int i = 0; i < number.length(); i++) {
				if(map.containsKey(number.substring(0, i))) {
					return false;
				}
			}
		}

//		for (int i = 0; i < phone_book.length; i++) {
//			String tmp = phone_book[i];
//			for (int j = 0; j < phone_book.length; j++) {
//				if(phone_book[j].startsWith(tmp)) {
//					if(i == j)
//						continue;
//					return false;
//				}
//			}
//		}
		return answer;
		
	}
}