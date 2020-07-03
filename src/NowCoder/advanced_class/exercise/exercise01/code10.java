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

    public static Node getMaxTree(int[] arr) {
        if (arr == null || arr.length < 1) {
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
            while (!stack.isEmpty() && nodes[i].value >= stack.peek().value){
                Node popNode = stack.pop();
                rBigMap.put(popNode, nodes[i]);
                if (stack.isEmpty()){
                    lBigMap.put(popNode, null);
                }else {
                    lBigMap.put(popNode, stack.peek());
                }
            }

            stack.push(nodes[i]);
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

    // for test -- print tree
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
        int[] arr = {3, 4, 5, 1, 2};
        Node head = getMaxTree(arr);
        printPreOrder(head); // 5 4 3 2 1
        System.out.println();
        printInOrder(head); // 4 3 5 2 1
        System.out.println();
        printTree(head);
//        getMaxTree02(arr);  // 5 4 3 2 1
    }
}
