package NowCoder.Nowcoder.exercise;

import NowCoder.Nowcoder.code18_MirrorTree;

import javax.print.attribute.standard.NumberUp;
import java.util.LinkedList;
import java.util.Queue;

public class code18 {
    public static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static void mirrorTree(Node head){
        if (head == null){
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.offer(head);

        while (!queue.isEmpty()){
            Node cur = queue.poll();
            Node left = cur.left;
            Node right = cur.right;

            cur.left = right;
            cur.right = left;

            if (left != null){
                queue.offer(left);
            }
            if (right != null){
                queue.offer(right);
            }
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
        Node head = new Node(8);
        head.left = new Node(6);
        head.right = new Node(10);
        head.left.left = new Node(5);
        head.left.right = new Node(7);
        head.right.left = new Node(9);
        head.right.right = new Node(11);

        printTree(head);
        mirrorTree(head);
        printTree(head);
    }
}
