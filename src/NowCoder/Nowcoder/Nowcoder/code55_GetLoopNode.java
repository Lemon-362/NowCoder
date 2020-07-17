package NowCoder.Nowcoder.Nowcoder;

/*
    给一个链表，若其中包含环，请找出该链表的环的入口结点，否则，输出null。
 */
public class code55_GetLoopNode {
    public static class Node {
        int val;
        Node next = null;

        Node(int val) {
            this.val = val;
        }
    }

    public static Node getLoopNode(Node pHead) {
        if (pHead == null || pHead.next == null) {
            return null;
        }

        Node n1 = pHead.next;
        Node n2 = pHead.next.next;

        while (n1 != n2){
            if (n2.next == null || n2.next.next == null){
                return null;
            }
            n1 = n1.next;
            n2 = n2.next.next;
        }

        n2 = pHead;

        while (n1 != n2){
            n1 = n1.next;
            n2 = n2.next;
        }

        return n1;
    }

    public static void main(String[] args) {
        // 1->2->3->4->5->6->7->4...
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);
        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

        System.out.println(getLoopNode(head1).val); // 4
    }
}
