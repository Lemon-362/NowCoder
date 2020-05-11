package NowCoder.Nowcoder;

import java.util.LinkedList;
import java.util.Queue;

/*
    操作给定的二叉树，将其变换为原二叉树的镜像。
 */
public class code18_MirrorTree {
    public static class TreeNode {
        private int val;
        private TreeNode left;
        private TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    // 广度优先搜索：一层一层的交换左右孩子
    public static void method(TreeNode root) {
        if (root == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        TreeNode cur = null;
        TreeNode tmp = null;
        while (!queue.isEmpty()) {
            while (queue.size() != 0) {
                cur = queue.poll();

                // TODO 先存, 再交换
                if (cur.left != null) {
                    queue.offer(cur.left);
                }

                if (cur.right != null) {
                    queue.offer(cur.right);
                }

                tmp = cur.left;
                cur.left = cur.right;
                cur.right = tmp;
            }
        }
    }

    public static void main(String[] args) {
        TreeNode head = new TreeNode(8);
        head.left = new TreeNode(6);
        head.right = new TreeNode(10);
        head.left.left = new TreeNode(5);
        head.left.right = new TreeNode(7);
        head.right.left = new TreeNode(9);
        head.right.right = new TreeNode(11);

        printTree(head);
        method(head);
        printTree(head);
    }

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
        String val = to + head.val + to;
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
}
