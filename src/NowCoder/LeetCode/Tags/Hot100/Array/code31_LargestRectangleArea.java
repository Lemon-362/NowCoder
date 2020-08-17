package NowCoder.LeetCode.Tags.Hot100.Array;

import java.util.Stack;

/*
84. 柱状图中最大的矩形
    给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
    求在该柱状图中，能够勾勒出来的矩形的最大面积

 */
public class code31_LargestRectangleArea {

    public static int largestRectangleArea(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }

        int res = 0;
        Stack<Integer> stack = new Stack<>();

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
        int[] arr = {2, 1, 5, 6, 2, 3};

        System.out.println(largestRectangleArea(arr)); //
    }

}
