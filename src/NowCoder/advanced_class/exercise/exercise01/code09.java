package NowCoder.advanced_class.exercise.exercise01;

import java.util.HashMap;
import java.util.Stack;

public class code09 {
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
