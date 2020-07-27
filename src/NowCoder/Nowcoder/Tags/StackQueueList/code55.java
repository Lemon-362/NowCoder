package NowCoder.Nowcoder.Tags.StackQueueList;

public class code55 {
    public static class Node {
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node getLoopNode(Node head){
        if (head == null || head.next == null){
            return null;
        }

        Node p1 = head.next;
        Node p2 = head.next.next;

        while (p1 != p2){
            if (p2 == null || p2.next == null){
                return null;
            }
            p1 = p1.next;
            p2 = p2.next.next;
        }

        return p1;
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

        System.out.println(getLoopNode(head1).value); // 4
    }

}
