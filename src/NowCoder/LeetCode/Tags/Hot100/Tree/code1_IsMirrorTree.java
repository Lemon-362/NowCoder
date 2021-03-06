package NowCoder.LeetCode.Tags.Hot100.Tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 101. 判断是否是堆成二叉树(镜像二叉树)
 * 剑指code58
 */
public class code1_IsMirrorTree {
    public static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    /**
     * TODO 宽度优先搜索
     *
     * 方法一: 递归版本
     *
     */
    public static boolean isMirrorTree1(Node head) {
        if (head == null) {
            return true;
        }

        return process(head.left, head.right);
    }

    public static boolean process(Node left, Node right) {
        // base case
        if (left == null && right == null) {
            return true;
        } else if (left == null || right == null) {
            return false;
        }

        if (left.value == right.value) {
            return process(left.left, right.right) && process(left.right, right.left);
        } else {
            return false;
        }
    }

    /**
     * 方法二: 迭代版本
     * TODO 注意: 使用Queue来求解时, 需要同时放入左右孩子进行比较, 而不是放入一个节点
     */
    public static boolean isMirrorTree2(Node head) {
        if (head == null) {
            return true;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.offer(head.left);
        queue.offer(head.right);

        while (!queue.isEmpty()) {
            Node left = queue.poll();
            Node right = queue.poll();

            if (left == null && right == null) {
                continue;
            } else if (left == null || right == null) {
                return false;
            }

            if (left.value != right.value) {
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
        System.out.println(isMirrorTree1(head1)); // false
        System.out.println(isMirrorTree2(head)); // true
        System.out.println(isMirrorTree2(head1)); // false
    }
}
