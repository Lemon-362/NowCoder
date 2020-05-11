package NowCoder.Nowcoder.exercise;

public class code15 {
    public static class Node {
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node reverseLinkedList(Node head){
        if (head == null){
            return null;
        }

        Node cur = head;
        Node pre = null;
        Node next = null;

        while (cur != null){
            next = cur.next;

            cur.next = pre;
            pre = cur;
            cur = next;
        }

        return pre;
    }
}
