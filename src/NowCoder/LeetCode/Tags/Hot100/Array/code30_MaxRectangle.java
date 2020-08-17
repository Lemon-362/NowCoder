package NowCoder.LeetCode.Tags.Hot100.Array;

import java.util.Stack;

/*
85. 最大矩形
    给定一个仅包含 0 和 1 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积

 */
public class code30_MaxRectangle {

    /*
    单调栈:
        TODO 单调栈存索引 大找小，从小到大
         advance_code11_MaximalRectangle
     */
    public static int maxRectangle(char[][] arr) {
        if (arr == null || arr.length < 1 || arr[0].length < 1) {
            return 0;
        }

        int[] height = new int[arr[0].length];
        int res = 0;

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                height[j] = arr[i][j] == '1' ? height[j] + 1 : 0;
            }
            res = Math.max(res, getMaxArea(height));
        }

        return res;
    }

    public static int getMaxArea(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }

        Stack<Integer> stack = new Stack<>();
        int res = 0;

        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[i] <= arr[stack.peek()]) {
                int popIndex = stack.pop();
                int right = i;
                int left = stack.isEmpty() ? -1 : stack.peek();
                int area = (right - left - 1) * arr[popIndex];
                res = Math.max(res, area);
            }

            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int popIndex = stack.pop();
            int right = arr.length;
            int left = stack.isEmpty() ? -1 : stack.peek();
            int area = (right - left - 1) * arr[popIndex];
            res = Math.max(res, area);
        }

        return res;
    }

    public static void main(String[] args) {
        char[][] map =
                {{'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}};
        System.out.println(maxRectangle(map)); // 6

        int[] arr = {4, 3, 2, 5, 6};
        System.out.println(getMaxArea(arr)); // 10

    }
}
