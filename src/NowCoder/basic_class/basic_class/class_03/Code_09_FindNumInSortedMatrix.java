package NowCoder.basic_class.basic_class.class_03;

/*
	在有规律的矩阵中找数：矩阵行、列是递增的
		从右往左，从右上角开始
		1  3  5  6
		2  5  7  9
		4  6  8  10
		num = 4
		num < 6 ：该列都不存在，往左走，列--
		num < 5 ：该列都不存在，往左走，列--
		num > 3 ：3这一行左边的数都不可能存在，该列可能存在，往下走，行++
		num < 5 ：5往下的该列都不存在，往左走，列--
		num > 2 ：2往下的列可能存在，往下走，行++
		num == 4
 */
public class Code_09_FindNumInSortedMatrix {

	public static boolean isContains(int[][] matrix, int K) {
		int row = 0;
		int col = matrix[0].length - 1;
		while (row < matrix.length && col > -1) {
			if (matrix[row][col] == K) {
				return true;
			} else if (matrix[row][col] > K) {
				col--;
			} else {
				row++;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		int[][] matrix = new int[][] { { 0, 1, 2, 3, 4, 5, 6 },// 0
				{ 10, 12, 13, 15, 16, 17, 18 },// 1
				{ 23, 24, 25, 26, 27, 28, 29 },// 2
				{ 44, 45, 46, 47, 48, 49, 50 },// 3
				{ 65, 66, 67, 68, 69, 70, 71 },// 4
				{ 96, 97, 98, 99, 100, 111, 122 },// 5
				{ 166, 176, 186, 187, 190, 195, 200 },// 6
				{ 233, 243, 321, 341, 356, 370, 380 } // 7
		};
		int K = 233;
		System.out.println(isContains(matrix, K));
	}

}
