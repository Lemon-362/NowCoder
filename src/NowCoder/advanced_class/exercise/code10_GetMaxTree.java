package NowCoder.advanced_class.exercise;

import NowCoder.advanced_class.class_3.Code_02_GetMaxTree;

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

        Node[] nodes = new Node[arr.length];
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = new Node(arr[i]);
        }

        Stack<Node> stack = new Stack<>();
        HashMap<Node, Node> lBigMap = new HashMap<>();
        HashMap<Node, Node> rBigMap = new HashMap<>();

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

    // 构建大根堆
    public static void getMaxTree02(int[] arr) {
        if (arr == null || arr.length < 1) {
            return;
        }

        // 根据值创建节点数组
        Node[] nodes = new Node[arr.length];
        for (int i = 0; i < arr.length; i++) {
            nodes[i] = new Node(arr[i]);
        }

        Queue<Node> queue = new PriorityQueue<>(new myComparator());
        for (int i = 0; i < nodes.length; i++) {
            queue.add(nodes[i]);
        }

        while (!queue.isEmpty()) {
            System.out.print(queue.poll().value + " ");
        }

        // TODO 根据数组建立二叉树

//        Node head = queue.poll();
//        while (!queue.isEmpty()){
//            Node cur = queue.poll();
//
//        }
//
//        return head;
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

    public static void main(String[] args) {
        int[] arr = {3, 4, 5, 1, 2};
        Node head = getMaxTree(arr);
        printPreOrder(head); // 5 4 3 2 1
        System.out.println();
        printInOrder(head); // 4 3 5 2 1
        System.out.println();
        getMaxTree02(arr);  // 5 4 3 2 1
    }
}
