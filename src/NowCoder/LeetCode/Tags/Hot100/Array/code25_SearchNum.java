package NowCoder.LeetCode.Tags.Hot100.Array;

/*
33. 搜索旋转排序数组
    假设按照升序排序的数组在预先未知的某个点上进行了旋转。
    ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
    搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
    你可以假设数组中不存在重复的元素。
    你的算法时间复杂度必须是 O(log n) 级别

 */
public class code25_SearchNum {

    /*
    二分查找的变种:
        将数组划分成两部分, 分别讨论, 从而缩减范围

     */
    public static int search(int[] arr, int aim) {
        if (arr == null || arr.length < 1) {
            return -1;
        }

        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == aim) {
                return mid;
            }

            // 左半边数组
            if (arr[mid] >= arr[left]) {
                // 看aim在是否在左半边
                if (aim >= arr[left] && aim < arr[mid]) { // aim在左半边, 且aim在mid左边
                    right = mid - 1;
                } else { // aim在mid右边
                    left = mid + 1;
                }
            } else { // 右半边数组
                if (aim <= arr[right] && aim > arr[mid]) { // aim在mid右边
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {

        int[] arr = {4, 5, 6, 7, 0, 1, 2};

        System.out.println(search(arr, 0));
        System.out.println(search(arr, 3));

    }
}
