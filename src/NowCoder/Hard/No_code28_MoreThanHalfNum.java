package NowCoder.Hard;

import java.util.HashMap;

/*
    数组中超过长度一半的数字：
        数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
        例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。
        由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。如果不存在则输出0。
 */
public class No_code28_MoreThanHalfNum {
    public static int method(int[] array) {
        // 最多只可能有一个值超过一半
        HashMap<Integer, Integer> map = new HashMap<>();
//        int index = 0;
        for (int i = 0; i < array.length; i++) {
            if (!map.containsKey(array[i])) {
                map.put(array[i], 1);
            } else {
                int index = map.get(array[i]) + 1;
                if (index > array.length / 2) {
                    return array[i];
                } else {
                    map.put(array[i], index);
                }
//                map.put(array[i], index + 1);
            }
        }
        // 遍历map的key，判断对应的value是否大于长度的一半
//        int halfLen = array.length / 2;
//        for (Integer key : map.keySet()) {
//            if (map.get(key) > halfLen) {
//                return key;
//            }
//        }
        return 0;
    }

    /**
     * 方法一:
     * 数组中有一个数字出现的次数超过数组长度的一半
     * ==> 排序之后位于数组中间的数字一定是这个数
     * ==> 寻找中位数
     * ==> 题目转换为: 在无序数组中寻找第k大/小的数
     * ==> k=n/2
     * ==> partition算法, 每次随机选取一个作为划分值, 每经历一次partition, 就看一下等于区域的索引是否包含了n/2的位置
     * 但是由于每次都是随机选择的划分值, 所以时间复杂度为 长期期望O(N)
     * ==> BFPRT算法, 严格O(N) 或者 heap堆, 找最小的k个数
     */
    public static int getKthMinNum(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }

        int k = arr.length / 2;

        int res = bfprt(arr, 0, arr.length - 1, k);

        int times = 0;
        // TODO 边界情况, 防止中位数没有超过一半
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == res) {
                times++;
            }
        }

        return times > k ? res : 0;
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

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 方法二:
     * 数组中有一个数字出现的次数超过数组长度的一半
     * ==> 该数的出现次数 > 其他数出现次数之和
     * ==> 统计出现次数
     * ==> 用HashMap ==> 空间复杂度O(N)
     * ==> 用times变量记录出现次数, 相同则+1, 不同则-1
     *     遍历结束后, 如果有值, 则times一定是大于0的, 此时要看res是否满足超过一半的条件
     */
    public static int getKthMinNum1(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }

        int res = arr[0];
        int times = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == res) {
                times++;
            } else {
                times--;
                // 当times减为0时, 要更换数字, 并重置times为1
                if (times == 0) {
                    res = arr[i];
                    times = 1;
                }
            }
        }

        times = 0;
        // TODO 边界情况, 防止中位数没有超过一半
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == res) {
                times++;
            }
        }

        return times > arr.length / 2 ? res : 0;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 2, 4, 2, 5, 2, 3};
        System.out.println(method(arr)); // 0
        System.out.println(getKthMinNum(arr));
        System.out.println(getKthMinNum1(arr));

        System.out.println("*******************");

        int[] arr1 = {1, 2, 3, 2, 2, 2, 5, 4, 2};
        System.out.println(method(arr1)); // 2
        System.out.println(getKthMinNum(arr1));
        System.out.println(getKthMinNum1(arr1));
    }
}
