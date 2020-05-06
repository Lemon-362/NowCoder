package NowCoder.Nowcoder;

import java.util.ArrayList;
import java.util.Arrays;

/*
    最小的k个数：
        输入n个整数，找出其中最小的K个数。
        例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4,。
 */
public class code29_GetLeastNum {
    public static ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        // TODO 越界时return 空list，而不是null
        if (k > input.length) {
            return list;
        }
        Arrays.sort(input);
        for (int i = 0; i < k; i++) {
            list.add(input[i]);
        }
        return list;
    }

    // BFPRT为什么不行?
    public static ArrayList<Integer> method(int[] input, int k) {
        if (k == input.length) {
            return save(input, k - 1);
        }
        return process(input, 0, input.length - 1, k - 1);
    }

    public static ArrayList<Integer> save(int[] arr, int i) {
        for (int j = 0; j <= i; j++) {
            list.add(arr[j]);
        }
        return list;
    }

    static ArrayList<Integer> list = new ArrayList<>();

    public static ArrayList<Integer> process(int[] arr, int l, int r, int i) {
        if (l == r) {
            return save(arr, l);
        }

        swap(arr, r, l + (int) (Math.random() * (r - l + 1)));
        int[] p = partition(arr, l, r);

        if (i >= p[0] && i <= p[1]) {
            return save(arr, i);
        } else if (i < p[0]) {
            return process(arr, l, p[0] - 1, i);
        } else {
            return process(arr, p[1] + 1, r, i);
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static int[] partition(int[] arr, int l, int r) {
        int less = l - 1;
        int more = r;
        int cur = l;
        while (cur < more) {
            if (arr[cur] < arr[r]) {
                swap(arr, ++less, cur++);
            } else if (arr[cur] > arr[r]) {
                swap(arr, --more, cur);
            } else {
                cur++;
            }
        }
        swap(arr, r, more);

        int[] range = new int[2];
        range[0] = less + 1;
        range[1] = more;
        return range;
    }

    public static void main(String[] args) {
        int[] arr = {4, 5, 1, 6, 2, 7, 3, 8};
        ArrayList<Integer> list = GetLeastNumbers_Solution(arr, 8);
        for (Integer num : list) {
            System.out.print(num + " ");
        }
    }
}
