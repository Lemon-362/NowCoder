package NowCoder.Nowcoder.Nowcoder;

import java.util.Arrays;

/*
    输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。
        假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
        例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
        TODO 先序找头, 中序递归
 */
public class code04_ReconTreeByPreAndInOrder {
    public static class TreeNode {
        private int value;
        private TreeNode left;
        private TreeNode right;

        public TreeNode(int value) {
            this.value = value;
        }
    }

    //前序遍历{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}
    public static TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        // base case
        if (pre.length == 0 || in.length == 0) {
            return null;
        }
        // 1. 创建树的头节点（先序遍历的第一个就是头节点）
        TreeNode head = new TreeNode(pre[0]);
        // 2. 找到在中序遍历中的位置
        int mid = 0;
        for (int i = 0; i < in.length; i++) {
            if (in[i] == pre[0]) {
                mid = i;
                break;
            }
        }
        // 3. 中序遍历中头节点前的都是左子树，后的都是右子树，由此递归
        head.left = reConstructBinaryTree(Arrays.copyOfRange(pre, 1, mid + 1), Arrays.copyOfRange(in, 0, mid));
        head.right = reConstructBinaryTree(Arrays.copyOfRange(pre, mid + 1, pre.length), Arrays.copyOfRange(in, mid + 1, in.length));
        // 4. 返回树的头节点，形成递归
        return head;
    }

    public static TreeNode process(int[] pre, int lPre, int rPre, int[] in, int lIn, int rIn){
        // base case
        if (lPre > rPre || lIn > rIn){
            return null;
        }

        TreeNode head = new TreeNode(pre[lPre]);
        int mid = lIn;
        for (int i = lIn; i <= rIn; i++) {
            if (in[i] == pre[lPre]){
                mid = i;
                break;
            }
        }

        head.left = process(pre, lPre + 1, mid, in, lIn, mid - 1);
        head.right = process(pre, mid + 1, rPre, in, mid + 1, rIn);

        return head;
    }

    public static TreeNode reConstructBinaryTree2(int[] pre, int[] in){
        return process(pre, 0, pre.length - 1, in, 0, in.length - 1);
    }

    // print
    public static void printTree(TreeNode head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    public static void printInOrder(TreeNode head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "v", len);
        String val = to + head.value + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "^", len);
    }

    public static String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }

    public static void main(String[] args) {
        int[] pre = {1, 2, 4, 7, 3, 5, 6, 8};
        int[] in = {4, 7, 2, 1, 5, 3, 8, 6};
        TreeNode treeNode = reConstructBinaryTree(pre, in);
        printTree(treeNode);

        System.out.println("******************");
        printTree(reConstructBinaryTree2(pre, in));
    }
}

