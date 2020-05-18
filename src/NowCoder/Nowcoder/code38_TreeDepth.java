package NowCoder.Nowcoder;

/*
    输入一棵二叉树，求该树的深度：
        从根结点到叶结点依次经过的结点（含根、叶结点）形成树的一条路径，最长路径的长度为树的深度。
    思路：深度优先 递归
 */
public class code38_TreeDepth {
    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }

    public static int TreeDepth(TreeNode root) {
        // 深度（层）可以理解为 当前层数 + 左右子树较大的层数 = max(左,右) + 1
        // 递归
        if(root == null){
            return 0;
        }

        int left = TreeDepth(root.left);
        int right = TreeDepth(root.right);
        // 向上返回
        return Math.max(left, right) + 1;
    }
}
