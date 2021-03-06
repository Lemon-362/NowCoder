package NowCoder.basic_class.exercise.exercise02;

import java.util.ArrayList;

public class code26 {
    public static class Node {
        private int value;
        private Node left;
        private Node right;
        private Node parent;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node getSuccessorNode(Node node) {
        if (node == null) {
            return null;
        }

        if (node.right != null){
            return getLeftMost(node.right);
        }else {
            Node parent = node.parent;
            while (parent != null && parent.left != node){
                node = parent;
                parent = node.parent;
            }
            return parent;
        }
    }

    public static Node getLeftMost(Node head){
        if (head == null) {
            return null;
        }

        Node cur = head;

        while (cur.left != null){
            cur = cur.left;
        }

        return cur;
    }

    // TODO 前驱节点：左子树上最右的节点，如果没有则找父节点的右孩子是当前节点
    public static Node getSuccessPreNode(Node node){
        if (node == null){
            return null;
        }

        if (node.left != null){
            return getRightMost(node.left);
        }else {
            Node parent = node.parent;
            while (parent != null && parent.right != node){
                node = parent;
                parent = node.parent;
            }
            return parent;
        }
    }

    public static Node getRightMost(Node head){
        if (head == null){
            return null;
        }

        while (head.right != null){
            head = head.right;
        }

        return head;
    }

    public static void main(String[] args) {
        Node head = new Node(6);
        head.parent = null;
        head.left = new Node(3);
        head.left.parent = head;
        head.left.left = new Node(1);
        head.left.left.parent = head.left;
        head.left.left.right = new Node(2);
        head.left.left.right.parent = head.left.left;
        head.left.right = new Node(4);
        head.left.right.parent = head.left;
        head.left.right.right = new Node(5);
        head.left.right.right.parent = head.left.right;
        head.right = new Node(9);
        head.right.parent = head;
        head.right.left = new Node(8);
        head.right.left.parent = head.right;
        head.right.left.left = new Node(7);
        head.right.left.left.parent = head.right.left;
        head.right.right = new Node(10);
        head.right.right.parent = head.right;

        Node test = head.left.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left.left.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left.right.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right.left.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test));
//        1 next: 2
//        2 next: 3
//        3 next: 4
//        4 next: 5
//        5 next: 6
//        6 next: 7
//        7 next: 8
//        8 next: 9
//        9 next: 10
//        10 next: null
    }
}
