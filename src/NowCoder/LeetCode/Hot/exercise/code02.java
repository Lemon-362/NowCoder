package NowCoder.LeetCode.Hot.exercise;

public class code02 {
    public static class Node {
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node addTwoNumbers(Node head1, Node head2) {
        if (head1 == null) {
            return head2;
        } else if (head2 == null) {
            return head1;
        }

        Node newHead = new Node(Integer.MIN_VALUE);
        Node cur = newHead;

        int carry = 0;
        Node p1 = head1;
        Node p2 = head2;

        while (p1 != null || p2 != null){
            int sum = 0;
            if (p1 != null){
                sum += p1.value;
                p1 = p1.next;
            }
            if (p2 != null){
                sum += p2.value;
                p2 = p2.next;
            }

            sum += carry;

            carry = sum / 10;
            sum = sum % 10;

            cur.next = new Node(sum);
            cur = cur.next;
        }

        if (carry == 1){
            cur.next = new Node(1);
        }

        Node res = newHead.next;
        newHead.next = null;

        return res;
    }

    public static void main(String[] args) {
        Node head1 = new Node(6);
        head1.next = new Node(3);
//        head1.next.next = new Node(3);

        Node head2 = new Node(5);
        head2.next = new Node(8);

        Node head = addTwoNumbers(head1, head2);
        while (head != null) {
            System.out.print(head.value + " "); // 1 2 1
            head = head.next;
        }
    }
}
