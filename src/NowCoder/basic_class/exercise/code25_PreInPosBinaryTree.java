package NowCoder.basic_class.exercise;

import NowCoder.basic_class.PrintBinaryTree;
import NowCoder.basic_class.basic_class.class_04.Code_01_PreInPosTraversal;

import java.util.Stack;

/*
    二叉树的先序、中序、后序遍历（递归和非递归方式）
    	先序：该节点、左子树、右子树（中左右）
		中序：左子树、该节点、右子树（左中右）
		后序：左子树、右子树、该节点（左右中）
	递归：首先访问到该节点，然后向左递归，最后向右递归：一共会访问该节点三次
		  所以根据 <打印> 这句话所处的位置（第一、二、三次到达该节点的时刻）
	非递归：用Stack栈
		1. 先序：先压入树顶，然后直接弹出打印
				 若有右孩子，则先压右，再压左，一直重复直到Stack为空
		2. 中序：把head的左子树都压入栈，当head到底（null）时，从栈中弹出打印，head向右移动
				 当前节点head：不为空，则压入head，head向左移
				 			   为空，则从栈中弹出打印，head向右移
		3. 后序：先序的中左右-->中右左（打印这句话不需要），然后存到栈中，（将打印语句替换为压栈），
		         再逐一弹出打印（逆序）
 */
public class code25_PreInPosBinaryTree {
    public static class Node {
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
        if (head != null){
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
        if (head != null){
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
        if (head != null){
            Stack<Node> stack = new Stack<>();
            Stack<Node> help = new Stack<>();
            stack.push(head);
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
    }

    // print
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

//        printTree(head);
        /*
                        5
                   3         8
               2      4    7     10
            1            6     9    11
         */
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
