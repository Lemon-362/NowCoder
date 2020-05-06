package NowCoder.basic_class.exercise01.Tree;

import java.util.Stack;

public class IsBST_IsCBT {
    public static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static boolean isBST(Node head){
        if (head == null){
            return true;
        }
        Stack<Node> stack = new Stack<>();
        Node[] nodes = new Node[100000];
        int index = 0;
        while (!stack.isEmpty() || head != null){
            if (head != null){
                stack.push(head);
                head = head.left;
            }else {
                head = stack.pop();
                nodes[index++] = head;
                head = head.right;
            }
        }
        boolean res = true;
        for (int i = 0; i < index - 2; i++) {
            if (nodes[i].value > nodes[i+1].value){
                res = false;
                break;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Node head = new Node(4);
        head.left = new Node(2);
        head.right = new Node(6);
        head.left.left = new Node(1);
        head.left.right = new Node(3);
        head.right.left = new Node(5);

        System.out.println(isBST(head));
    }
}
