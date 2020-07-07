package NowCoder.LeetCode.Hot;

import java.util.ArrayList;
import java.util.List;

/**
 * 括号生成：
 *  数字N表示生成括号的对数，设计函数生成所有可能的q且有效的括号组合
 *
 * 思路：
 *  TODO 深度优先搜索（递归）的基本思想：
 *      1. 考虑能够到达左子树和右子树时的条件
 *      2. 考虑到达叶节点时剪枝的条件
 *
 *  1. 到达左子树的条件
 *      左子树代表左括号，只需要满足左括号的剩余数量 > 0即可
 *  2. 到达右子树的条件
 *      右子树代表右括号，需要满足右括号的剩余数量 > 0，并且左括号的剩余数量 < 右括号的剩余数量
 *  3. 剪枝的条件，即base case
 *      在左右括号的剩余数量为0时结算，将不满足1和2条件的分支剪掉
 *      base case：左右括号剩余数都为0
 *
 *  使用减法，每次使用一个括号，剩余数量 -1
 *  从头节点开始，头节点表示左右括号剩余N个可以使用
 *  逐层递归
 *
 */
public class code13_GenerateParenthesis {
    private static List<String> list = new ArrayList<>();

    public static List<String> generateParenthesis(int N){
        if (N <= 0) {
            return list;
        }

        // 深度优先搜索
        process(N, N, "");

        return list;
    }

    /**
     * 深度优先搜索：递归（回溯）+剪枝
     * @param leftNum 左括号剩余可使用数量
     * @param rightNum 右括号剩余可使用数量
     * @param res 到达叶节点时得到的结果，根据base case进行判断是否剪枝舍去
     */
    public static void process(int leftNum, int rightNum, String res){
        // base case
        // 当且仅当左右剩余可使用的数量都为0时，才是正确结果
        // 需要回溯return
        if (leftNum == 0 && rightNum == 0){
            list.add(res);
            return;
        }

        // 往左走，剪枝在进入该递归前就进行了判断
        if (leftNum > 0){
            process(leftNum - 1, rightNum, res + "(");
        }

        // 往右走，剪枝在进入该递归前就进行了判断
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
