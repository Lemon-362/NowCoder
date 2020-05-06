package NowCoder.Nowcoder;

import java.util.ArrayList;

/*
    二叉树中和为某一值的路径：
        输入一颗二叉树的根节点和一个整数，
        打印出二叉树中结点值的和为输入整数的所有路径。
        路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。
        (注意: 在返回值的list中，数组长度大的数组靠前)
 */
public class code24_FindPath {
    public static class TreeNode {
        private int val;
        private TreeNode left;
        private TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    static ArrayList<ArrayList<Integer>> res = new ArrayList<>();
    static ArrayList<Integer> path = new ArrayList<>();

    public static ArrayList<ArrayList<Integer>> method(TreeNode root, int target){
        if(root == null){ // 走到末尾了
            return res;
        }

        // 先将当前节点的值存入path中
        path.add(root.val);
        // target减小
        target -= root.val;

        // 如果target为0，且到末尾时，表示一条路径，就将path加到res中
        if (target == 0 && root.left == null && root.right == null){
            res.add(new ArrayList<Integer>(path));
        }

        // 否则的话，继续向左/右递归
        method(root.left, target);
        method(root.right, target);

        path.remove(path.size() - 1);

        return res;
    }

}
