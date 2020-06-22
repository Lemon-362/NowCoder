package NowCoder.advanced_class.exercise.exercise01;

import java.util.LinkedList;

public class code07 {
    public static int[] slidingWindow(int[] arr, int w) {
        if (arr == null || arr.length < 1) {
            return null;
        }

        LinkedList<Integer> list = new LinkedList<>();
        int[] res = new int[arr.length - w + 1];
        int index = 0;

        for (int i = 0; i < arr.length; i++) {
            while (!list.isEmpty() && arr[i] >= arr[list.peekLast()]){
                list.pollLast();
            }

            list.addLast(i);

            if (list.peekFirst() == i - w){
                list.pollFirst();
            }

            if (i >= w - 1){
                res[index++] = arr[list.peekFirst()];
            }
        }

        return res;
    }

    // for test
    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {4, 3, 5, 4, 3, 3, 6, 7};
        int w = 3;
        printArray(slidingWindow(arr, w)); // 5 5 5 4 6 7
    }
}
