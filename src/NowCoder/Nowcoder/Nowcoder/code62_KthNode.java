package NowCoder.Nowcoder.Nowcoder;

import java.util.LinkedList;
import java.util.Queue;

/*
    给定一棵二叉搜索树，请找出其中的第k小的结点。
    例如， （5，3，7，2，4，6，8）中，按结点数值大小顺序第三小结点的值为4。
 */
public class code62_KthNode {
    public static class Node {
        private int val;
        private Node left;
        private Node right;

        public Node(int val) {
            this.val = val;
        }
    }

    public static Node KthNode(Node pRoot, int k) {
        if (pRoot == null || k == 0) {
            return null;
        }
//        Queue<Node> queue = new LinkedList<>();
//        inOrder(pRoot, queue);
        Queue<Node> queue = morrisIn(pRoot);
        Node cur = null;
        while (k > 0) {
            cur = queue.poll();
            k--;
        }
        return cur;
    }

    public static void inOrder(Node head, Queue<Node> queue) {
        if (head == null) {
            return;
        }
        inOrder(head.left, queue);
        queue.offer(head);
        inOrder(head.right, queue);
    }

    public static Queue<Node> morrisIn(Node head){
        if (head == null){
            return null;
        }

        Queue<Node> queue = new LinkedList<>();
        Node cur = head;
        Node mostRight = null;

        while (cur != null){
            mostRight = cur.left;
            if (mostRight != null){
                while (mostRight.right != null && mostRight.right != cur){
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null){
                    mostRight.right = cur;
                    cur = cur.left;
                }else {
                    mostRight.right = null;

                    queue.offer(cur);

                    cur = cur.right;
                }
            }else {
                queue.offer(cur);

                cur = cur.right;
            }
        }

        return queue;
    }

    public static void main(String[] args) {
        Node head = new Node(5);
        head.left = new Node(3);
        head.right = new Node(6);
        head.left.left = new Node(2);
        head.left.right = new Node(4);
        head.left.left.left = new Node(1);

        System.out.println(KthNode(head, 3).val); // 3
    }
}
