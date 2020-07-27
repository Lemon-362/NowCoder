package NowCoder.LeetCode.Tags.Hot100.StackQueueList;

/*
142. 环形链表的第一个入环节点
    给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
    为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
    如果 pos 是 -1，则在该链表中没有环。

 */
public class code06_FindFirstCycleNode {
    public static class Node {
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node findNode(Node head) {
        if (head == null || head.next == null) {
            return null;
        }

        Node p1 = head.next;
        Node p2 = head.next.next;

        while (p1 != p2) {
            // TODO 这里不能判断p2的next和next.next, 因为可能p2已经走到头了
            if (p2 == null || p2.next == null) {
                return null;
            }
            p1 = p1.next;
            p2 = p2.next.next;
        }

        p2 = head;
        while (p1 != p2) {
            p1 = p1.next;
            p2 = p2.next;
        }

        return p1;
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

        System.out.println(findNode(head1).value);  // 4
    }
}
