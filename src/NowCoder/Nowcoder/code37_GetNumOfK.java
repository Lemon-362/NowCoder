package NowCoder.Nowcoder;

/*
    统计一个数字在排序数组中出现的次数。
 */
public class code37_GetNumOfK {
    public static int GetNumberOfK(int[] arr, int k) {
        if (arr.length == 0) {
            return 0;
        }
        // 排序数组中，如果有很多相同元素，那么一定在一起，所以可以通过二分法，查找第一个和最后一个的索引
        // 通过下标计算就能得到相同元素的个数
        int firstIndex = getFirstIndex(arr, 0, arr.length - 1, k);
        int lastIndex = getLastIndex(arr, 0, arr.length - 1, k);

        int res = 0;
        if (firstIndex != -1 && lastIndex != -1) {
            res = lastIndex - firstIndex + 1;
        }

        return res;
    }

    // 获得第一个的下标：非递归
    public static int getFirstIndex(int[] arr, int start, int end, int k) {
        int len = arr.length;
        int mid = (start + end) >> 1;
        while (start <= end) {
            if (arr[mid] < k) { // 中间值小于k，向右
                start = mid + 1;
            } else if (arr[mid] > k) { // 中间值大于k，向左
                end = mid - 1;
            } else if ((arr[mid] == k) && (mid - 1 > -1) && (arr[mid - 1] == k)) { // 中间值等于k，且左边的一个仍为k，向左
                end = mid - 1;
            } else { // 中间值等于k，但左边的不等于k，只有一个k，则当前mid就是第一个
                return mid;
            }
            mid = (start + end) >> 1;
        }
        // 本身就不符合条件
        return -1;
    }

    // 获得最后一个的下标：递归
    public static int getLastIndex(int[] arr, int start, int end, int k) {
        // base case
        if (start > end) {
            return -1;
        }
        int mid = (start + end) >> 1;
        if (arr[mid] < k) {
            return getLastIndex(arr, mid + 1, end, k);
        } else if (arr[mid] > k) {
            return getLastIndex(arr, start, mid - 1, k);
        } else if ((mid + 1 < arr.length) && (arr[mid + 1] == k)) {
            return getLastIndex(arr, mid + 1, end, k);
        } else {
            return mid;
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 3, 3, 3, 4, 4, 5, 6};
        System.out.println(GetNumberOfK(arr, 3));
    }
}
