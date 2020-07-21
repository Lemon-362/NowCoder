package NowCoder.advanced_class.code.class_7;

/*
	纸牌博弈：
		给定一个整型数组arr，代表数值不同的纸牌排成一条线。
		玩家A和B依次拿走纸牌，规定A先，B后，但每个玩家只能拿走最左或最有的纸牌
		假设都绝顶聪明，都为了赢而拿，问最后获胜者的分数是多少？
	例：arr = [1, 2, 100, 4]
		返回101
 */
public class Code_03_CardsInLine {

	// 暴力递归
	public static int win1(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int f = f(arr, 0, arr.length - 1);
		int s = s(arr, 0, arr.length - 1);
		return Math.max(f, s);
	}

	public static int f(int[] arr, int i, int j) {
		if (i == j) {
			return arr[i];
		}
		int a = arr[i] + s(arr, i + 1, j);
		int b = arr[j] + s(arr, i, j - 1);
		return Math.max(a, b);
	}

	public static int s(int[] arr, int i, int j) {
		if (i == j) {
			return 0;
		}
		return Math.min(f(arr, i + 1, j), f(arr, i, j - 1));
	}

	// 动态规划
	public static int win2(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int[][] f = new int[arr.length][arr.length];
		int[][] s = new int[arr.length][arr.length];
		for (int j = 0; j < arr.length; j++) {
			f[j][j] = arr[j];
			for (int i = j - 1; i >= 0; i--) {
				f[i][j] = Math.max(arr[i] + s[i + 1][j], arr[j] + s[i][j - 1]);
				s[i][j] = Math.min(f[i + 1][j], f[i][j - 1]);
			}
		}
		return Math.max(f[0][arr.length - 1], s[0][arr.length - 1]);
	}

	public static void main(String[] args) {
		int[] arr = { 1, 2, 100, 4};
		System.out.println(win1(arr));
		System.out.println(win2(arr));

	}

}
