package NowCoder.advanced_class.code.class_3;

import java.util.Stack;

/*
    最大子矩阵的大小：
        给定一个整形矩阵map，其中的值只有0和1
        求其中全是1的所有矩阵区域中，最大的矩形区域为1的数量
    例：
    1 0 1 1
    1 1 1 1
    1 1 1 0
       返回6
 */
public class Code_03_MaximalRectangle {
    public static int maxRecSize(int[][] map) {
        if (map == null || map.length == 0 || map[0].length == 0) {
            return 0;
        }
        int maxArea = 0;
        int[] height = new int[map[0].length]; // 长度就是map的列
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                // 如果当前位置为0，直接为0，往上没有连续的1
                // 如果当前位置为1，往上有连续的1，直接在之前的值上加1
                height[j] = map[i][j] == 0 ? 0 : height[j] + 1; // TODO 高度数组height只有一行
            }
            // 每遍历完一行，就找一次
            maxArea = Math.max(maxRecFromBottom(height), maxArea);
        }
        return maxArea;
    }

    // 直方图找最大矩形：单调栈
    // TODO 在左右找比他小的，因为比他小说明不能扩，到那个数截止。比他大说明能扩
    public static int maxRecFromBottom(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int maxArea = 0;
        Stack<Integer> stack = new Stack<>(); // 只存索引，找左右比他小的，栈底到栈顶：从小到大
        // 遍历数组
        for (int i = 0; i < height.length; i++) { // TODO 从小到大，存索引
            while (!stack.isEmpty() && height[i] <= height[stack.peek()]) { // 若不符合从小到大，则结算弹出的值
                int j = stack.pop(); // 弹出的值
                // 左边是他下一个
                int k = stack.isEmpty() ? -1 : stack.peek(); // 左边界
                // 右边是压入的数
                int curArea = (i - k - 1) * height[j]; // 右边界就是要存的数，范围（k，i）= i - k - 1，左右都不包含，高度是弹出的值对应数组的值
                maxArea = Math.max(maxArea, curArea);
            }
            stack.push(i);
        }
        // 存完数组，栈中可能还有值，依次弹出结算
        while (!stack.isEmpty()) {
            int j = stack.pop();
            // 左边是塔下要给
            int k = stack.isEmpty() ? -1 : stack.peek(); // 左边界
            // 右边是null，数组末尾
            int curArea = (height.length - k - 1) * height[j]; // 右边界是数组右边，范围（k，length），左右都不包含
            maxArea = Math.max(maxArea, curArea);
        }
        return maxArea;
    }

    public static void main(String[] args) {
        int[][] map = {{1,0,1,1},{1,1,1,1},{1,1,1,0}};
        System.out.println(maxRecSize(map)); // 6

        int[] arr = {4, 3, 2, 5, 6};
        System.out.println(maxRecFromBottom(arr)); // 10
    }
}
