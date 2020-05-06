package NowCoder.advanced_class.class_3;

import java.util.Scanner;
import java.util.Stack;

/*
    单调栈的应用：烽火传递
        数组形成环形，有如下要求：
        1. 相邻必可看见烽火
        2. 不相邻时，要求两者之间的值<=两边的较小值
        求可以看见烽火的数量对？
 */
public class Code_04_MountainsAndFlame {
    public static class Pair { // 栈中存 (值，次数)
        public int value;
        public int times;

        public Pair(int value) {
            this.value = value;
            this.times = 1;
        }
    }

    // main
    public static long communications(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int size = arr.length;
        int maxIndex = 0;
        // 找第一个最大值的位置
        for (int i = 0; i < size; i++) {
            maxIndex = arr[maxIndex] < arr[i] ? i : maxIndex;
        }
        int value = arr[maxIndex]; // 最大值
        // 最大值的下一个位置
        int index = nextIndex(size, maxIndex); // 因为是环形的，所以有可能最大值刚好是最后一个，那么其下一个就到开头了

        long res = 0L;
        // 找左右比它大的，从大到小
        Stack<Pair> stack = new Stack<>(); // (值, 出现次数)
        // 压入最大值
        stack.push(new Pair(value));
        // 从最大值的下一个开始遍历环形数组，如果index回到了最大值位置，则结束
        while (index != maxIndex) {
            value = arr[index]; // 要压入栈的当前值
            while (!stack.isEmpty() && value > stack.peek().value) { // 不符合从大到小，弹出结算
                int times = stack.pop().times; // 弹出元素的出现次数
                res += getInternalSum(times) + 2 * times; // 共有 C(2，k) + 2k 对
            }
            // value<=stack.peek()，此时压入元素不大于栈顶
            if (!stack.isEmpty() && stack.peek().value == value) { // 栈顶和当前值相等
                stack.peek().times++; // 叠加，出现次数加1
            } else { // 符合从大到小，压入栈
                stack.push(new Pair(value));
            }
            index = nextIndex(size, index); // index更新到下一个位置
        }

        while (!stack.isEmpty()) {
            int k = stack.pop().times;
            if (stack.size() >= 2) { // 如果弹出之后还剩2个以上，C(k, 2) + 2k
                res += getInternalSum(k) + 2 * k;
            } else if (stack.size() == 1) { // 如果弹出之后还剩一个
                int a = stack.peek().times; // 栈底元素的次数
                if (a >= 2) {
                    res += getInternalSum(k) + 2 * k;
                } else if (a == 1) {
                    res += getInternalSum(k) + k;
                }
            } else { // 如果弹出之后没有了
                if (k >= 2) { // 弹出元素的次数不止一个，内部可以满足
                    res += getInternalSum(k);
                } else {
                    res += 0;
                }
            }
        }

//        // 遍历完结算
//        while (!stack.isEmpty()) {
//            int times = stack.pop().times;
//            res += getInternalSum(times); // 内部有 C(2，m)对
//            if (!stack.isEmpty()) { // 对外部分类
//                res += times;
//                if (stack.size() > 1) {
//                    res += times;
//                } else {
//                    res += stack.peek().times > 1 ? times : 0;
//                }
//            }
//        }
        return res;
    }

    // 环形数组中i的下一个索引
    public static int nextIndex(int size, int i) {
        return i < (size - 1) ? (i + 1) : 0;
    }

    // C(N, 2) = N*(N-1)/2
    public static long getInternalSum(int n) {
        return n == 1L ? 0L : (long) n * (long) (n - 1) / 2L;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 4, 5, 3};
        System.out.println(communications(arr)); // 7
    }
}
