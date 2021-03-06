package InputAndOutput;

import java.util.Scanner;
import java.util.Stack;

public class test3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            int k = sc.nextInt();
            int[] arr1 = new int[n];
//            Stack<Integer> stack1 = new Stack<>();
            int num = 0;
            for (int i = 0; i < n; i++) {
                num = sc.nextInt();
//                stack1.push(num);
                arr1[i] = num;
            }

            int[] arr = new int[n * n];
            int p = 0;
            for (int i = 0; i < arr1.length; i++) {
                int num1 = arr1[i];
                for (int j = 0; j < arr1.length; j++) {
                    arr[p++] = num1 *10 + arr1[j];
                }
            }
//            while (!stack1.isEmpty()) {
//                int num1 = stack1.pop();
//                for (int i = 0; i < arr1.length; i++) {
//                    arr[p] = num1 * 10 + arr1[i];
//                    p++;
//                }
//            }

            int[] resArr = getMinKNumsByBFPRT(arr, k);

            int res = resArr[resArr.length - 1];
            int a = res / 10;
            int b = res % 10;
            String s = "(" + a + "," + b + ")";
            System.out.println(s);
        }
    }

    public static Stack<Integer> copy(Stack<Integer> stack) {
        Stack<Integer> copyStack = new Stack<>();
        while (!stack.isEmpty()) {
            copyStack.push(stack.pop());
        }
        return copyStack;
    }

    // BFPRT：O(N)
    public static int[] getMinKNumsByBFPRT(int[] arr, int k) {
        if (k < 1 || k > arr.length) {
            return arr;
        }
        int minKth = getMinKthByBFPRT(arr, k);
        int[] res = new int[k];
        int index = 0;
        for (int i = 0; i != arr.length; i++) {
            if (arr[i] < minKth) {
                res[index++] = arr[i];
            }
        }
        for (; index != res.length; index++) {
            res[index] = minKth;
        }
        return res;
    }

    public static int getMinKthByBFPRT(int[] arr, int K) {
        int[] copyArr = copyArray(arr);
        return bfprt(copyArr, 0, copyArr.length - 1, K - 1);
    }

    public static int[] copyArray(int[] arr) {
        int[] res = new int[arr.length];
        for (int i = 0; i != res.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // BFPRT流程，在begin-end上求第i小的数
    public static int bfprt(int[] arr, int begin, int end, int i) {
        if (begin == end) {
            return arr[begin];
        }
        // 中位数数组中的中位数 作为划分值
        int pivot = medianOfMedians(arr, begin, end);
        // partition
        int[] pivotRange = partition(arr, begin, end, pivot);
        if (i >= pivotRange[0] && i <= pivotRange[1]) { // 等于区域
            return arr[i];
        } else if (i < pivotRange[0]) { // 小于区域递归
            return bfprt(arr, begin, pivotRange[0] - 1, i);
        } else { // 大于区域递归
            return bfprt(arr, pivotRange[1] + 1, end, i);
        }
    }

    // 中位数数组中的中位数
    public static int medianOfMedians(int[] arr, int begin, int end) {
        int num = end - begin + 1;
        int offset = num % 5 == 0 ? 0 : 1;
        // 中位数数组
        int[] mArr = new int[num / 5 + offset];
        for (int i = 0; i < mArr.length; i++) {
            int beginI = begin + i * 5;
            int endI = beginI + 4;
            mArr[i] = getMedian(arr, beginI, Math.min(end, endI)); // 每组中的中位数放在中位数数组中
        }
        // 求中位数数组中的中位数
        return bfprt(mArr, 0, mArr.length - 1, mArr.length / 2);
    }

    public static int[] partition(int[] arr, int begin, int end, int pivotValue) {
        int small = begin - 1;
        int cur = begin;
        int big = end + 1;
        while (cur != big) {
            if (arr[cur] < pivotValue) {
                swap(arr, ++small, cur++);
            } else if (arr[cur] > pivotValue) {
                swap(arr, cur, --big);
            } else {
                cur++;
            }
        }
        // 返回等于区域
        int[] range = new int[2];
        range[0] = small + 1;
        range[1] = big - 1;
        return range;
    }

    public static int getMedian(int[] arr, int begin, int end) {
        insertionSort(arr, begin, end);
        int sum = end + begin;
        int mid = (sum / 2) + (sum % 2);
        return arr[mid];
    }

    public static void insertionSort(int[] arr, int begin, int end) {
        for (int i = begin + 1; i != end + 1; i++) {
            for (int j = i; j != begin; j--) {
                if (arr[j - 1] > arr[j]) {
                    swap(arr, j - 1, j);
                } else {
                    break;
                }
            }
        }
    }

    public static void swap(int[] arr, int index1, int index2) {
        int tmp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = tmp;
    }
}
