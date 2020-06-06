package NowCoder.advanced_class.exercise.exercise01;

import java.util.LinkedList;

public class code08 {
    public static int getNum01(int[] arr, int num) {
        if (arr == null || arr.length < 1 || num < 1) {
            return 0;
        }

        int res = 0;

        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                if (isValid(arr, i, j, num)) {
                    res++;
                }
            }
        }

        return res;
    }

    public static boolean isValid(int[] arr, int i, int j, int num) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (int k = i; k <= j; k++) {
            min = Math.min(min, arr[k]);
            max = Math.max(max, arr[k]);
        }

        return max - min <= num;
    }

    public static int getNum02(int[] arr, int num) {
        if (arr == null || arr.length < 1 || num < 1) {
            return 0;
        }

        LinkedList<Integer> minQ = new LinkedList<>();
        LinkedList<Integer> maxQ = new LinkedList<>();
        int res = 0;
        int L = 0;
        int R = 0;

        while (L < arr.length) {
            while (R < arr.length) {
                while (!minQ.isEmpty() && arr[R] <= arr[minQ.peekLast()]) {
                    minQ.pollLast();
                }
                minQ.addLast(R);

                while (!maxQ.isEmpty() && arr[R] >= arr[maxQ.peekLast()]) {
                    maxQ.pollLast();
                }
                maxQ.addLast(R);

                if (arr[maxQ.peekFirst()] - arr[minQ.peekFirst()] > num) {
                    break;
                }

                R++;
            }

            res += (R - L);

            if (minQ.peekFirst() == L) {
                minQ.pollFirst();
            }
            if (maxQ.peekFirst() == L) {
                maxQ.pollFirst();
            }

            L++;
        }

        return res;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        int num = 2;
        System.out.println(getNum01(arr, num)); // 12
        System.out.println(getNum02(arr, num));
    }
}
