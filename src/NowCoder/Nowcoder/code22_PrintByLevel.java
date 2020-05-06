package NowCoder.Nowcoder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/*
    从上往下打印出二叉树的每个节点，同层节点从左至右打印。
 */
public class code22_PrintByLevel {
    public static class TreeNode {
        private int val;
        private TreeNode left;
        private TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    // 实际上就是按层序列化的步骤
    public static ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        // 按层序列化
        ArrayList<Integer> list = new ArrayList<>();
        if (root == null) { // 如果头节点为空，直接返回空list
            return list;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        list.add(root.val);
        while (!queue.isEmpty()) {
            root = queue.poll();
            if (root.left != null) {
                list.add(root.left.val);
                queue.offer(root.left);
            }
            if (root.right != null) {
                list.add(root.right.val);
                queue.offer(root.right);
            }
        }
        return list;
    }

    public static void main(String[] args) {
        TreeNode head = new TreeNode(1);
        head.left = new TreeNode(3);
        head.right = new TreeNode(2);
        head.left.left = new TreeNode(4);
        head.right.left = new TreeNode(6);

        ArrayList<Integer> list = PrintFromTopToBottom(head);
        for (Integer num : list) {
            System.out.print(num + " ");
        }
    }
}
