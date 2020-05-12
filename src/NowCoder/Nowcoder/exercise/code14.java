package NowCoder.Nowcoder.exercise;

public class code14 {
    public static class Node {
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node findKthToTail(Node head, int k){
        if (head == null){
            return null;
        }

        Node cur = head;
        int len = 0;
        while (cur != null){
            len++;
            cur = cur.next;
        }

        cur = head;
        for (int i = 0; i < len - k; i++) {
            cur = cur.next;
        }

        return cur;
    }

    public static void main(String[] args) {
        Node head = new Node(3);
        head.next = new Node(5);
        head.next.next = new Node(1);
        head.next.next.next = new Node(7);
        head.next.next.next.next = new Node(8);

        System.out.println(findKthToTail(head, 3).value); // 1
    }
}
