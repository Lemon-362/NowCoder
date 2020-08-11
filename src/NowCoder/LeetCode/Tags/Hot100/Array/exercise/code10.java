package NowCoder.LeetCode.Tags.Hot100.Array.exercise;

import java.util.ArrayList;
import java.util.List;

/*
39. 组合总和
    给定一个无重复元素的数组 candidates 和一个目标数 target ，找
    出 candidates 中所有可以使数字和为 target 的组合。
    TODO candidates 中的数字可以无限制重复被选取
说明：
    所有数字（包括 target）都是正整数。
    解集不能包含重复的组合

 */
public class code10 {

    public static List<List<Integer>> res = new ArrayList<>();

    public static List<List<Integer>> combinationSum(int[] arr, int aim) {
        if (arr == null || arr.length < 1) {
            return res;
        }

        process(arr, 0, aim, new ArrayList<>());

        return res;
    }

    public static void process(int[] arr, int index, int aim, List<Integer> list) {
        // base case
        if (aim == 0){
            res.add(new ArrayList<>(list));
            return;
        }

        for (int i = index; i < arr.length; i++) {
            if (arr[i] > aim){
                continue;
            }

            list.add(arr[i]);

            process(arr, i, aim - arr[i], list);

            list.remove(list.size() - 1);
        }
    }

    public static void main(String[] args) {

        int[] arr = {8, 7, 4, 3};

        List<List<Integer>> lists = combinationSum(arr, 11);

        for (List<Integer> list : lists) {
            for (Integer num : list) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
//        8 3
//        7 4
//        4 4 3
    }
}
