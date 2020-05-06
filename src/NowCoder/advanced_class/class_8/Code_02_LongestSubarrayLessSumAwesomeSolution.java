package NowCoder.advanced_class.class_8;

import java.util.HashMap;

/*
    累加和等于aim的最长子数组：
		给定一个数组arr，可正可负可0，和一个整数num
		求累加和小于等于aim的最长子数组，要求时间复杂度O(N)
		TODO 小于等于
 */
public class Code_02_LongestSubarrayLessSumAwesomeSolution {
    // O(N)
    /*
        1. 得到min_sum和min_sun_index: 以某一位置开始往右加出的最小累加和,出现最小累加和时的位置
        2. L指针从0位置开始, R指针指向0位置对应的min_sum_index
        3. 若0位置对应的min_sum <= aim, 则看R(min_sum_index)+1位置
           此时R指针指向该位置对应的min_sum_index
        4. 若0位置对应的min_sum + 该位置对应的min_sum_index的min_sum <= aim,重复2和3
        5. 若某一时刻累加和sum > aim, 此时 0 -- T | T+1 -- N上, 0-T是满足条件的子数组
           L右移一个, sum = sum - arr[0], 让T+1开始逐个加入sum中,看是否 <= aim
        6. 如果某一时刻不符合,则再让L右移一个,再重复5
    */
    public static int maxLengthAwesome(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[] min_sum = new int[arr.length]; // min_sum
        int[] min_sum_index = new int[arr.length]; // min_sum_index
        min_sum[arr.length - 1] = arr[arr.length - 1]; // 最后一个位置的最小sum
        min_sum_index[arr.length - 1] = arr.length - 1; // 最后一个位置的右边界
        // 从后往前,计算每个位置的min_sum和min_sum_index
        for (int i = arr.length - 2; i >= 0; i--) { // 从右往左算min_sum
            if (min_sum[i + 1] < 0) { // 如果后一个位置的min_sum的值<0，则可以扩进来
                min_sum[i] = arr[i] + min_sum[i + 1];
                min_sum_index[i] = min_sum_index[i + 1];
            } else { // 如果>0，则最小sum只能是自己
                min_sum[i] = arr[i];
                min_sum_index[i] = i;
            }
        }

        /*
            1. 得到min_sum和min_sun_index: 以某一位置开始往右加出的最小累加和,出现最小累加和时的位置
            2. L指针从0位置开始, R指针指向0位置对应的min_sum_index
            3. 若0位置对应的min_sum <= aim, 则看R(min_sum_index)+1位置
               此时R指针指向该位置对应的min_sum_index
            4. 若0位置对应的min_sum + 该位置对应的min_sum_index的min_sum <= aim,重复2和3
            5. 若某一时刻累加和sum > aim, 此时 0 -- T | T+1 -- N上, 0-T是满足条件的子数组
               L右移一个, sum = sum - arr[0], 让T+1开始逐个加入sum中,看是否 <= aim
               TODO 也就是以L开头的子数组找到了,然后L右移,往后找
            6. 如果某一时刻不符合,则再让L右移一个,再重复5 TODO 重复5相当于重复234
        */

        int R = 0; // 可以扩到的右边界 TODO R也在数组索引上移动,而不在min_sum_index上移动
        int sum = 0;
        int len = 0;
        for (int L = 0; L < arr.length; L++) {
            while (R < arr.length && sum + min_sum[R] <= aim) { // R不越界，且往右扩还是<=aim
                sum += min_sum[R];
                R = min_sum_index[R] + 1;
            }
            // 此时L - R-1(T)是满足条件的，而R(T+1)位置是不满足的
            // T = R - 1; T + 1 = R
            sum -= R > L ? arr[L] : 0; // 如果R在L右边，因为下一循环L++，也就是将L位置的数排除出去，
            // 所以sum要减arr[L]
            len = Math.max(len, R - L); // 满足条件的子数组长度 [L, R-1] = R-1-L+1
            R = Math.max(R, L + 1); // 防止第一个数就大于了aim，R不能包含自己位置
        }
        return len;
    }

    // O(N*logN)
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
//        for (int i = 0; i < 1000000; i++) {
//            int[] arr = generateRandomArray(10, 20);
//            int k = (int) (Math.random() * 20) - 5;
//            if (maxLengthAwesome(arr, k) != maxLength(arr, k)) {
//                System.out.println("oops!");
//            }
//        }
        int[] arr = {100, 200, 7, -6, -3, 300};
        System.out.println(maxLengthAwesome(arr, 7)); // 3
        System.out.println(maxLength(arr, 7)); // 3

    }

}
