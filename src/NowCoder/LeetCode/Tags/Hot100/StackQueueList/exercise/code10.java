package NowCoder.LeetCode.Tags.Hot100.StackQueueList.exercise;

import java.util.Stack;

/*
234. 判断链表是否是回文结构：存在对称轴，两边逆序关系/正反念都一样
    1. 额外空间复杂度：O(n)
    2. 额外空间复杂度：O(1)  快慢指针，反转后半部分，逐一比较，还原链表
	反转链表的时候，要用两个指针
 */
public class code10 {
    public static class Node {
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static boolean isHuiWenLinkedList(Node head) {
        if (head == null || head.next == null) {
            return true;
        }

        Stack<Node> stack = new Stack<>();
        Node cur = head;

        while (cur != null){
            stack.push(cur);
            cur = cur.next;
        }

        cur = head;
        while (!stack.isEmpty()){
            if (stack.pop().value != cur.value){
                return false;
            }

            cur = cur.next;
        }

        return true;
    }

    public static boolean isHuiWenByO1(Node head) {
        if (head == null || head.next == null) {
            return true;
        }

        Node p1 = head;
        Node p2 = head.next;

        while (p2 != null && p2.next != null){
            p1 = p1.next;
            p2 = p2.next.next;
        }

        Node p3 = p1.next;
        p1.next = null;
        while (p3 != null){
            p2 = p3.next;

            p3.next = p1;

            p1 = p3;
            p3 = p2;
        }

        p3 = p1;
        p2 = head;
        boolean res = true;

        while (p1 != null && p2 != null){
            if (p1.value != p2.value){
                res = false;
                break;
            }
            p1 = p1.next;
            p2 = p2.next;
        }

        while (p3 != null){
            p2 = p3.next;

            p3.next = p1;

            p1 = p3;
            p3 = p2;
        }

        return res;
    }

    public static void print(Node headNode) {
        while (headNode != null) {
            System.out.print(headNode.value + " ");
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
