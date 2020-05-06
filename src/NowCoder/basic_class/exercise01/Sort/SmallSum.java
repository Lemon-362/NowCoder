package NowCoder.basic_class.exercise01.Sort;

public class SmallSum {
    public static int method(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return method(arr, 0, arr.length - 1);
    }

    public static int method(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        int mid = l + ((r - l) >> 1);
        return method(arr, l, mid) + method(arr, mid + 1, r) + merge(arr, l, mid, r);
    }

    public static int merge(int[] arr, int l, int mid, int r) {
        int[] help = new int[r - l + 1];
        int p1 = l;
        int p2 = mid + 1;
        int i = 0;
        int sum = 0;
        while (p1 <= mid && p2 <= r) {
            sum += arr[p1] < arr[p2] ? (r - p2 + 1) * arr[p1] : 0;
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= mid) {
            help[i++] = arr[p1++];
        }
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }
        for (int j = 0; j < help.length; j++) {
            arr[l + j] = help[j];
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 4, 2, 5};
        System.out.println(method(arr)); // 16
    }
}