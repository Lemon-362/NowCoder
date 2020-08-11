package NowCoder.LeetCode.Tags.Hot100.Array;

import java.util.Stack;

/*
581. 最短无序连续子数组
    给定一个整数数组，你需要寻找一个连续的子数组，
    如果对这个子数组进行升序排序，那么整个数组都会变为不降序排序。
    你找到的子数组应是最短的，请输出它的长度
    注意: 数组中可能包含重复元素

 */
public class code07_FindShortestUnsortedSubArray {

    /*
        类似于贪心
        从左到右循环，记录最大值为 max，
            若 arr[i] < max, 则表明位置 i 需要调整, 循环结束，记录需要调整的最大位置 i 为 high;
        从右到左循环，记录最小值为 min,
            若 arr[i] > min, 则表明位置 i 需要调整，循环结束，记录需要调整的最小位置 i 为 low

     */
    public static int findUnsortedSubArray1(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }

        int max = arr[0];
        int right = 0;
        for (int i = 1; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
            if (arr[i] < max){
                right = i;
            }
        }

        int min = arr[arr.length - 1];
        int left = arr.length - 1;
        for (int i = arr.length - 2; i >= 0; i--) {
            min = Math.min(min, arr[i]);
            if (arr[i] > min){
                left = i;
            }
        }

        if (left < right) {
            return right - left + 1;
        }else {
            return 0;
        }
    }

    /*
    栈的方式:
        可以看成是类似于单调栈的方法, 但是单调栈找的是离他最近的
        而本题要找的并不是最近的,
        TODO 找左边离他最远且比他大的数
            所以不能把结算的时机放在弹出index上, 而是放在插入index上

        1. 为什么会无序?
            对于一个数来说, 肯定是因为他前面有比他大的数, 导致这个数本应该位于那个位置上
        2. 如何找到这个数?
        (1) 找左边界
            ==> 正序遍历,找左边离他最远且比他大的数的位置
            使用栈, 栈底到栈顶是从小到大的
            当一个数入栈时, 如果栈顶元素 > 它, 那么说明找到了一个左边比它大的数
            直到这个数可以入栈为止, 就可以找到这个数的左边离他最远且比他大的
        (2) 找右边界
            ==> 看成是翻转数组, 要让数组降序排列
                逆序遍历, 找左边离他最远且比他小的
        3. TODO 结算时机: 对每次入栈但不符合条件的数进行结算
        4. 对于重复元素, 因为是找左边离他最远的数, 所以如果入栈元素和栈顶相等, 不应该入栈
            只需要保留靠左的那个元素即可

     */
    public static int findUnsortedSubArray2(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }

        Stack<Integer> stack = new Stack<>();

        int left = arr.length;
        // 正序遍历, 相当于升序排列
        for (int i = 0; i < arr.length; i++) {
            // 找左边离他最近且比它大的数的位置
            // 由于是对入栈元素结算, 所以栈是从小到大的
            while (!stack.isEmpty() && arr[i] < arr[stack.peek()]){
                left = Math.min(left, stack.pop());
            }
            // 对于相同元素, 不入栈
            if (stack.isEmpty()){
                stack.push(i);
            }else if (arr[i] != arr[stack.peek()]){
                stack.push(i);
            }
        }

        stack.clear();
        int right = 0;
        // 逆序遍历, 相当于降序排列
        for (int i = arr.length - 1; i >= 0; i--) {
            // 找左边离他最近且比它小的数的位置
            // 由于是对入栈元素结算, 所以栈是从大到小的
            while (!stack.isEmpty() && arr[i] > arr[stack.peek()]){
                right = Math.max(right, stack.pop());
            }
            // 对于相同元素, 不入栈
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

    public static void main(String[] args) {

        int[] arr1 = {2, 6, 4, 8, 10, 9, 15};
        int[] arr2 = {1, 2, 4, 4, 5, 3};

        System.out.println(findUnsortedSubArray2(arr1)); // 5
        System.out.println(findUnsortedSubArray2(arr2)); // 4

    }
}
