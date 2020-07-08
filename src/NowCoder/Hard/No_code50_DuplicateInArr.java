package NowCoder.Hard;

import java.util.HashSet;

/*
    数组中重复的数字：
        在一个长度为n的数组里的所有数字都在0到n-1的范围内。
        数组中某些数字是重复的，但不知道有几个数字是重复的。
        也不知道每个数字重复几次。请找出数组中任意一个重复的数字。
        例如，如果输入长度为7的数组{2,3,1,0,2,5,3}，那么对应的输出是第一个重复的数字2。
 */
public class No_code50_DuplicateInArr {
    /*
        TODO 扩展题：不修改数组找出重复的数字：
            长度为n+1的数组中所有数组都在 1-n 范围内，所以必有重复数字
            找出其中任一个重复数字，要求不能修改数组。

      方法一：
        借助原题的解题思路，通过辅助数组，将原数组中的数字复制到对应的位置上
        那么一定能找到某一位置是已存在数字的，即为重复的数字

        时间复杂度：O(N)
        空间复杂度：O(N)

      方法二：
        因为元素在1-N内，所以如果没有重复，数组长度应该只有N
        而现在数组长度为N+1，说明至少有一个是重复的
        那么可以统计某一范围内元素的个数，如果等于这个范围的长度，则可能不重复
        如果超过这个范围的长度，这个范围内必有重复
        不断二分，直到最后一个

        时间复杂度：O(NlogN)
        空间复杂度：O(1)

        TODO 这种方法只能找到一个，不能找到所有
         示例中统计1-2内的数字有2个，但是都是2，此时也会认为是不重复
     */
    public static int duplicateWithoutChange(int[] arr) {
        if (arr == null || arr.length < 1) {
            return -1;
        }

        // 元素在 1-N 内，所以start要从1开始
        int start = 1;
        int end = arr.length - 1;

        while (start <= end) {
            int mid = (start + end) >> 1;
            // 遍历arr数组，计算元素在 start-mid 范围内的个数
            int count = count(arr, start, mid);
            // 退出条件：start==end
            if (start == end && count > 1) {
                // TODO 用 start-end（1-N）来模拟数组中的元素
                return start;
            }
            // 二分：mid - start + 1是左半边应该有的元素个数，如果超过了说明左半边有重复的
            if (count > (mid - start + 1)) {
                end = mid;
            } else {
                start = mid + 1;
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

    /*
        规定了在 0 - N-1 范围内，所以如果没有重复的话，排序数组的每个元素都应该正好对应其索引
        即 第0号位置就是0，第1号位置就是1
        那么，我们可以每次将当前位置的元素交换到其对应的位置上
        在交换之前要先判断：当前位置的元素和应该对应的位置上的元素是否相同，如果相同，则是重复数字返回

        时间复杂度：O(N)
        空间复杂度：O(1)
     */
    public static int duplicate2(int[] arr) {
        if (arr == null || arr.length < 1) {
            return -1;
        }

        for (int i = 0; i < arr.length; i++) {
            while (arr[i] != i) {
                if (arr[i] == arr[arr[i]]) {
                    return arr[i];
                }
                swap(arr, i, arr[i]);
            }
        }

        return -1;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static boolean duplicate(int[] numbers, int length, int[] duplication) {
        if (numbers == null || numbers.length < 1) {
            return false;
        }

        HashSet<Integer> set = new HashSet<>();

        for (int i = 0; i < length; i++) {
            if (set.contains(numbers[i])) {
                duplication[0] = numbers[i];
                return true;
            } else {
                set.add(numbers[i]);
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 1, 0, 2, 5, 3};
        int[] res = new int[1];
        System.out.println(duplicate(arr, arr.length, res));
        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i]); // 2
        }

        System.out.println(duplicate2(arr)); // 2

        System.out.println("*********************");

        int[] arr1 = {2, 3, 5, 4, 3, 2, 6, 7};
        System.out.println(duplicateWithoutChange(arr1)); // 3
    }
}
