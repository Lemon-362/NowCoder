package NowCoder.Nowcoder;

/*
    统计一个数字在排序数组中出现的次数。
        排序数组中，如果有很多相同元素，那么一定在一起，所以可以通过二分法，查找第一个和最后一个的索引
        通过下标计算就能得到相同元素的个数
    TODO 有序数组问题的解题思路：画折线图 + 二分法
 */
public class code37_GetNumOfK {
    public static int GetNumberOfK(int[] arr, int target) {

        if (arr == null || arr.length < 1) {
            return 0;
        }

        int i = 0;
        int j = arr.length - 1;
        while (i <= j) {
            int mid = (i + j) >> 1;

            if (arr[mid] > target) {
                j = mid - 1;
            } else if (arr[mid] < target) {
                i = mid + 1;
            } else {
                j = mid - 1;
            }
        }
        // 左边第一个索引
        int left = i;

        i = 0;
        j = arr.length - 1;
        while (i <= j) {
            int mid = (i + j) >> 1;

            if (arr[mid] > target) {
                j = mid - 1;
            } else if (arr[mid] < target) {
                i = mid + 1;
            } else {
                i = mid + 1;
            }
        }
        // 右边最后一个索引
        int right = j;

        return right - left + 1;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 3, 3, 3, 4, 4, 5, 6};
        System.out.println(GetNumberOfK(arr, 3)); // 4
    }
}
