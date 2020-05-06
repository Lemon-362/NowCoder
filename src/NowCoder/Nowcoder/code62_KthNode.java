package NowCoder.Nowcoder;

import java.util.LinkedList;
import java.util.Queue;

/*
    给定一棵二叉搜索树，请找出其中的第k小的结点。
    例如， （5，3，7，2，4，6，8）    中，按结点数值大小顺序第三小结点的值为4。
 */
public class code62_KthNode {
    public static class TreeNode {
        private int val;
        private TreeNode left;
        private TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static TreeNode KthNode(TreeNode pRoot, int k)
    {
        if(pRoot == null || k == 0){
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        inOrder(pRoot, queue);
        TreeNode cur = null;
        while(k > 0){
            cur = queue.poll();
            k--;
        }
        return cur;
    }

    public static void inOrder(TreeNode head, Queue<TreeNode> queue){
        if(head == null){
            return;
        }
        inOrder(head.left, queue);
        queue.offer(head);
        inOrder(head.right, queue);
    }

    public static void main(String[] args) {

    }
}
