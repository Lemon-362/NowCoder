package NowCoder.LeetCode.Tags.Hot100.StackQueueList;

import java.util.HashSet;

/*
141. 判断链表是否有环
    给定一个链表，判断链表中是否有环。
    为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
    如果 pos 是 -1，则在该链表中没有环

 */
public class code05_HasCycleInList {
    public static class Node {
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    /*
    环形链表:
        找入环节点 ==> HashMap / 快慢双指针

        (1) HashMap:
            直接将每个节点放入map中, 如果已存在, 则有环

        (2) 快慢双指针:
            快指针一次走两步, 慢指针一次走一步
            如果链表无环, 快指针会先走到头, 直接返回false
            如果链表有环, 快慢指针一定会相遇

        TODO 在使用快慢双指针时, 快慢双指针的初始化一定要设置为head.next和head.next.next
            如果设置为head和head.next则会死循环

     */
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

        Node p1 = head.next;
        Node p2 = head.next.next;

        while (p1 != p2) {
            // TODO 这里不能判断p2的next和next.next, 因为可能p2已经走到头了
            if (p2 == null || p2.next == null) {
                return false;
            }
            p1 = p1.next;
            p2 = p2.next.next;
        }

        // 这里可以剩去, 没有让求相交位置
        p2 = head;
        while (p1 != p2) {
            p1 = p1.next;
            p2 = p2.next;
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
