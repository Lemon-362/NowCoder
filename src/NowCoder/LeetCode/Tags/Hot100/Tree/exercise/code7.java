package NowCoder.LeetCode.Tags.Hot100.Tree.exercise;

import java.util.LinkedList;
import java.util.Queue;

public class code7 {
        public static class Node{
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node mergeTrees1(Node head1, Node head2){
        if (head1 == null && head2 == null){
            return null;
        }else if (head1 == null){
            return head2;
        }else if (head2 == null){
            return head1;
        }

        return process(head1, head2);
    }

    // 按层遍历，宽度优先
    public static Node process(Node head1, Node head2){
        // base case
        if (head1 == null && head2 == null){
            return null;
        }else if (head1 == null){
            return head2;
        }else if (head2 == null){
            return head1;
        }

        head1.value += head2.value;

        head1.left = process(head1.left, head2.left);
        head1.right = process(head1.right, head2.right);

        return head1;
    }

    public static Node mergeTrees2(Node head1, Node head2){
        if (head1 == null && head2 == null){
            return null;
        }else if (head1 == null){
            return head2;
        }else if (head2 == null){
            return head1;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.offer(head1);
        queue.offer(head2);

        while (!queue.isEmpty()){
            Node node1 = queue.poll();
            Node node2 = queue.poll();

            if (node1 == null && node2 == null){
                continue;
            }else if (node1 == null){
                node1 = node2;
            }else if (node2 == null){
                continue;
            }else {
                node1.value += node2.value;
            }

            if (node2.left != null){
                if (node1.left == null){
                    node1.left = node2.left;
                }else {
                    queue.offer(node1.left);
                    queue.offer(node2.left);
                }
            }

            if (node2.right != null){
                if (node1.right == null){
                    node1.right = node2.right;
                }else {
                    queue.offer(node1.right);
                    queue.offer(node2.right);
                }
            }
        }

        return head1;
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
        Node head1 = new Node(1);
        head1.left = new Node(3);
        head1.right = new Node(2);
        head1.left.left = new Node(5);

        Node head2 = new Node(2);
        head2.left = new Node(1);
        head2.right = new Node(3);
        head2.left.right = new Node(4);
        head2.right.right = new Node(7);

        printTree(head1);
        printTree(head2);

        Node head = mergeTrees1(head1, head2);
        printTree(head);

    }
}
