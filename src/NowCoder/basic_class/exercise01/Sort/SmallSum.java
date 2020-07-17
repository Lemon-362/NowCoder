package NowCoder.basic_class.exercise01.Sort;

public class SmallSum {
    public static int method(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }

        return mergeSort(arr, 0, arr.length - 1);
    }

    public static int mergeSort(int[] arr, int l, int r) {
        // base case
        if (l == r) {
            return 0;
        }

        int mid = (l + r) >> 1;

        return mergeSort(arr, l, mid) + mergeSort(arr, mid + 1, r) + merge(arr, l, r, mid);
    }

    public static int merge(int[] arr, int l, int r, int mid) {
        int[] help = new int[r - l + 1];
        int index = 0;
        int res = 0;
        int p1 = l;
        int p2 = mid + 1;

        while (p1 <= mid && p2 <= r) {
            res += arr[p1] < arr[p2] ? arr[p1] * (r - p2 + 1) : 0;
            help[index++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }

        while (p1 <= mid) {
            help[index++] = arr[p1++];
        }
        while (p2 <= r) {
            help[index++] = arr[p2++];
        }

        return res;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 4, 2, 5};
        System.out.println(method(arr)); // 16
    }
}
