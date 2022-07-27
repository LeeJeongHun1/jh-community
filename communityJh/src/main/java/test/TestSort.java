package test;

import java.util.Arrays;

public class TestSort {
	public static void main(String[] args) {
		int[] array = { 1, 5, 2, 6, 3, 7, 4 };
		int[][] commands = { { 2, 5, 3 }, { 4, 4, 1 }, { 1, 7, 3 } };
		int[] answer = new int[commands.length];
		int len = 0;
		for (int[] ia : commands) {
//			System.out.println(Arrays.toString(ia));
			int tmp[] = splitArr(array, ia[0], ia[1]);
			insertoinSort(tmp);
			System.out.println(Arrays.toString(tmp));
			answer[len++] = tmp[ia[2]-1];
//			for (int i = 0; i < ia.length; i++) {
//			}
		}
		System.out.println(Arrays.toString(answer));
	}

	private static int[] splitArr(int[] arr, int start, int end) {
		int[] tem = new int[end - start + 1];
		for (int i = 0; i < tem.length; i++) {
			tem[i] = arr[start-1];
			start++;
		}
		return tem;
	}

	private static void insertoinSort(int[] arr) {
		for (int i = 1; i < arr.length; i++) {
			int idx = i;
			int tmp = arr[i];
			while (idx > 0 && arr[idx - 1] > tmp) {
				// swap
				arr[idx] = arr[idx - 1];
				idx--;
			}
			arr[idx] = tmp;
		}
	}
}