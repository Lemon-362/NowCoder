package NowCoder.Nowcoder;

import java.util.HashSet;

/*
    数组中重复的数字：
        在一个长度为n的数组里的所有数字都在0到n-1的范围内。
        数组中某些数字是重复的，但不知道有几个数字是重复的。
        也不知道每个数字重复几次。请找出数组中任意一个重复的数字。
        例如，如果输入长度为7的数组{2,3,1,0,2,5,3}，那么对应的输出是第一个重复的数字2。
 */
public class code50_DuplicateInArr {
    /*
        TODO 扩展：不修改数组找出重复的数字：
            长度为n+1的数组中所有数组都在 1-n 范围内，所以必有重复数字
            找出其中任一个重复数字，要求不能修改数组。

     */


    /*
        规定了在 0 - N-1 范围内，所以如果没有重复的话，排序数组的每个元素都应该正好对应其索引
        即 第0号位置就是0，第1号位置就是1
        那么，我们可以每次将当前位置的元素交换到其对应的位置上
        在交换之前要先判断：当前位置的元素和应该对应的位置上的元素是否相同，如果相同，则是重复数字返回

        时间复杂度：O(N)
        空间复杂度：O(1)
     */
    public static int duplicate2(int[] arr){
        if (arr == null || arr.length < 1){
            return -1;
        }

        for (int i = 0; i < arr.length; i++) {
            while (arr[i] != i){
                if (arr[i] == arr[arr[i]]){
                    return arr[i];
                }
                swap(arr, i, arr[i]);
            }
        }

        return -1;
    }

    public static void swap(int[] arr, int i, int j){
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
            System.out.println(res[i]);
        }

        System.out.println(duplicate2(arr));
    }
}
