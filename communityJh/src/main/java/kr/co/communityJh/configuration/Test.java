package kr.co.communityJh.configuration;

public class Test {
	public static void main(String[] args) {
		String new_id = "...!@BaT#*..y.abcdefghijklm.";
		String anwser = "";
		// 1단계
		anwser = new_id.toLowerCase();
		int pageable = 14;
//		if(pageable / 5 <= 1 && pageable % 5 == 0) {
//			
//			System.out.println(pageable);
//		}
//		System.out.println(pageable % 5);
		System.out.println(pageable - 5 <= 0 ? 0 : ((pageable) / 5) * 5);
		System.out.println(anwser);
		
		// 2단계
		anwser = anwser.replaceAll("[^a-z0-9-_.]", "");
		System.out.println(anwser);
		
		// 3단계
		anwser = anwser.replaceAll("[.]+", ".");
		System.out.println(anwser);
		
		// 4단계
		anwser = anwser.startsWith(".") ? anwser.substring(1) : anwser;
		anwser = anwser.endsWith(".") ? anwser.replaceAll(".$", "") : anwser;
		System.out.println(anwser);
		
		// 5단계
		anwser = anwser.equals("") ? "a" : anwser;
		System.out.println(anwser);
		
		// 6단계
		anwser = anwser.length() >= 16 ? anwser.substring(0, 15) : anwser;
		anwser = anwser.endsWith(".") ? anwser.replaceAll(".$", "") : anwser;
		System.out.println(anwser);
		
		// 7단계
		if(anwser.length() <= 2) {
			String temp = anwser.substring(anwser.length()-1);
			while(anwser.length() < 3) {
				anwser += temp;
			}
		}
//		result = result.length() <= 2 ? result.substring(0, 15) : result;
		System.out.println(anwser);
		
		
	}
}
