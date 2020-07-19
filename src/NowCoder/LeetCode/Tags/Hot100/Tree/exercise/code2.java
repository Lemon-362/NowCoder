package NowCoder.LeetCode.Tags.Hot100.Tree.exercise;

import java.util.LinkedList;
import java.util.Queue;

public class code2 {
    public static class Node{
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static int maxDepth1(Node head){
        if (head == null){
            return 0;
        }

        return process(head);
    }

    public static int process(Node head){
        // base case
        if (head == null){
            return 0;
        }

        int leftDepth = process(head.left);
        int rightDepth = process(head.right);

        return Math.max(leftDepth, rightDepth) + 1;
    }

    public static int maxDepth2(Node head){
        if (head == null){
            return 0;
        }

        int depth = 0;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(head);

        while (!queue.isEmpty()){
            depth++;
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                Node cur = queue.poll();

                if (cur.left != null){
                    queue.offer(cur.left);
                }

                if (cur.right != null){
                    queue.offer(cur.right);
                }
            }
        }

        return depth;
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
        head.left.left.left = new Node(1);

        printTree(head);

        System.out.println(maxDepth1(head)); // 4
        System.out.println(maxDepth2(head)); // 4
    }
}
