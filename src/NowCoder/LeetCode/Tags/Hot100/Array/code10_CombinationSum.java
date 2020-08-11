package NowCoder.LeetCode.Tags.Hot100.Array;

import java.util.ArrayList;
import java.util.List;

/*
39. 组合总和
    给定一个无重复元素的数组 candidates 和一个目标数 target ，找
    出 candidates 中所有可以使数字和为 target 的组合。
    candidates 中的数字可以无限制重复被选取
说明：
    所有数字（包括 target）都是正整数。
    解集不能包含重复的组合

 */
public class code10_CombinationSum {
    /*
    递归回溯 + 剪枝:
        本题和两数之和不同, 每个数可以使用无限次
        所以应该考虑递归回溯方法

        (1) 递归process:
            只考虑在当前层, 有多少种选择的方式
            然后用for循环遍历当前层的每一种选择方式, 并向下递归
        TODO 递归时只考虑当前层, 不能考虑上一层的选择
            因此, 需要用start变量, 表示从哪一层开始

        (2) 回溯:
            在添加完如果发现该选择不可用, 还需要回溯, remove掉该选择

        (3) 剪枝1:
            对于选完后aim < 0的情况, 应该直接舍去
        (4) TODO 剪枝2:
                如果当前层的一个选择的数已经 > aim, 那么就不应该再往下继续递归了
                应该直接continue跳过该选择

     */
    public static List<List<Integer>> res = new ArrayList<>();

    public static List<List<Integer>> combinationSum(int[] arr, int aim) {
        if (arr == null || arr.length < 1) {
            return res;
        }

        process(arr, 0, aim, new ArrayList<>());

        return res;
    }

    public static void process(int[] arr, int index, int aim,
                               List<Integer> list) {
        // base case
        if (aim == 0) {
            res.add(new ArrayList<>(list));
            return;
        }

        for (int i = index; i < arr.length; i++) {
            // 剪枝
            if (arr[i] > aim) {
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
    }
}
