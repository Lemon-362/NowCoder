package NowCoder.advanced_class.exercise.exercise01;

import java.util.HashMap;
import java.util.Stack;

public class code11 {
    public static int maximalRectangle(int[][] map) {
        if (map == null || map.length < 1 || map[0].length < 1) {
            return 0;
        }

        int[] height = new int[map[0].length];
        int res = 0;

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                height[j] = map[i][j] == 0 ? 0 : height[j] + 1;
            }
            res = Math.max(res, getMaxArea(height));
        }

        return res;
    }

    public static int getMaxArea(int[] arr){
        Stack<Integer> stack = new Stack<>();
        int res = 0;

        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[i] <= arr[stack.peek()]){
                int popIndex = stack.pop();
                int right = i;
                int left = stack.isEmpty() ? -1 : stack.peek();
                int area = (right - left - 1) * arr[popIndex];
                res = Math.max(res, area);
            }

            stack.push(i);
        }

        while (!stack.isEmpty()){
            int popIndex = stack.pop();
            int right = arr.length;
            int left = stack.isEmpty() ? -1 : stack.peek();
            int area = (right - left - 1) * arr[popIndex];
            res = Math.max(res, area);
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
