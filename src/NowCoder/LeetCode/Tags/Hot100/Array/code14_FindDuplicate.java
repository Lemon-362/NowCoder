package NowCoder.LeetCode.Tags.Hot100.Array;

/*
287. 寻找重复数
    长度为n+1的数组中所有数组都在 1-n 范围内，所以必有重复数字
    找出其中任一个重复数字，要求不能修改数组。
    TODO 剑指code50_DuplicateInArr的扩展题

    剑指code50_DuplicateInArr原题
        在一个长度为n的数组里的所有数字都在0到n-1的范围内。
        数组中某些数字是重复的，但不知道有几个数字是重复的。
        也不知道每个数字重复几次。请找出数组中任意一个重复的数字。
        例如，如果输入长度为7的数组{2,3,1,0,2,5,3}，那么对应的输出是第一个重复的数字2。

 */
public class code14_FindDuplicate {

    public static int duplicateWithoutChange(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }

        // 变换思路: 二分的 i,j 不在arr索引上变化, 而是在 1~N 上变化
        // 从而可以统计 1~mid 范围内的数的个数
        // arr.length = N + 1 ==> N = arr.length - 1
        int start = 1;
        int end = arr.length - 1;

        while (start <= end) {
            int mid = (start + end) >> 1;

            // 遍历arr数组，计算元素在 start-mid 范围内的个数
            int count = count(arr, start, mid);

            // 退出条件: 如果在arr中重复出现 i==j==mid 的值, 那么即为所求
            if (start == end && count > 1) {
                return start;
            }

            if (count == (mid - start + 1)){
                start = mid + 1;
            }else {
                end = mid;
            }
        }

        return -1;
    }

    // 遍历整个数组，计算数组arr中元素在 start-mid 范围内的个数
    public static int count(int[] arr, int start, int mid) {
        if (arr == null) {
            return 0;
        }

        int count = 0;
        // TODO 遍历整个数组
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= start && arr[i] <= mid) {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 3, 4, 2, 2};
        int[] arr2 = {3, 1, 3, 4, 2};

        System.out.println(duplicateWithoutChange(arr1)); // 2
        System.out.println(duplicateWithoutChange(arr2)); // 3
    }

}
