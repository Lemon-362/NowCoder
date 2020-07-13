package NowCoder.basic_class.exercise.exercise02;

import java.util.LinkedList;
import java.util.Queue;

public class code29 {
    public static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static boolean isBST(Node head) {
        if (head == null) {
            return true;
        }

        Node cur = head;
        Node mostRight = null;
        Node pre = null;

        while (cur != null){
            mostRight = cur.left;
            if (mostRight != null){
                while (mostRight.right != null && mostRight.right != cur){
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;

                    cur = cur.left;
                }else {
                    mostRight.right = null;

                    if (pre != null && pre.value >= cur.value){
                        return false;
                    }
                    pre = cur;

                    cur = cur.right;
                }
            }else {
                if (pre != null && pre.value >= cur.value){
                    return false;
                }
                pre = cur;

                cur = cur.right;
            }
        }

        return true;
    }

    public static boolean isCBT(Node head) {
        if (head == null) {
            return true;
        }

        boolean res = true;
        boolean flag = false;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(head);

        while (!queue.isEmpty()){
            Node cur = queue.poll();
            Node left = cur.left;
            Node right = cur.right;

            if ((flag && (left != null || right != null))
            || (left == null && right != null)){
                res = false;
                break;
            }

            if (left == null || right == null){
                flag = true;
            }

            if (left != null){
                queue.offer(left);
            }
            if (right != null){
                queue.offer(right);
            }
        }

        return res;
    }

    public static void main(String[] args) {
        Node head = new Node(4);
        head.left = new Node(2);
        head.right = new Node(6);
        head.left.left = new Node(1);
        head.left.right = new Node(3);
        head.right.left = new Node(5);

        System.out.println(isBST(head));
        System.out.println(isCBT(head));
    }
}
