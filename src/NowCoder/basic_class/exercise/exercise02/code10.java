package NowCoder.basic_class.exercise.exercise02;

import java.util.Arrays;

public class code10 {
    public static int maxGap(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
            min = Math.min(min, arr[i]);
        }

        if (max == min) {
            return 0;
        }

        int len = arr.length;
        int[] mins = new int[len + 1];
        int[] maxs = new int[len + 1];
        boolean[] hasNum = new boolean[len + 1];
        int bid = 0;

        for (int i = 0; i < arr.length; i++) {
            bid = bucket(arr[i], max, min, len);
            mins[bid] = hasNum[bid] ? Math.min(arr[i], mins[bid]) : arr[i];
            maxs[bid] = hasNum[bid] ? Math.max(arr[i], maxs[bid]) : arr[i];
            hasNum[bid] = true;
        }

        int lastMax = maxs[0];
        int res = 0;

        for (int i = 1; i <= len; i++) {
            if (hasNum[i]) {
                res = Math.max(res, mins[i] - lastMax);
                lastMax = maxs[i];
            }
        }

        return res;
    }

    public static int bucket(int num, int max, int min, int len) {
        return (num - min) * len / (max - min);
    }

    public static void main(String[] args) {
        int test = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean flag = true;

        for (int i = 0; i < test; i++) {
            int[] arr = generateArr(maxValue, maxSize);
            int[] newArr = copyArr(arr);
            if (comparator(newArr) != maxGap(arr)) {
                flag = false;
                break;
            }
        }
        System.out.println(flag ? "Nice!" : "Fucking fucked");
    }

    public static int[] generateArr(int maxValue, int maxSize) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    public static int[] copyArr(int[] arr) {
        int[] newArr = new int[arr.length];
        for (int i = 0; i < newArr.length; i++) {
            newArr[i] = arr[i];
        }
        return newArr;
    }

    public static int comparator(int[] arr) {
        if (arr.length < 2 || arr == null) {
            return 0;
        }
        Arrays.sort(arr);
        int maxGap = 0;
        for (int i = 1; i < arr.length; i++) {
            maxGap = Math.max(maxGap, arr[i] - arr[i - 1]);
        }
        return maxGap;
    }
}
