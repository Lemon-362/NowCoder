package NowCoder.Nowcoder.Nowcoder;

import java.util.ArrayList;

/*
    搜索二叉树转换成双向链表：
        输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。
        要求不能创建任何新的结点，只能调整树中结点指针的指向。
 */
public class code26_BSTConvertDoubleLinkedList {
    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }
    }

    public static TreeNode Convert(TreeNode head) {
        if (head == null) {
            return null;
        }
        ArrayList<TreeNode> list = new ArrayList<>();
        inOrder(head, list);
        return method(list);
    }

    public static void inOrder(TreeNode head, ArrayList<TreeNode> list) {
        if (head == null) {
            return;
        }
        inOrder(head.left, list);
        list.add(head);
        inOrder(head.right, list);
    }

    public static TreeNode method(ArrayList<TreeNode> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            list.get(i).right = list.get(i + 1);
            list.get(i + 1).left = list.get(i);
        }
        return list.get(0);
    }

    public static void main(String[] args) {
        TreeNode head = new TreeNode(4);
        head.left = new TreeNode(2);
        head.right = new TreeNode(5);
        head.left.left = new TreeNode(1);
        head.left.right = new TreeNode(3);

        TreeNode newHead = Convert(head);
        print(newHead);
    }

    public static void print(TreeNode head){
        if (head == null){
            return;
        }
        TreeNode cur = null;
        while (head != null){
            System.out.print(head.val + " ");
            cur = head;
            head = head.right;
        }
        System.out.println();
        while (cur != null){
            System.out.print(cur.val + " ");
            cur = cur.left;
        }
        System.out.println();
    }
}
