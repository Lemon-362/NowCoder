package NowCoder.LeetCode.Tags.Hot100.Array.exercise;

import java.util.ArrayList;
import java.util.List;

/*
78. 子集
    给定一组不含重复元素的整数数组 arr，返回该数组所有可能的子集（幂集）。
    说明：解集不能包含重复的子集。

 */
public class code08 {
    public static List<List<Integer>> res = new ArrayList<>();

    public static List<List<Integer>> subSets(int[] arr) {
        if (arr == null || arr.length < 1){
            return res;
        }

        process(arr, 0, new ArrayList<>());

        return res;
    }

    public static void process(int[] arr, int index, List<Integer> list){
        // base case
        if (index == arr.length){
            res.add(new ArrayList<>(list));
            return;
        }

        process(arr, index + 1, list);

        list.add(arr[index]);
        process(arr, index + 1, list);
        list.remove(list.size() - 1);
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3};

        List<List<Integer>> lists = subSets(arr);

        for (List<Integer> list : lists) {
            for (Integer num : list) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
//
//        3
//        2
//        2 3
//        1
//        1 3
//        1 2
//        1 2 3
    }

}
