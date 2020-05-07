package NowCoder.advanced_class.exercise;

import java.util.LinkedList;
import java.util.Queue;

/*
    判断搜索二叉树和完全二叉树：
        BST:Morris中序遍历依次升序
        CBT:按层遍历,依次填满
            1) 有右无左,false
            2) 不违反1)的情况下,当前节点左右孩子不全时,左右孩子下的所有节点必须全为叶节点,不能含有子树
 */
public class code27_IsBSTAndCBT {
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

        Node cur = head;
        Node mostRight = null;
        Node pre = null;
        boolean res = true;

        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                } else {
                    mostRight.right = null;

                    if (pre != null && pre.value > cur.value) {
                        res = false;
                        break;
                    }
                    pre = cur;

                    cur = cur.right;
                }
            } else {
                if (pre != null && pre.value > cur.value) {
                    res = false;
                    break;
                }
                pre = cur;

                cur = cur.right;
            }
        }

        return res;
    }

    public static boolean isCBT(Node head) {
        if (head == null) {
            return true;
        }

        Queue<Node> queue = new LinkedList<>();
        boolean res = true;
        boolean leaf = false;
        queue.offer(head);

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            Node left = cur.left;
            Node right = cur.right;

            if ((leaf && (left != null || right != null))
                    ||
                    (left == null && right != null)) {
                res = false;
                break;
            }

            if (left != null){
                queue.offer(left);
            }

            if (right != null){
                queue.offer(right);
            }

            if (left == null || right == null){
                leaf = true;
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
        System.out.println(isCBT(head));
    }
}
