package NowCoder.basic_class.exercise;

import java.util.Stack;

/*
	判断链表是否是回文结构：存在对称轴，两边逆序关系/正反念都一样
		1. 额外空间复杂度：O(n)
		2. 额外空间复杂度：O(1)  快慢指针，反转后半部分，逐一比较，还原链表
	反转链表的时候，要用两个指针
 */
public class code21_IsHuiWenLinkedList {
    public static class Node {
        private int data;
        private Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    // O(n)
    public static boolean isHuiWenLinkedList(Node headNode) {
        if (headNode == null || headNode.next == null) {
            return true;
        }
        Stack<Node> stack = new Stack<>();
        Node cur = headNode;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        cur = headNode;
        boolean res = true;
        while (cur != null) {
            if (cur.data != stack.pop().data) {
                res = false;
                break;
            }
            cur = cur.next;
        }
        return res;
    }

    public static boolean isHuiWenByO1(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        Node n1 = head;
        Node n2 = head;
        while (n2.next != null && n2.next.next != null){
            n1 = n1.next;
            n2 = n2.next.next;
        }
        n2 = n1.next;
        n1.next = null;
        Node n3 = null;
        while (n2 != null){
            n3 = n2.next;

            n2.next = n1;

            n1 = n2;
            n2 = n3;
        }
        n3 = n1;
        n2 = head;
        boolean res = true;
        while (n1 != null && n2 != null){
            if (n1.data != n2.data){
                res = false;
                break;
            }
            n1 = n1.next;
            n2 = n2.next;
        }
        while (n3 != null){
            n1 = n3.next;

            n3.next = n2;

            n2 = n3;
            n3 = n1;
        }
        return res;
    }

    public static void print(Node headNode) {
        while (headNode != null) {
            System.out.print(headNode.data + " ");
            headNode = headNode.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(3);
        head.next.next = new Node(5);
        head.next.next.next = new Node(3);
        head.next.next.next.next = new Node(1);

        print(head);
//        System.out.println(isHuiWenLinkedList(head));
        System.out.println(isHuiWenByO1(head));
        print(head);

    }
}
