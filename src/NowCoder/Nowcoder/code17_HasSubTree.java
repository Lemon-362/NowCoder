package NowCoder.Nowcoder;

/*
    输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）
 */
public class code17_HasSubTree {
    public static class TreeNode {
        private int value;
        private TreeNode left;
        private TreeNode right;

        public TreeNode(int value) {
            this.value = value;
        }
    }

    // 首先头节点必须相同，然后才能往下比较
    public static boolean method(TreeNode root1, TreeNode root2) {
        boolean res = false;
        if (root1 != null && root2 != null) {
            // 只有当两个树的头节点相同时，才往下比较
            if (root1.value == root2.value) {
                res = isSubTree(root1, root2);
            }
            // 如果当前两个头节点不同，res肯定为false，则树1往左/右
            if (!res) {
                res = method(root1.left, root2);
            }
            if (!res) {
                res = method(root1.right, root2);
            }
        }
        return res;
    }

    // 递归
    public static boolean isSubTree(TreeNode root1, TreeNode root2) {
        // base case
        if (root2 == null) { // 树2遍历完都相同，则返回true，也可能是树2有一侧没有节点，也返回true
            return true;
        }
        if (root1 == null) { // 先遍历完树1，说明树2还有节点，肯定不是子结构，则返回false
            return false;
        }
        // 递归
        if (root1.value == root2.value) { // 对应的节点值相同，往左和右继续判断
            return isSubTree(root1.left, root2.left) && isSubTree(root1.right, root2.right);
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        TreeNode head = new TreeNode(1);
        head.left = new TreeNode(2);
        head.right = new TreeNode(3);
        head.left.left = new TreeNode(4);
        head.left.right = new TreeNode(5);
        head.left.left.left = new TreeNode(6);

        TreeNode head1 = new TreeNode(2);
        head1.left = new TreeNode(4);
//        head1.left.left = new TreeNode(6);
//        head1.left.left.left = new TreeNode(6);

        System.out.println(method(head, head1));
    }
}
