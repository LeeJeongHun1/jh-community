package kr.co.communityJh.configuration;

import java.util.HashMap;

public class TestTimer {
	public static void main(String[] args) {
		long answer = 0;
		int n = 6;
		int timer = 1;
		int[] times = {7, 10};
		HashMap<Integer, Boolean> map = new HashMap<Integer, Boolean>();
		for (int i : times) {
			map.put(i, true);
			n--;
		}
		while(true) {
			map.forEach((t, u) -> {
				if(u) {
					
				}
			});
		}
	}
}
