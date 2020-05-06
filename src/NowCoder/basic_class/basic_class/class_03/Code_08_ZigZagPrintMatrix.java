package NowCoder.basic_class.basic_class.class_03;

/*
	之字形打印矩阵：
		 A B 两点，分别往右和往下移动，每次移动一格，打印一次对角线
		右上 --> 左下
		左下 --> 右上
		移动到头，A往下，B往右
 */
public class Code_08_ZigZagPrintMatrix {

	public static void printMatrixZigZag(int[][] matrix) {
		// A
		int AR = 0;
		int AC = 0;
		// B
		int BR = 0;
		int BC = 0;
		int endR = matrix.length - 1;
		int endC = matrix[0].length - 1;
		boolean fromUp = false;
		while (AR != endR + 1) {
			printLevel(matrix, AR, AC, BR, BC, fromUp);
			AR = AC == endC ? AR + 1 : AR;
			AC = AC == endC ? AC : AC + 1;
			BC = BR == endR ? BC + 1 : BC;
			BR = BR == endR ? BR : BR + 1;
			fromUp = !fromUp;
		}
		System.out.println();
	}

	// 打印对角线
	public static void printLevel(int[][] m, int AR, int AC, int BR, int BC,
			boolean f) {
		if (f) { // 右上 --> 左下
			while (AR != BR + 1) {
				System.out.print(m[AR++][AC--] + " ");
			}
		} else { // 左下 --> 右上
			while (BR != AR - 1) {
				System.out.print(m[BR--][BC++] + " ");
			}
		}
	}

	public static void main(String[] args) {
		int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } };
		printMatrixZigZag(matrix);

	}

}
