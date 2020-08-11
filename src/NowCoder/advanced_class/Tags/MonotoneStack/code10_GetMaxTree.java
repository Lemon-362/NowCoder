package NowCoder.advanced_class.Tags.MonotoneStack;

import java.util.*;

/*
    单调栈的应用：构造数组的MaxTree
        数组建树，使得数的每个子树最大值都是头部，且要求时间空间复杂度为O(N)
        定义二叉树节点，一个数组的MaxTree定义如下：
        1. 数组没有重复元素
        2. MaxTree是一颗二叉树，数组的每一个值对应一个节点
        3. 包括MaxTree树在内的任一个子树，值最大的节点都是头部

    解法：
        遍历节点时记录每个节点左右离他最近且比他大的节点 TODO 小找大，从大到小
        根据左右记录构建题目要求的二叉树
 */
public class code10_GetMaxTree {
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
            Node cur = nodes[i];
            while (!stack.isEmpty() && cur.value >= stack.peek().value){
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

    // 构建大根堆
    public static Node getMaxTree2(int[] arr) {
        if (arr == null || arr.length < 1) {
            return null;
        }

        Node[] nodes = new Node[arr.length];
        for (int i = 0; i < arr.length; i++) {
            nodes[i] = new Node(arr[i]);
        }

        PriorityQueue<Node> pQ = new PriorityQueue<>(new myComparator());
        for (int i = 0; i < nodes.length; i++) {
            pQ.add(nodes[i]);
        }

        Node head = pQ.poll();

        Queue<Node> queue = new LinkedList<>();
        queue.offer(head);

        while (!queue.isEmpty()){
            Node cur = queue.poll();
            cur.left = pQ.poll();
            cur.right = pQ.poll();

            if (cur.left != null){
                queue.offer(cur.left);
            }
            if (cur.right != null){
                queue.offer(cur.right);
            }
        }

        return head;
    }

    public static class myComparator implements Comparator<Node> {
        public int compare(Node o1, Node o2) {
            return o2.value - o1.value;
        }
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
        Node head = getMaxTree2(arr);
        printTree(head);

        printPreOrder(head); // 5 4 3 2 1
        System.out.println();
        printInOrder(head); // 4 3 5 2 1
        System.out.println();
//        getMaxTree2(arr);  // 5 4 3 2 1
    }
}
