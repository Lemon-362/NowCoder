package NowCoder.LeetCode.Tags.Hot100.Tree.exercise;

import java.util.LinkedList;
import java.util.Queue;

public class code1 {
    public static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static boolean isMirrorTree1(Node head) {
        if (head == null) {
            return true;
        }

        return process(head.left, head.right);
    }

    public static boolean process(Node left, Node right){
        // base case
        if (left == null && right == null){
            return true;
        }else if (left == null || right == null){
            return false;
        }

        if (left.value == right.value){
            return process(left.left, right.right) && process(left.right, right.left);
        }else {
            return false;
        }
    }

    public static boolean isMirrorTree2(Node head) {
        if (head == null) {
            return true;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.offer(head.left);
        queue.offer(head.right);

        while (!queue.isEmpty()){
            Node left = queue.poll();
            Node right = queue.poll();

            if (left == null && right == null){
                return true;
            }else if (left == null || right == null){
                return false;
            }

            if (left.value != right.value){
                return false;
            }

            queue.offer(left.left);
            queue.offer(right.right);

            queue.offer(left.right);
            queue.offer(right.left);
        }

        return true;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(2);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(5);
        head.right.right = new Node(4);

        Node head1 = new Node(1);
        head1.left = new Node(2);
        head1.right = new Node(2);
        head1.left.left = new Node(4);
        head1.left.right = new Node(3);
        head1.right.left = new Node(5);
        head1.right.right = new Node(4);

        System.out.println(isMirrorTree1(head)); // true
        System.out.println(isMirrorTree2(head)); // true
        System.out.println(isMirrorTree1(head1)); // false
        System.out.println(isMirrorTree2(head1)); // false
    }
}
