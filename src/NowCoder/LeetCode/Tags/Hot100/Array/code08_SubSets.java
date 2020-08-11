package NowCoder.LeetCode.Tags.Hot100.Array;

import java.util.ArrayList;
import java.util.List;

/*
78. 子集
    给定一组不含重复元素的整数数组 arr，返回该数组所有可能的子集（幂集）。
    说明：解集不能包含重复的子集。

 */
public class code08_SubSets {
    /*
    字符串的子序列
        TODO 在递归回溯算法中, 如果process的参数是List, 而不是String
            (1) 在process递归中, list.add一个元素后,
                在回溯时(process递归函数退出时),
                应该list.remove(list.size() - 1)移出最后一个元素
                这样才能回溯到上一个节点处
            (2) 在process的base case中, 因为共用的一个list
                所以应该用res.add(new ArrayList<>(list)), 而不是直接res.add(list)
     */
//    public static List<List<Integer>> res = new ArrayList<>();

    public static List<List<Integer>> subSets(int[] arr) {
        List<List<Integer>> res = new ArrayList<>();

        process(arr, 0, new ArrayList<>(), res);

        return res;
    }

    public static void process(int[] arr, int index, List<Integer> list, List<List<Integer>> res) {
        // base case
        if (index == arr.length) {
            res.add(new ArrayList<>(list));
            return;
        }

        process(arr, index + 1, list, res);

        list.add(arr[index]);
        process(arr, index + 1, list, res);

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
    }
}
