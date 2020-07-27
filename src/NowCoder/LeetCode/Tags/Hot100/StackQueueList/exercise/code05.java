package NowCoder.LeetCode.Tags.Hot100.StackQueueList.exercise;

import java.util.HashSet;

/*
141. 判断链表是否有环
    给定一个链表，判断链表中是否有环。
    为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
    如果 pos 是 -1，则在该链表中没有环

 */
public class code05 {
    public static class Node {
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static boolean hasCycle1(Node head) {
        if (head == null || head.next == null) {
            return false;
        }

        HashSet<Node> set = new HashSet<>();

        Node cur = head;
        while (cur != null){
            if (set.contains(cur)){
                return true;
            }else {
                set.add(cur);
            }

            cur = cur.next;
        }

        return false;
    }

    public static boolean hasCycle2(Node head) {
        if (head == null || head.next == null) {
            return false;
        }

        Node p1 = head;
        Node p2 = head.next;

        while (p1 != p2){
            if (p2 == null || p2.next == null){
                return false;
            }
            p1 = p1.next;
            p2 = p2.next.next;
        }

        return true;
    }

    public static void main(String[] args) {
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);
        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

        System.out.println(hasCycle2(head1));
    }
}
