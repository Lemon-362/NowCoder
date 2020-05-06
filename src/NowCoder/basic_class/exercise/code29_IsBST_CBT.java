package NowCoder.basic_class.exercise;

/*
	判断是否是搜索二叉树、完全二叉树：
		搜索二叉树：任意节点为头的子树，左子树均小于该节点，右子树均大于该节点
		完全二叉树：从左往右放满节点

	判断搜索二叉树：中序遍历的节点依次升序则为搜索二叉树
	判断完全二叉树：按层遍历
        对任意节点，有右孩子，没有左孩子，一定不是
                    有左孩子，没有右孩子/左右孩子都没有：后面所有节点都必须时其叶节点
 */

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class code29_IsBST_CBT {
    public static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static boolean isBST(Node head) {
        if (head == null) {
            return true;
        }
        Stack<Node> stack = new Stack<>();
        // 不使用数组存储，用一个变量存储之前的值，依次更新
        // TODO 要用double类型存储
        double min = -Double.MAX_VALUE;
        while (!stack.isEmpty() || head != null) {
            if (head != null) {
                stack.push(head);
                head = head.left;
            } else {
                head = stack.pop();
                if (head.value <= min) {
                    return false;
                }
                min = head.value;
                head = head.right;
            }
        }
        return true;
    }

    public static boolean isCBT(Node head){
        if (head == null){
            return true;
        }
        boolean leaf = false;
        Node l, r;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(head);
        while (!queue.isEmpty()){
            head = queue.poll();
            l = head.left;
            r = head.right;
            if ((leaf && (l != null || r != null)) || (l == null && r != null)){
                return false;
            }
            if (l != null){
                queue.offer(l);
            }
            if (r != null){
                queue.offer(r);
            }else {
                leaf = true;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Node head = new Node(4);
        head.left = new Node(2);
        head.right = new Node(6);
        head.left.left = new Node(1);
        head.left.right = new Node(3);
        head.right.left = new Node(5);

        System.out.println(isBST(head));
        System.out.println(isCBT(head));
    }
}
