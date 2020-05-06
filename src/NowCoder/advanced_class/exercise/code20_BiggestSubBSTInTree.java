package NowCoder.advanced_class.exercise;

import NowCoder.advanced_class.class_5.Code_01_BiggestSubBSTInTree;

import javax.swing.*;

/*
	树的递归1：最大搜索二叉子树
		给定一个二叉树的头节点head，返回最大搜索二叉子树的大小
		也就是说这个二叉树本身可能不是搜索二叉树，需要在其中找到最大的搜索二叉树

	// TODO 树的套路：题目要求整棵树的。。。，那么就通过递归求解以每个节点为头的子树的。。。，答案一定在其中
	// TODO 树的递归流程：
	    1. 先以每个节点为头，找题目要求的条件，列出所有可能性
	    2. 根据可能性，列出所有需要的信息
	    3. 将向左向右递归返回的信息都整合成结构相同的信息，封装成一个类（黑盒）
	    4. 改递归：
	        先假设下级能够返回给我信息（直接根据类写出下级返回信息的代码）
	        然后我要根据下级返回的信息和列出的可能性进行加工，使得后续能够返回所有可能性的值
	        最后根据可能性的值，包装信息，把当前节点作为下级，向上返回（解黑盒）
 */
public class code20_BiggestSubBSTInTree {
    public static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    /*
        题目要求整个树中最大的BST --> 求以每个节点为头的子树上的最大BST
        1. 分析可能性:
            1) 可能在左子树上
            2) 可能在右子树上
            3) 左右子树都是BST,和头节点含在一起,是该子树最大的BST
            -->要求  左子树最大BST的头节点是头节点的左孩子,右子树最大BST的头节点是头节点的右孩子
                    且头节点>左子树最大BST的max,头节点<右子树最大BST的min
        TODO 需要比较每个可能性求出的最大BST的size,取max
        2. 根据可能性,列出所需信息:
            1) 左子树的size
            2) 右子树的size
            3) 左子树的max
            4) 右子树的min
        3. 整合成结构相同的信息,当作黑盒,用于向上返回信息:
            1) 子树最大BST的size
            2) 子树最大BST的头节点
            3) 子树的max
            4) 子树的min
        4. 改递归
     */

    public static class ReturnData {
        private int size;
        private Node head;
        private int max;
        private int min;

        public ReturnData(int size, Node head, int max, int min) {
            this.size = size;
            this.head = head;
            this.max = max;
            this.min = min;
        }
    }

    public static ReturnData process(Node head){
        if (head == null){
            return new ReturnData(0, null, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }

        ReturnData left = process(head.left);
        ReturnData right = process(head.right);

        int leftSize = left.size;
        int rightSize = right.size;
        int curSize = 0;
        if (left.head == head.left && right.head == head.right
        && head.value > left.max && head.value < right.min){
            curSize = leftSize + rightSize + 1;
        }

        int maxSize = Math.max(Math.max(leftSize, rightSize), curSize);
        Node maxHead = leftSize > rightSize ? left.head : right.head;
        if (maxSize == curSize){
            maxHead = head;
        }
        int maxMax = Math.max(Math.max(left.max, right.max), head.value);
        int minMin = Math.min(Math.min(left.min, right.min), head.value);

        return new ReturnData(maxSize, maxHead, maxMax, minMin);
    }

    public static Node getMaxBST(Node head){
        return process(head).head;
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
        Node head = new Node(6);
        head.left = new Node(1);
        head.left.left = new Node(0);
        head.left.right = new Node(3);
        head.right = new Node(12);
        head.right.left = new Node(10);
        head.right.left.left = new Node(4);
        head.right.left.left.left = new Node(2);
        head.right.left.left.right = new Node(5);
        head.right.left.right = new Node(14);
        head.right.left.right.left = new Node(11);
        head.right.left.right.right = new Node(15);
        head.right.right = new Node(13);
        head.right.right.left = new Node(20);
        head.right.right.right = new Node(16);

        Node bst = getMaxBST(head);
        printTree(bst);
        System.out.println(process(head).size); // 7
    }
}
