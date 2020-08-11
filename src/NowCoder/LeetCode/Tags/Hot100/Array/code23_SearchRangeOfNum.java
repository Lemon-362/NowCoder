package NowCoder.LeetCode.Tags.Hot100.Array;

/*
34. 在排序数组中查找元素的范围
    给定一个按照升序排列的整数数组 nums，和一个目标值 target。
    找出给定目标值在数组中的开始位置和结束位置。
    你的算法时间复杂度必须是 O(log n) 级别。
    如果数组中不存在目标值，返回 [-1, -1]

    TODO 数组会有重复元素
 */
public class code23_SearchRangeOfNum {

    /*
    二分查找: 寻找左右边界
        TODO 题目限制时间复杂度必须是 O(log n) 级别 ==> 二分法

     */
    public static int[] searchRange(int[] arr, int aim) {
        if (arr == null || arr.length < 1) {
            return new int[]{-1, -1};
        }

        int i = 0;
        int j = arr.length - 1;
        int left = 0;
        int right = 0;

        while (i <= j) {
            int mid = i + (j - i) / 2;

            if (arr[mid] == aim) {
                j = mid - 1;
            } else if (arr[mid] < aim) {
                i = mid + 1;
            } else if (arr[mid] > aim) {
                j = mid - 1;
            }
        }
        if (i >= arr.length || arr[i] != aim) {
            left = -1;
        } else {
            left = i;
        }

        i = 0;
        j = arr.length - 1;
        while (i <= j) {
            int mid = i + (j - i) / 2;

            if (arr[mid] == aim) {
                i = mid + 1;
            } else if (arr[mid] < aim) {
                i = mid + 1;
            } else if (arr[mid] > aim) {
                j = mid - 1;
            }
        }
        if (j < 0 || arr[j] != aim) {
            right = -1;
        } else {
            right = j;
        }

        return new int[]{left, right};
    }

    public static void main(String[] args) {
        int[] arr = {5, 7, 7, 8, 8, 10};

        int[] res = searchRange(arr, 8);
        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i]);
            // aim=8: 3 4
            // aim=6: -1 -1
        }
    }
}
