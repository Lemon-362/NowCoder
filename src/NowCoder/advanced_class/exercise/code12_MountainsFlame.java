package NowCoder.advanced_class.exercise;

import java.util.HashMap;
import java.util.Stack;

/*
    单调栈的应用：烽火传递
        数组形成环形，有如下要求：
        1. 相邻必可看见烽火
        2. 不相邻时，要求两者之间的值<=两边的较小值
        求可以看见烽火的数量对？

    TODO 单调栈存类
 */
public class code12_MountainsFlame {
    public static class Pair {
        private int value;
        private int times;

        public Pair(int value) {
            this.value = value;
            this.times = 1;
        }
    }

    public static int mountainsFlame(int[] arr) {
        if (arr == null || arr.length < 1) {
            return -1;
        }

        int maxIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            maxIndex = arr[maxIndex] < arr[i] ? i : maxIndex;
        }
        int maxValue = arr[maxIndex];
        int len = arr.length;
        int next = getNextIndex(maxIndex, len);

        Stack<Pair> stack = new Stack<>();
        stack.push(new Pair(maxValue));
        int res = 0;

        while (next != maxIndex) {
            int cur = arr[next];
            while (!stack.isEmpty() && cur > stack.peek().value) {
                int k = stack.pop().times;
                res += getCk2(k) + 2 * k;
            }
            if (cur == stack.peek().value) {
                stack.peek().times++;
            } else {
                stack.push(new Pair(cur));
            }
            next = getNextIndex(next, len);
        }

        while (!stack.isEmpty()) {
            int k = stack.pop().times;
            if (stack.size() >= 2) {
                res += getCk2(k) + 2 * k;
            } else if (stack.size() == 1) {
                int a = stack.peek().times;
                if (a >= 2) {
                    res += getCk2(k) + 2 * k;
                }else {
                    res += getCk2(k) + k;
                }
            }else {
                if (k >= 2){
                    res += getCk2(k);
                }else {
                    res += 0;
                }
            }
        }

        return res;
    }

    public static int getCk2(int k) {
        return k > 1 ? k * (k - 1) / 2 : 0;
    }

    public static int getNextIndex(int index, int len) {
        return index == len - 1 ? 0 : index + 1;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 4, 5, 3};
        System.out.println(mountainsFlame(arr)); // 7
    }
}
