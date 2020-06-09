package NowCoder.advanced_class.exercise.exercise01;

import java.util.HashMap;
import java.util.Stack;

public class code10 {
    public static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node getMaxTree(int[] arr){
        if (arr == null || arr.length < 1){
            return null;
        }

        Stack<Node> stack = new Stack<>();
        HashMap<Node, Node> lBigMap = new HashMap<>();
        HashMap<Node, Node> rBigMap = new HashMap<>();

        Node[] nodes = new Node[arr.length];
        for (int i = 0; i < arr.length; i++) {
            nodes[i] = new Node(arr[i]);
        }

        for (int i = 0; i < nodes.length; i++) {
            Node cur = nodes[i];
            while (!stack.isEmpty() && cur.value > stack.peek().value){
                Node popNode = stack.pop();
                rBigMap.put(popNode, cur);
                if (stack.isEmpty()){
                    lBigMap.put(popNode, null);
                }else {
                    lBigMap.put(popNode, stack.peek());
                }
            }

            stack.push(cur);
        }

        while (!stack.isEmpty()){
            Node popNode = stack.pop();
            rBigMap.put(popNode, null);
            if (stack.isEmpty()){
                lBigMap.put(popNode, null);
            }else {
                lBigMap.put(popNode, stack.peek());
            }
        }

        Node head = null;
        for (int i = 0; i < nodes.length; i++) {
            Node cur = nodes[i];
            Node left = lBigMap.get(cur);
            Node right = rBigMap.get(cur);

            if (left == null && right == null){
                head = cur;
            }else if (left == null){
                if (right.left == null){
                    right.left = cur;
                }else {
                    right.right = cur;
                }
            }else if (right == null){
                if (left.left == null){
                    left.left = cur;
                }else {
                    left.right = cur;
                }
            }else {
                Node parent = left.value < right.value ? left : right;
                if (parent.left == null){
                    parent.left = cur;
                }else {
                    parent.right = cur;
                }
            }
        }

        return head;
    }

    public static void printPreOrder(Node head) {
        if (head == null) {
            return;
        }
        System.out.print(head.value + " ");
        printPreOrder(head.left);
        printPreOrder(head.right);
    }

    public static void printInOrder(Node head) {
        if (head == null) {
            return;
        }
        printPreOrder(head.left);
        System.out.print(head.value + " ");
        printPreOrder(head.right);
    }

    public static void main(String[] args) {
        int[] arr = {3, 4, 5, 1, 2};
        Node head = getMaxTree(arr);
        printPreOrder(head); // 5 4 3 2 1
        System.out.println();
        printInOrder(head); // 4 3 5 2 1
        System.out.println();
//        getMaxTree02(arr);  // 5 4 3 2 1
    }
}
