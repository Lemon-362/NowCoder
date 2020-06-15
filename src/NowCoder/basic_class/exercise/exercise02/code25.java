package NowCoder.basic_class.exercise.exercise02;

import NowCoder.basic_class.exercise.code25_PreInPosBinaryTree;

public class code25 {
    public static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static void morrisPre(Node head){
        if (head == null){
            return;
        }

        Node cur = head;
        Node mostRight = null;

        while (cur != null){
            mostRight = cur.left;
            if (mostRight != null){
                while (mostRight.right != null && mostRight.right != cur){
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null){
                    mostRight.right = cur;

                    System.out.print(cur.value + " ");

                    cur = cur.left;
                }else {
                    mostRight.right = null;

                    cur = cur.right;
                }
            }else {
                System.out.print(cur.value + " ");

                cur = cur.right;
            }
        }

        System.out.println();
    }

    public static void morrisIn(Node head){
        if (head == null){
            return;
        }

        Node cur = head;
        Node mostRight = null;

        while (cur != null){
            mostRight = cur.left;
            if (mostRight != null){
                while (mostRight.right != null && mostRight.right != cur){
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null){
                    mostRight.right = cur;

                    cur = cur.left;
                }else {
                    mostRight.right = null;

                    System.out.print(cur.value + " ");

                    cur = cur.right;
                }
            }else {
                System.out.print(cur.value + " ");

                cur = cur.right;
            }
        }

        System.out.println();
    }

    public static void morrisPos(Node head) {
        if (head == null) {
            return;
        }

        Node cur = head;
        Node mostRight = null;

        while (cur != null){
            mostRight = cur.left;
            if (mostRight != null){
                while (mostRight.right != null && mostRight.right != cur){
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null){
                    mostRight.right = cur;

                    cur = cur.left;
                }else {
                    mostRight.right = null;

                    printEdge(cur.left);

                    cur = cur.right;
                }
            }else {


                cur = cur.right;
            }
        }

        printEdge(head);

        System.out.println();
    }

    public static void printEdge(Node node){
        if (node == null){
            return;
        }

        Node head = reverse(node);
        Node cur = head;

        while (cur != null){
            System.out.print(cur.value + " ");

            cur = cur.right;
        }

        reverse(head);
    }

    public static Node reverse(Node head){
        Node cur = head;
        Node pre = null;
        Node next = null;

        while (cur != null){
            next = cur.right;

            cur.right = pre;

            pre = cur;
            cur = next;
        }

        return pre;
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

        morrisPre(head); // 5 3 2 1 4 8 7 6 10 9 11
        morrisIn(head); // 1 2 3 4 5 6 7 8 9 10 11
        morrisPos(head); // 1 2 4 3 6 7 9 11 10 8 5
    }
}
