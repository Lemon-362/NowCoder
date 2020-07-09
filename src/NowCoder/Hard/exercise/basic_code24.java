package NowCoder.Hard.exercise;

public class basic_code24 {
    public static class Node {
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node findFirstIntersectNode(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }

        Node loop1 = getLoopNode(head1);
        Node loop2 = getLoopNode(head2);

        if (loop1 == null && loop2 == null){
            return bothNoLoop(head1, head2);
        }

        if (loop1 != null && loop2 != null){
            return bothLoop(head1, head2, loop1, loop2);
        }

        return null;
    }

    public static Node bothLoop(Node head1, Node head2, Node loop1, Node loop2){
        if (loop1 == loop2){
            Node p1 = head1;
            Node p2 = head2;
            int n = 0;

            while (p1 != loop1){
                n++;
                p1 = p1.next;
            }
            while (p2 != loop1){
                n--;
                p2 = p2.next;
            }

            p1 = n > 0 ? head1 : head2;
            p2 = p1 == head1 ? head2 : head1;
            n = Math.abs(n);

            while (n > 0){
                p1 = p1.next;
                n--;
            }

            while (p1 != p2){
                p1 = p1.next;
                p2 = p2.next;
            }

            return p1;
        }else {
            Node cur = loop1.next;
            while (cur != loop1){
                if (cur == loop2){
                    return loop1;
                }
                cur = cur.next;
            }
            return null;
        }
    }

    public static Node bothNoLoop(Node head1, Node head2){
        if (head1 == null || head2 == null){
            return null;
        }

        Node p1 = head1;
        Node p2 = head2;
        int n = 0;

        while (p1 != null){
            n++;
            p1 = p1.next;
        }
        while (p2 != null){
            n--;
            p2 = p2.next;
        }

        p1 = n > 0 ? head1 : head2;
        p2 = p1 == head1 ? head2 : head1;
        n = Math.abs(n);

        while (n > 0){
            p1 = p1.next;
            n--;
        }

        while (p1 != p2){
            p1 = p1.next;
            p2 = p2.next;
        }

        return p1;
    }

    public static Node getLoopNode(Node head){
        if (head == null){
            return null;
        }

        Node p1 = head.next;
        Node p2 = head.next.next;

        while (p1 != p2){
            if (p2.next == null || p2.next.next == null){
                return null;
            }
            p1 = p1.next;
            p2 = p2.next.next;
        }

        p2 = head;
        while (p1 != p2){
            p1 = p1.next;
            p2 = p2.next;
        }

        return p1;
    }

    public static void main(String[] args) {
        // 1->2->3->4->5->6->7->null
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);

        // 0->9->8->6->7->null
        Node head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(findFirstIntersectNode(head1, head2).value);

        // 1->2->3->4->5->6->7->4...
        head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);
        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

        // 0->9->8->2...
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next; // 8->2
        System.out.println(findFirstIntersectNode(head1, head2).value);

        // 0->9->8->6->4->5->6..
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(findFirstIntersectNode(head1, head2).value);

        // 6 2 4
    }
}
