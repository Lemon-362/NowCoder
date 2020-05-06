package NowCoder.advanced_class.exercise;

/*
	纸牌博弈：
		给定一个整型数组arr，代表数值不同的纸牌排成一条线。
		玩家A和B依次拿走纸牌，规定A先，B后，但每个玩家只能拿走最左或最有的纸牌
		假设都绝顶聪明，都为了赢而拿，问最后获胜者的分数是多少？
	例：arr = [1, 2, 100, 4]
		返回101
 */
public class code30_CardsInLine {
    /*
        1. 暴力递归:
            分析其中的一个子过程:在arr[i, ..., j]上选择
            假设A在arr[i, ..., j]上先拿的最终得分f(i, j), B在arr[i, ..., j]上后拿的最终得分s(i, j)
            1) 对于A先拿来说: f
                a. A先拿走arr[i], 那么下一轮A变成了在arr[i+1, ..., j]上后拿
                -->总得分 = arr[i] + s(i+1, j)
                b. A先拿走arr[j], 那么下一轮A变成了在arr[i, ..., j-1]上后拿
                -->总得分 = arr[i+1] + s(i, j-1)
            TODO 在a和b中选较大值
            base case:
                i == j时,只有arr[i],那么A先拿走arr[i]
                -->总得分 = arr[i]
            2) 对于B后拿来说: s
                a. A先拿走了arr[i], 那么对于B来说,当前是B在arr[i+1, ..., j]上先拿
                -->总得分 = f(i+1, j)
                b. A先拿走了arr[j], 那么对于B来说,当前是B在arr[i, ..., j-1]上先拿
                -->总得分 = f(i, j-1)
            TODO 在a和b中选较小值:因为A已经拿走了较大值,剩下的是较小值,B为了赢,只能先拿较小的
            base case:
                i == j时,只有arr[i],那么A先拿走了arr[i], 对于B来说, 数组中没有东西了
                -->总得分 = 0

            最终在这几种情况中选择max
     */
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

        int a = f(arr, i + 1, j);
        int b = f(arr, i, j - 1);

        return Math.min(a, b);
    }

    public static int getMaxScore1(int[] arr) {
        int f_score = f(arr, 0, arr.length - 1);
        int s_score = s(arr, 0, arr.length - 1);

        return Math.max(f_score, s_score);
    }

    /*
        2. 动态规划:
            两张表f和s,可变参数都是i和j,范围就是arr.length
     */
    public static int getMaxScore2(int[] arr) {
        int[][] f = new int[arr.length][arr.length];
        int[][] s = new int[arr.length][arr.length];

        for (int j = 0; j < arr.length; j++) {
            f[j][j] = arr[j];
            s[j][j] = 0;
            for (int i = j - 1; i >= 0; i--) {
                int f_a = arr[i] + s[i + 1][j];
                int f_b = arr[j] + s[i][j - 1];
                f[i][j] = Math.max(f_a, f_b);

                int s_a = f[i + 1][j];
                int s_b = f[i][j - 1];
                s[i][j] = Math.min(s_a, s_b);
            }
        }


        int f_score = f[0][arr.length - 1];
        int s_score = s[0][arr.length - 1];

        return Math.max(f_score, s_score);
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 100, 4};
        System.out.println(getMaxScore1(arr)); // 101
        System.out.println(getMaxScore2(arr)); // 101
    }
}
