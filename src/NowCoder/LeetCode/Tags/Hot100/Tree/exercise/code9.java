package NowCoder.LeetCode.Tags.Hot100.Tree.exercise;

import java.util.LinkedList;
import java.util.Queue;

public class code9 {
    public static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static void flattenToList1(Node head) {
        if (head == null) {
            return;
        }

        process(head);
    }

    public static void process(Node head) {
        // base case
        if (head == null) {
            return;
        }

        process(head.left);
        process(head.right);

        Node right = head.right;
        head.right = head.left;
        head.left = null;

        while (head.right != null) {
            head = head.right;
        }

        head.right = right;
    }

    public static void flattenToList2(Node head) {
        if (head == null) {
            return;
        }

        Node cur = head;

        while (cur != null) {
            if (cur.left != null) {

                Node left = cur.left;

                while (left.right != null) {
                    left = left.right;
                }

                left.right = cur.right;

                cur.right = cur.left;
                cur.left = null;
            }

            cur = cur.right;
        }
    }

    public static void printTree(Node head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    public static void printInOrder(Node head, int height, String to, int len) {
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
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(5);
        head.left.left = new Node(3);
        head.left.right = new Node(4);
        head.right.right = new Node(6);

        printTree(head);

        flattenToList2(head);

        printTree(head);
    }
}
