package NowCoder.basic_class.basic_class.class_08;

/*
	暴力递归：
1. 把问题转化为规模缩小了的同类问题的子问题
2. 有明确的不需要继续进行递归的条件（base case） -- 终止条件
3. 有得到子问题结果后的决策过程
4. 不记录每一个子问题的解

	动态规划：
1. 从暴力递归中来
2. 记录每一个子问题的解，避免重复计算
3. 把暴力递归的过程抽象成了状态表达
4. 存在化简状态表达
 */

/*
	求n！
 */

public class Code_01_Factorial {
	public static long getFactorial1(int n) {
		if (n == 1) {
			return 1L;
		}
		return (long) n * getFactorial1(n - 1);
	}

	public static long getFactorial2(int n) {
		long result = 1L;
		for (int i = 1; i <= n; i++) {
			result *= i;
		}
		return result;
	}

	public static void main(String[] args) {
		int n = 5;
		System.out.println(getFactorial1(n));
		System.out.println(getFactorial2(n)); // 120
	}

}
