package NowCoder.basic_class.exercise;

/*
    给定一个数组和一个整数a，可以从数组中任选数字，
    问能不能累加得到a。

暴力递归：
    要当前位置 / 不要当前位置
    求出所有结果，然后和a进行判断，有一个相等就返回true
动态规划：

 */
public class code45_MoneyProblem {
    public static boolean isSum(int[] arr, int a, int res, int i) {
        if (i == arr.length) { // 到达数组末尾时，看有没有值等于a，如果有，那么就返回true
            return res == a;
        }
        return isSum(arr, a, res, i + 1) || isSum(arr, a, res + arr[i], i + 1);
    }

    public static boolean isSum02(int[] arr, int a) {
        // dp的行是arr元素个数+1
        int row = arr.length + 1;
        // dp的列是arr元素之和+1
        int col = 0;
        for (int i = 0; i < row - 1; i++) {
            col += arr[i];
        }
        col = col + 1;
        // dp表
        boolean[][] dp = new boolean[row][col];
        // base case：i==arr.length，最后一行
        for (int i = 0; i < col; i++) { // 在最后一行上，遍历dp的列
            if (i == a) {
                dp[row - 1][i] = true;
            }
        }
        // 普遍位置：根据暴力递归的普遍位置语句，从倒数第二行开始往上依次填值
        for (int i = row - 2; i >= 0; i--) {
            for (int j = 0; j < col; j++) {
                if (j + arr[i] <= a) { // 限制范围：如果j（之前的和）加上当前位置的值 大于 a，那么一定为false
                    dp[i][j] = dp[i + 1][j] || dp[i + 1][j + arr[i]];
                }
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        int[] arr = {1, 4, 8};
        int a = 11;
        System.out.println(isSum(arr, a, 0, 0));
        System.out.println(isSum02(arr, a));
//        boolean[][] dp = isSum02(arr, a);
//        for (int i = 0; i < dp.length; i++) {
//            for (int j = 0; j < dp[0].length; j++) {
//                System.out.print(dp[i][j] + " ");
//            }
//            System.out.println();
//        }
    }
}
