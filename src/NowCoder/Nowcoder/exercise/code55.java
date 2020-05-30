package NowCoder.Nowcoder.exercise;

public class code55 {
    public static class Node {
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node getLoopNode(Node node){
        if (node == null){
            return null;
        }

        Node n1 = node.next;
        Node n2 = node.next.next;

        while (n1 != n2){
            if (n2.next == null || n2.next.next == null){
                return null;
            }
            n1 = n1.next;
            n2 = n2.next.next;
        }

        n2 = node;
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

        System.out.println(getLoopNode(head1).value); // 4
    }

}
