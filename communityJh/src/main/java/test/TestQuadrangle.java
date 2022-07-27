package test;

public class TestQuadrangle {
	public static void main(String[] args) {
		int[][] quadrangle = {{1, 4}, {3, 4}, {3, 10}};
		int[] answer = new int[2];
		
		// x축
		if(quadrangle[0][0] == quadrangle[1][0]) {
			answer[0] = quadrangle[2][0];
		} else if(quadrangle[0][0] == quadrangle[2][0]) {
			answer[0] = quadrangle[1][0];
		} else if(quadrangle[1][0] == quadrangle[2][0]) {
			answer[0] = quadrangle[0][0];
		}
		
		// y축
		if(quadrangle[0][1] == quadrangle[1][1]) {
			answer[1] = quadrangle[2][1];
		} else if(quadrangle[0][1] == quadrangle[2][1]) {
			answer[1] = quadrangle[1][1];
		} else if(quadrangle[1][1] == quadrangle[2][1]) {
			answer[1] = quadrangle[0][1];
		}
		
		System.out.println(answer[0]);
		System.out.println(answer[1]);
		
	}
}
