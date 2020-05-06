package NowCoder.Nowcoder;

/*
    请实现一个函数，用来判断一颗二叉树是不是对称的。
    注意，如果一个二叉树同此二叉树的镜像是同样的，定义其为对称的。
    也就是说，以头节点为中心的对称轴，左右是镜像的。
 */
public class code58_IsMirrorTree {
    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }

    public static boolean isMirrorTree(TreeNode pRoot) {
        if (pRoot == null) {
            return true;
        }
        // 一层一层的比较每个节点，递归
        return method(pRoot.left, pRoot.right);
    }

    public static boolean method(TreeNode node1, TreeNode node2) {
        // 边界
        if (node1 == null && node2 == null) {
            return true;
        } else if (node1 == null || node2 == null) {
            return false;
        }
        // base case
        if (node1.val != node2.val) {
            return false;
        } else { // 如果左右子树值相同，向下递归，看他们的左子树和右子树是否是镜像
            return method(node1.left, node2.right) && method(node1.right, node2.left);
        }
    }
}
