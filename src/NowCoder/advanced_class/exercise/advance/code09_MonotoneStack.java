package NowCoder.advanced_class.exercise.advance;

import java.util.HashMap;
import java.util.Stack;

/*
    单调栈：栈+哈希表
        在数组中找每个数的左边和右边离他最近且比他大的数
    TODO 找比他大的：栈从栈底到栈顶是 从大到小的
    TODO 找比他小的：栈从栈底到栈顶是 从小到大的

    TODO 相邻元素相等时的解决办法？

遍历元素时，依次压入，不满足条件时弹出：
    弹出值的左信息：他的下一个
    弹出值的右信息：压入的数
遍历完栈中还有元素时，依次弹出：
    弹出值的左信息：他的下一个
    弹出值的右信息：null
 */
public class code09_MonotoneStack {
    public static void MonotoneStack(int[] arr) {
        if (arr == null || arr.length < 1) {
            return;
        }

        Stack<Integer> stack = new Stack<>();
        HashMap<Integer, Integer> lBigMap = new HashMap<>();
        HashMap<Integer, Integer> rBigMap = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[i] >= arr[stack.peek()]){
                int popIndex = stack.pop();
                rBigMap.put(popIndex, i);
                if (stack.isEmpty()){
                    lBigMap.put(popIndex, -1);
                }else {
                    lBigMap.put(popIndex, stack.peek());
                }
            }

            stack.push(i);
        }

        while (!stack.isEmpty()){
            int popIndex = stack.pop();
            rBigMap.put(popIndex, -1);
            if (stack.isEmpty()){
                lBigMap.put(popIndex, -1);
            }else {
                lBigMap.put(popIndex, stack.peek());
            }
        }

        // 打印
        for (int i = 0; i < arr.length; i++) {
            int left = lBigMap.get(i);
            int right = rBigMap.get(i);
            // 打印索引对应的值
            System.out.print(arr[i] + " ");
            if (left == -1) {
                System.out.print("左： " + "null ");
            } else {
                System.out.print("左： " + arr[left] + " ");
            }
            if (right == -1) {
                System.out.print("右： " + "null ");
            } else {
                System.out.print("右： " + arr[right] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[] arr = {5, 4, 3, 6, 5, 3};
        MonotoneStack(arr);
//        5 左： null 右： 6
//        4 左： 5 右： 6
//        3 左： 4 右： 6
//        6 左： null 右： null
//        5 左： 6 右： null
//        3 左： 5 右： null
    }
}
