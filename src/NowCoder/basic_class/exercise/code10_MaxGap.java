package NowCoder.basic_class.exercise;

import java.util.Arrays;

/*
    最大差值：
        给定一个数组，求排序之后相邻两数的最大差值
    1. 先确定全局最大和全局最小，用于后续计算每个桶的长度
    2. 每个桶内存三个信息：hasNum，min，max
    3. 最大差值一定等于 当前桶的min - 前一个桶的max
 */
public class code10_MaxGap {
    public static int maxGap(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int len = arr.length;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < len; i++) {
            min = Math.min(arr[i], min);
            max = Math.max(arr[i], max);
        }
        if (max == min) {
            return 0;
        }
        boolean[] hasNum = new boolean[len + 1];
        int[] mins = new int[len + 1];
        int[] maxs = new int[len + 1];
        int bid;
        for (int i = 0; i < len; i++) {
            bid = bucket(arr[i], min, max, len);
            mins[bid] = hasNum[bid] ? Math.min(mins[bid], arr[i]) : arr[i];
            maxs[bid] = hasNum[bid] ? Math.max(maxs[bid], arr[i]) : arr[i];
            hasNum[bid] = true;
        }
        int res = 0;
        int lastMax = maxs[0];
        for (int i = 1; i <= len; i++) {
            if (hasNum[i]) {
                res = Math.max(res, mins[i] - lastMax);
                lastMax = maxs[i];
            }
        }
        return res;
    }

    public static int bucket(int num, int min, int max, int len) {
        return (int) ((num - min) * len / (max - min));
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
