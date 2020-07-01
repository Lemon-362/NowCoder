package NowCoder.Nowcoder.exercise;

public class code56 {
    public static class Node {
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node deleteDuplication(Node head) {
        if (head == null) {
            return null;
        }

        Node newHead = new Node(Integer.MIN_VALUE);
        newHead.next = head;
        Node cur = newHead;
        Node pre = newHead;

        while (cur != null){
            if (cur.next != null && cur.value == cur.next.value){
                while (cur.next != null && cur.value == cur.next.value){
                    cur = cur.next;
                }
                cur = cur.next;
                pre.next = cur;
            }else {
                pre = cur;
                cur = cur.next;
            }
        }

        Node res = newHead.next;
        newHead.next = null;

        return res;
    }

    public static void print(Node head) {
        if (head == null) {
            return;
        }
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(3);
        head.next.next.next.next = new Node(4);
        head.next.next.next.next.next = new Node(4);
        head.next.next.next.next.next.next = new Node(5);

        print(head); // 1 2 3 3 4 4 5

        Node newHead = deleteDuplication(head);
        print(newHead); // 1 2 5
    }
}
