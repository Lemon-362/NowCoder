package NowCoder.basic_class.exercise01.Tree;

import java.util.Stack;

public class PreInPosBinaryTree {
    public static class Node{
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static void preOrderRecur(Node head){
        if (head == null){
            return;
        }
        System.out.print(head.value + " ");
        preOrderRecur(head.left);
        preOrderRecur(head.right);
    }

    public static void preOrderUnRecur(Node head){
        if (head == null){
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(head);
        while (!stack.isEmpty()){
            head = stack.pop();
            System.out.print(head.value + " ");
            if (head.right != null){
                stack.push(head.right);
            }
            if (head.left != null){
                stack.push(head.left);
            }
        }
    }

    public static void inOrderRecur(Node head){
        if (head == null){
            return;
        }
        inOrderRecur(head.left);
        System.out.print(head.value + " ");
        inOrderRecur(head.right);
    }

    public static void inOrderUnRecur(Node head){
        if (head == null){
            return;
        }
        Stack<Node> stack = new Stack<>();
        while (!stack.isEmpty() || head != null){
            if (head != null){
                stack.push(head);
                head = head.left;
            }else {
                head = stack.pop();
                System.out.print(head.value + " ");
                head = head.right;
            }
        }
    }

    public static void posOrderRecur(Node head){
        if (head == null){
            return;
        }
        posOrderRecur(head.left);
        posOrderRecur(head.right);
        System.out.print(head.value + " ");
    }

    public static void posOrderUnRecur(Node head){
        if (head == null){
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(head);
        Stack<Node> help = new Stack<>();
        while (!stack.isEmpty()){
            head = stack.pop();
            help.push(head);
            if (head.left != null){
                stack.push(head.left);
            }
            if (head.right != null){
                stack.push(head.right);
            }
        }
        while (!help.isEmpty()){
            System.out.print(help.pop().value + " ");
        }
    }

    public static void main(String[] args) {
        Node head = new Node(5);
        head.left = new Node(3);
        head.right = new Node(8);
        head.left.left = new Node(2);
        head.left.right = new Node(4);
        head.left.left.left = new Node(1);
        head.right.left = new Node(7);
        head.right.left.left = new Node(6);
        head.right.right = new Node(10);
        head.right.right.left = new Node(9);
        head.right.right.right = new Node(11);

        System.out.println("==============recursive==============");
        System.out.print("pre-order: ");
        preOrderRecur(head); // 5 3 2 1 4 8 7 6 10 9 11
        System.out.println();
        System.out.print("in-order: ");
        inOrderRecur(head); // 1 2 3 4 5 6 7 8 9 10 11
        System.out.println();
        System.out.print("pos-order: ");
        posOrderRecur(head); // 1 2 4 3 6 7 9 11 10 8 5
        System.out.println();

        // unrecursive
        System.out.println("============unrecursive=============");
        System.out.print("pre-order: ");
        preOrderUnRecur(head);
        System.out.println();
        System.out.print("in-order: ");
        inOrderUnRecur(head);
        System.out.println();
        System.out.print("pos-order: ");
        posOrderUnRecur(head);
        System.out.println();
    }
}