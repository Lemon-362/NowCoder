package NowCoder.LeetCode.Tags.Hot100.Array;

import java.util.HashMap;

/*
169. 多数元素
    给定一个大小为 n 的数组，找到其中的多数元素。
    多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
    你可以假设数组是非空的，并且给定的数组总是存在多数元素。

TODO 剑指Offer的code28_MoreThanHalfNum
 */
public class code01_MajorityElement {

    /*
    方法一: HashMap记录出现次数

        时间复杂度: O(N)
        空间复杂度: O(N)
     */
    public static int majorityElement1(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            if (!map.containsKey(arr[i])) {
                map.put(arr[i], 1);
            } else {
                int times = map.get(arr[i]) + 1;

                if (times > arr.length / 2) {
                    return arr[i];
                }

                map.put(arr[i], times);
            }
        }

        return 0;
    }

    /*
    方法二: 擂台法
        用一个变量记录当前数出现的次数
        因为超过一半的那个数的出现次数, 可以用来抵消其他数
        当遍历完后, 次数一定是>0的, 也就找到了那个数

        时间复杂度: O(N)
        空间复杂度: O(1)
     */
    public static int majorityElement2(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }

        int res = arr[0];
        int times = 1;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == res){
                times++;
            }else {
                times--;

                if (times == 0){
                    times = 1;
                    res = arr[i];
                }
            }
        }

        times = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == res){
                times++;
            }
        }

        return times > arr.length / 2 ? res : 0;
    }

    /*
    方法三: 出现次数 > n/2 ==> 排序后一定是在中间的 ==> 找第k(n/2)小的数

        时间复杂度: O(N)
     */
    public static int majorityElement3(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }

        int res = bfprt(arr, 0, arr.length - 1, arr.length / 2);

        int times = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == res) {
                times++;
            }
        }

        return times > arr.length / 2 ? res : 0;
    }

    public static int bfprt(int[] arr, int l, int r, int k) {
        // base case
        if (l == r) {
            return arr[l];
        }

        int num = medianInMedians(arr, l, r);

        int[] p = partition(arr, l, r, num);

        if (k >= p[0] && k <= p[1]) {
            return num;
        } else if (k < p[0]) {
            return bfprt(arr, l, p[0] - 1, k);
        } else {
            return bfprt(arr, p[1] + 1, r, k);
        }
    }

    public static int[] partition(int[] arr, int l, int r, int num) {
        int less = l - 1;
        int more = r + 1;
        int cur = l;

        while (cur < more) {
            if (arr[cur] < num) {
                swap(arr, ++less, cur++);
            } else if (arr[cur] > num) {
                swap(arr, --more, cur);
            } else {
                cur++;
            }
        }

        return new int[]{less + 1, more - 1};
    }

    public static int medianInMedians(int[] arr, int l, int r) {
        int len = r - l + 1;
        int offset = len % 5 == 0 ? 0 : 1;
        int group = len / 5 + offset;
        int[] mArr = new int[group];

        for (int i = 0; i < mArr.length; i++) {
            int start = l + 5 * i;
            int end = start + 4;

            if (i == mArr.length - 1) {
                mArr[i] = getMedian(arr, start, r);
            } else {
                mArr[i] = getMedian(arr, start, end);
            }
        }

        return bfprt(mArr, 0, mArr.length - 1, mArr.length / 2);
    }

    public static int getMedian(int[] arr, int l, int r) {
        insertSort(arr, l, r);
        int mid = (l + r) >> 1;

        return arr[mid];
    }

    public static void insertSort(int[] arr, int l, int r) {
        for (int i = l + 1; i <= r; i++) {
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
                swap(arr, j, j + 1);
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 2, 4, 2, 5, 2, 3};
        System.out.println(majorityElement1(arr)); // 0
        System.out.println(majorityElement2(arr));
        System.out.println(majorityElement3(arr));

        System.out.println("*******************");

        int[] arr1 = {1, 2, 3, 2, 2, 2, 5, 4, 2};
        System.out.println(majorityElement1(arr1)); // 2
        System.out.println(majorityElement2(arr1));
        System.out.println(majorityElement3(arr1));
    }
}
