package NowCoder.Nowcoder;

/*
    输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）
    TODO 这里判断的是 子结构!!! 而不是子树
    TODO 子树: 从某一节点往下的所有节点 进阶code03(序列化后使用KMP算法, 判断字符串是否包含子串)
            ==> 子树可以是子结构，但子结构不是子树
    TODO 子结构: 树里的一个分支, 不需要到叶节点
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

    // TODO 首先头节点必须相同，然后才能往下比较
    public static boolean method(TreeNode head1, TreeNode head2) {
        if (head1 == null || head2 == null){
            return false;
        }

        boolean res = false;

        if (head1.value == head2.value){ // 头节点相同, 则可以递归往下继续判断
            res = isSubTree(head1, head2);
        }else { // 头节点不同, 则让主树往下移动, 直到找到头节点相同的位置
            res = method(head1.left, head2) || method(head1.right, head2);
        }

        return res;
    }

    // 递归
    public static boolean isSubTree(TreeNode root1, TreeNode root2) {
        // base case
        if (root2 == null) { // 树2遍历完都相同，则返回true，也可能是树2有一侧没有节点，也返回true
            return true;
        }
        // 潜在前提: root2 != null
        if (root1 == null) { // 先遍历完树1，潜在前提:树2还有节点，肯定不是子结构，则返回false
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
