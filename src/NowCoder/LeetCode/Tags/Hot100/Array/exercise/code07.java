package NowCoder.LeetCode.Tags.Hot100.Array.exercise;

import java.util.Stack;

/*
581. 最短无序连续子数组
    给定一个整数数组，你需要寻找一个连续的子数组，
    如果对这个子数组进行升序排序，那么整个数组都会变为不降序排序。
    你找到的子数组应是最短的，请输出它的长度
    注意: 数组中可能包含重复元素

 */
public class code07 {

    public static int findUnsortedSubArray1(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }

        Stack<Integer> stack = new Stack<>();
        int left = arr.length;
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[i] < arr[stack.peek()]){
                left = Math.min(left, stack.pop());
            }
            if (stack.isEmpty()){
                stack.push(i);
            }else if (arr[i] != arr[stack.peek()]){
                stack.push(i);
            }
        }

        stack.clear();
        int right = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[i] > arr[stack.peek()]){
                right = Math.max(right, stack.pop());
            }
            if (stack.isEmpty()){
                stack.push(i);
            }else if (arr[i] != arr[stack.peek()]){
                stack.push(i);
            }
        }

        if (right > left){
            return right - left + 1;
        }else {
            return 0;
        }
    }

    public static int findUnsortedSubArray2(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }

        int left = 0;
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < max){
                break;
            }else {
                max = arr[i];
                left = i;
            }
        }

        int right = arr.length - 1;
        int min = arr[arr.length - 1];
        for (int i = arr.length - 2; i >= 0; i--) {
            if (arr[i] > min){
                break;
            }else {
                min = arr[i];
                right = i;
            }
        }

        if (right > left){
            return right - left + 1;
        }else {
            return 0;
        }
    }

    public static void main(String[] args) {

        int[] arr1 = {2, 6, 4, 8, 10, 9, 15};
        int[] arr2 = {1, 3, 2, 2, 2};
        int[] arr = {1, 3, 2, 3, 3};

        System.out.println(findUnsortedSubArray1(arr1)); // 5
        System.out.println(findUnsortedSubArray1(arr2)); // 4
        System.out.println(findUnsortedSubArray1(arr)); // 2

    }
}
