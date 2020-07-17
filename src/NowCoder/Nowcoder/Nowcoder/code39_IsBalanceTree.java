package NowCoder.Nowcoder.Nowcoder;

/*
    输入一棵二叉树，判断该二叉树是否是平衡二叉树。
 */
public class code39_IsBalanceTree {
    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }

    public boolean IsBalanced_Solution(TreeNode root) {
        // 平衡二叉树：对于每个节点，其左子树和右子树的高度差不超过1
        return process(root).isB;
    }

    public static class ReturnData {
        private boolean isB;
        private int height;

        public ReturnData(boolean isB, int height) {
            this.isB = isB;
            this.height = height;
        }
    }

    public static ReturnData process(TreeNode root) {
        if (root == null) {
            return new ReturnData(true, 0);
        }

        ReturnData left = process(root.left);
        if (!left.isB) {
            return new ReturnData(false, 0);
        }

        ReturnData right = process(root.right);
        if (!right.isB) {
            return new ReturnData(false, 0);
        }

        if (Math.abs(left.height - right.height) > 1) {
            return new ReturnData(false, 0);
        }

        return new ReturnData(true, Math.max(left.height, right.height) + 1);
    }
}
