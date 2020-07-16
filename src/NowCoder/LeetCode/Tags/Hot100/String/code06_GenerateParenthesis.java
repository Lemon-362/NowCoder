package NowCoder.LeetCode.Tags.Hot100.String;

import java.util.ArrayList;
import java.util.List;

/**
 * 括号生成：
 *  数字N表示生成括号的对数，设计函数生成所有可能的q且有效的括号组合
 *
 */
public class code06_GenerateParenthesis {
    public static List<String> list = new ArrayList<>();
    /**
     * TODO 和String.code04_LetterCombinations类似，只需要处理当前层，剩下的交给递归处理
     *      但是不同的是，这里需要进行剪枝，只有满足条件的才能放入list中
     *
     * TODO 递归 + 剪枝 + 二叉树
     *      逐层进行判断，并在最后进行剪枝处理
     *      实际上就是一个二叉树，每一层的分支都有两个可能 ( 和 )
     *
     * TODO 剪枝操作, 可以看成是在进入下层递归分支前的判断, 只有满足条件才能从当前分支进入下级分支
     *
     *  1. 给定N时，可以看成是 ( 和 ) 还剩N个没有使用
     *  2. 不满足条件的情况：(剩余数量 >= )剩余数量
     *     TODO 即要求：左括号的剩余数量 < 右括号剩余数量
     *      等于的时候也不行, 例如: 当(和)剩余数量都为3时, 就必须进入左分支(, 而不能进入右分支)
     *  3. base case：
     *      (剩余数量 和 )剩余数量 都为0时，由于在递归的过程中就进行了剪枝
     *      所以到达base case时一定都是符合要求的，直接放入list中
     *  4. 当前层不需要处理，直接根据分支进入下层递归
     *  (1) 要左括号: 直接加上"("
     *  (2) 要右括号: 只有当左括号的剩余数量 < 右括号剩余数量时, 才能加上")"
     *
     *
     */
    public static List<String> generateParenthesis(int N){
        if (N <= 0){
            return null;
        }

        process(N, N, "");

        return list;
    }

    public static void process(int leftNum, int rightNum, String res){
        // base case
        if (leftNum == 0 && rightNum == 0){
            list.add(res);
            return;
        }

        // 1. 要左括号(，不需要条件
        if (leftNum > 0) {
            process(leftNum - 1, rightNum, res + "(");
        }

        // 2. 要右括号)，此时必须满足：左括号的剩余数量 < 右括号剩余数量
        if (rightNum > 0 && leftNum < rightNum){
            process(leftNum, rightNum - 1, res + ")");
        }
    }

    public static void main(String[] args) {
        List<String> list = generateParenthesis(3);
        for (String s : list) {
            System.out.println(s);
        }
//        ((()))
//        (()())
//        (())()
//        ()(())
//        ()()()
    }
}
