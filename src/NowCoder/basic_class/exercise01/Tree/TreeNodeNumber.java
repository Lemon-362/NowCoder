package NowCoder.basic_class.exercise01.Tree;

import NowCoder.basic_class.exercise.code30_TreeNodeNumber;

public class TreeNodeNumber {
    public static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static int method(Node head){
        if (head == null){
            return 0;
        }
        int H = getLeftMost(head, 1);
        return process(head, 1, H);
    }

    public static int process(Node node, int level, int h){
        if (node == null){
            return 0;
        }
        if (level == h){
            return 1;
        }
        if (getLeftMost(node.right, level + 1) == h){
            return (1 << (h - level)) + process(node.right, level + 1, h);
        }else {
            return (1 << (h - level - 1)) + process(node.left, level + 1, h);
        }
    }

    public static int getLeftMost(Node node, int level){
        if (node == null){
            return 0;
        }
        while (node != null){
            level++;
            node = node.left;
        }
        return level - 1;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
//        head.right.right = new Node(7);
        System.out.println(method(head));
    }
}
