package NowCoder;

import java.util.*;

public class test4 {

    public static int[] process(int[] arr) {

        int[] res = new int[arr.length];

        Stack<Integer> stack = new Stack<>();
        HashMap<Integer, Integer> lBigMap = new HashMap<>();
        HashMap<Integer, Integer> rBigMap = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[i] >= arr[stack.peek()]) {
                int popIndex = stack.pop();
                rBigMap.put(popIndex, i);
                if (stack.isEmpty()) {
                    lBigMap.put(popIndex, -1);
                } else {
                    lBigMap.put(popIndex, stack.peek());
                }
            }

            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int popIndex = stack.pop();
            rBigMap.put(popIndex, -1);
            if (stack.isEmpty()) {
                lBigMap.put(popIndex, -1);
            } else {
                lBigMap.put(popIndex, stack.peek());
            }
        }

        // 打印
        for (int i = 0; i < arr.length; i++) {
            int left = lBigMap.get(i);
            if (left == -1) {
                res[i] = -1;
            } else {
                res[i] = arr[left];
            }
        }

        return res;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        int[] arr = new int[n];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = scanner.nextInt();
        }

        int[] res = process(arr);

        for (int i = 0; i < res.length; i++) {
            System.out.print(res[i] + " ");
        }

    }
}
