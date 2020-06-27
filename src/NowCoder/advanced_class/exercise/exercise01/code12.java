package NowCoder.advanced_class.exercise.exercise01;

import javax.xml.transform.stax.StAXResult;
import java.util.HashMap;
import java.util.Stack;

public class code12 {
    public static class Pair {
        private int value;
        private int times;

        public Pair(int value, int times) {
            this.value = value;
            this.times = times;
        }
    }

    public static int mountainsFlame(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }

        int maxIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            maxIndex = arr[maxIndex] > arr[i] ? maxIndex : i;
        }
        int maxValue = arr[maxIndex];
        int len = arr.length;
        int nextIndex = getNextIndex(maxIndex, len);
        Stack<Pair> stack = new Stack<>();
        stack.push(new Pair(maxValue, 1));

        int res = 0;

        while (nextIndex != maxIndex){
            while (!stack.isEmpty() && arr[nextIndex] > stack.peek().value){
                int times = stack.pop().times;
                res += getCk2(times) + 2 * times;
            }

            if (arr[nextIndex] == stack.peek().value){
                stack.peek().times++;
            }else {
                stack.push(new Pair(arr[nextIndex], 1));
            }

            nextIndex = getNextIndex(nextIndex, len);
        }

        while (!stack.isEmpty()){
            int k = stack.pop().times;
            if (stack.size() >= 2){
                res += getCk2(k) + 2 * k;
            }else if (stack.size() == 1){
                int a = stack.peek().times;
                if (a >= 2){
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

    public static int getCk2(int times){
        return times > 1 ? times * (times - 1) / 2 : 0;
    }

    public static int getNextIndex(int index, int len){
        return index == len - 1 ? 0 : index + 1;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 4, 5, 3};
        System.out.println(mountainsFlame(arr)); // 7
    }
}
