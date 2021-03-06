package NowCoder.LeetCode.Tags.Hot100.Array.exercise;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/*
448. 找到数组中所有消失的数字
    给定一个范围在  1 ≤ a[i] ≤ n ( n = 数组大小 ) 的 整型数组，
    数组中的元素一些出现了两次，另一些只出现一次。
    找到所有在 [1, n] 范围之间没有出现在数组中的数字。
    您能在不使用额外空间且时间复杂度为O(n)的情况下完成这个任务吗?
    你可以假定返回的数组不算在额外空间内

 */
public class code03 {
    public static List<Integer> findDisappearedNumbers1(int[] arr){
        List<Integer> list = new ArrayList<>();
        if (arr == null || arr.length < 1) {
            return list;
        }

        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            set.add(arr[i]);
        }

        for (int i = 1; i <= arr.length; i++) {
            if (!set.contains(i)){
                list.add(i);
            }
        }

        return list;
    }

    public static List<Integer> findDisappearedNumbers2(int[] arr) {
        List<Integer> list = new ArrayList<>();
        if (arr == null || arr.length < 1) {
            return list;
        }

        for (int i = 0; i < arr.length; i++) {
            int index = Math.abs(arr[i]) - 1;
            if (arr[index] > 0){
                arr[index] *= -1;
            }
        }

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 0){
                list.add(i + 1);
            }
        }

        return list;
    }

    public static void main(String[] args) {
        int[] arr = {4, 3, 2, 7, 8, 2, 3, 1};

        List<Integer> list = findDisappearedNumbers2(arr);
        for (Integer num : list) {
            System.out.print(num + " "); // 5 6
        }

    }
}
