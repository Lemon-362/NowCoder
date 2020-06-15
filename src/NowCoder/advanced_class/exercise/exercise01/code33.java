package NowCoder.advanced_class.exercise.exercise01;

public class code33 {
    public static int getMaxLength(int[] arr, int aim) {
        if (arr == null || arr.length < 1) {
            return 0;
        }

        int[] min_sum = new int[arr.length];
        int[] min_sum_index = new int[arr.length];
        min_sum[arr.length - 1] = arr[arr.length - 1];
        min_sum_index[arr.length - 1] = arr.length - 1;

        for (int i = arr.length - 2; i >= 0; i--) {
            if (min_sum[i + 1] <= 0) {
                min_sum[i] = min_sum[i + 1] + arr[i];
                min_sum_index[i] = min_sum_index[i + 1];
            } else {
                min_sum[i] = arr[i];
                min_sum_index[i] = i;
            }
        }

        int res = 0;
        int sum = 0;
        int L = 0;
        int R = 0;

        while (L < arr.length) {
            while (R < arr.length && sum + min_sum[R] <= aim) {
                sum += min_sum[R];
                R = min_sum_index[R] + 1;
            }

            res = Math.max(res, R - 1 - L + 1);

            sum -= R > L ? arr[L] : 0;

            R = Math.max(R, L + 1);

            L++;
        }

        return res;
    }

    // for test
    public static int maxLength(int[] arr, int k) {
        int[] h = new int[arr.length + 1];
        int sum = 0;
        h[0] = sum;
        for (int i = 0; i != arr.length; i++) {
            sum += arr[i];
            h[i + 1] = Math.max(sum, h[i]);
        }
        sum = 0;
        int res = 0;
        int pre = 0;
        int len = 0;
        for (int i = 0; i != arr.length; i++) {
            sum += arr[i];
            pre = getLessIndex(h, sum - k);
            len = pre == -1 ? 0 : i - pre + 1;
            res = Math.max(res, len);
        }
        return res;
    }

    public static int getLessIndex(int[] arr, int num) {
        int low = 0;
        int high = arr.length - 1;
        int mid = 0;
        int res = -1;
        while (low <= high) {
            mid = (low + high) / 2;
            if (arr[mid] >= num) {
                res = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return res;
    }

    // for test
    public static int[] generateRandomArray(int len, int maxValue) {
        int[] res = new int[len];
        for (int i = 0; i != res.length; i++) {
            res[i] = (int) (Math.random() * maxValue) - (maxValue / 3);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {100, 200, 7, -6, -3, 300};
        System.out.println(getMaxLength(arr, 7)); // 3

        boolean res = true;
        for (int i = 0; i < 1000000; i++) {
            int[] arr1 = generateRandomArray(10, 20);
            int k = (int) (Math.random() * 20) - 5;
            if (getMaxLength(arr1, k) != maxLength(arr1, k)) {
                res = false;
                break;
            }
        }
        System.out.println(res ? "Nice!" : "Oops!");
    }
}
