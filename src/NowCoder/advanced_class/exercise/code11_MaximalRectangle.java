package NowCoder.advanced_class.exercise;

import java.util.Stack;

/*
    单调栈的应用：最大子矩阵的大小
        给定一个整形矩阵map，其中的值只有0和1
        求其中全是1的所有矩阵区域中，最大的矩形区域为1的数量
    例：
    1 0 1 1
    1 1 1 1
    1 1 1 0
       返回6

    TODO 单调栈存索引
 */
public class code11_MaximalRectangle {
    public static int maximalRectangle(int[][] map) {
        if (map == null || map.length < 1 || map[0].length < 1) {
            return 0;
        }

        int[] height = new int[map[0].length];
        int max = 0;

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                height[j] = map[i][j] == 0 ? 0 : height[j] + 1;
            }
            max = Math.max(max, getMaxArea(height));
        }

        return max;
    }

    public static int getMaxArea(int[] arr){
        Stack<Integer> stack = new Stack<>();
        int res = 0;

        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[i] <= arr[stack.peek()]){
                int popIndex = stack.pop();
                int right = i;
                int left = stack.isEmpty() ? -1 : stack.peek();
                res = Math.max(res, (right - left - 1) * arr[popIndex]);
            }

            stack.push(i);
        }

        while (!stack.isEmpty()){
            int popIndex = stack.pop();
            int right = arr.length;
            int left = stack.isEmpty() ? -1 : stack.peek();
            res = Math.max(res, (right - left - 1) * arr[popIndex]);
        }

        return res;
    }

    public static void main(String[] args) {
        int[][] map = {{1, 0, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 0}};
        System.out.println(maximalRectangle(map)); // 6

        int[] arr = {4, 3, 2, 5, 6};
        System.out.println(getMaxArea(arr)); // 10
    }
}
