package NowCoder.LeetCode.Tags.Hot100.StackQueueList.exercise;

/*
19. 删除链表的倒数第K个节点
    给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。

 */
public class code02 {
    public static class Node {
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node removeKthNodeFromEnd(Node head, int k){
        if (head == null){
            return null;
        }

        Node newHead = new Node(Integer.MIN_VALUE);
        newHead.next = head;
        Node p1 = newHead;
        Node p2 = newHead;

        while (k + 1 > 0){
            p2 = p2.next;
            k--;
        }

        while (p2 != null){
            p1 = p1.next;
            p2 = p2.next;
        }

        if (p1.next.next != null){
            p1.next = p1.next.next;
        }else {
            p1.next = null;
        }

        Node res = newHead.next;
        newHead.next = null;

        return res;
    }

    public static void print(Node head){
        if (head == null){
            return;
        }

        Node cur = head;
        while (cur != null){
            System.out.print(cur.value + " ");
            cur = cur.next;
        }

        System.out.println();
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);

        print(head);
        Node res = removeKthNodeFromEnd(head, 1); // 1 2 3 4
        print(res);

    }

}
