package NowCoder.LeetCode.Tags.Hot100.Array;

import java.util.ArrayList;
import java.util.HashMap;
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
public class code03_FindDisapperedNumbers {

    /*
    方法一: HashMap

     */
    public static List<Integer> findDisappearedNumbers1(int[] arr){
        List<Integer> list = new ArrayList<>();
        if (arr == null || arr.length < 1) {
            return list;
        }

        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            set.add(arr[i]);
        }

        for (int i = 0; i < arr.length; i++) {
            if (!set.contains(i + 1)){
                list.add(i + 1);
            }
        }

        return list;
    }

    /*
    方法二: 类似剑指Offer_code50, 由于元素范围在 1~n, 且重复的元素出现了两次
        所以可以利用元素和其索引对应的关系
        但是又不能像code50一样交换, 因为会进入死循环

        将每个元素应该对应的位置上的元素 * -1
        那么, 对于缺失的数字, 对应位置上的元素一定没有处理过, 即为正

        然后再次遍历数组, 此时 1~n 是其索引, 只需要看此时的位置上元素是否 > 0

     */
    public static List<Integer> findDisappearedNumbers2(int[] arr) {
        List<Integer> list = new ArrayList<>();
        if (arr == null || arr.length < 1) {
            return list;
        }

        for (int i = 0; i < arr.length; i++) {
            // 将每个元素应该对应的位置进行处理, 如果缺失那么那个位置一定没有处理过
            int index = Math.abs(arr[i]) - 1;
            if (arr[index] > 0){
                arr[index] *= -1;
            }
        }

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 0){ // 元素大于0, 说明之前的处理中没有该索引对应的元素对该位置进行操作
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
