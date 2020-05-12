package NowCoder.Nowcoder.exercise;

import NowCoder.Nowcoder.code26_BSTConvertDoubleLinkedList;

import java.util.ArrayList;

public class code26 {
    public static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static ArrayList<Node> list = new ArrayList<>();

    public static Node convertBSTToDoubleLinkedList(Node head){
        if (head == null){
            return null;
        }

        inOrder(head);

        return Connection(list);
    }

    public static void inOrder(Node head){
        if (head == null){
            return;
        }
        inOrder(head.left);
        list.add(head);
        inOrder(head.right);
    }

    public static Node Connection(ArrayList<Node> list){
        for (int i = 0; i < list.size() - 1; i++) {
            list.get(i).right = list.get(i + 1);
            list.get(i + 1).left = list.get(i);
        }

        list.get(0).left = null;
        list.get(list.size() - 1).right = null;

        return list.get(0);
    }

    public static void print(Node head){
        if (head == null){
            return;
        }
        Node cur = null;
        while (head != null){
            System.out.print(head.value + " ");
            cur = head;
            head = head.right;
        }
        System.out.println();
        while (cur != null){
            System.out.print(cur.value + " ");
            cur = cur.left;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head = new Node(4);
        head.left = new Node(2);
        head.right = new Node(5);
        head.left.left = new Node(1);
        head.left.right = new Node(3);

        Node newHead = convertBSTToDoubleLinkedList(head);
        print(newHead);
//      1 2 3 4 5
//      5 4 3 2 1
    }
}
