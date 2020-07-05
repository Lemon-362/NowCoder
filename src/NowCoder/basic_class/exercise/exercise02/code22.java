package NowCoder.basic_class.exercise.exercise02;

public class code22 {
    public static class Node {
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node SmallEqualBig01(Node head, int num) {
        if (head == null) {
            return null;
        }

        int len = 0;
        Node cur = head;
        while (cur != null){
            len++;
            cur = cur.next;
        }

        cur = head;
        Node[] nodes = new Node[len];
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = new Node(cur.value);
            cur = cur.next;
        }

        partition(nodes, num);

        for (int i = 0; i < nodes.length - 1; i++) {
            nodes[i].next = nodes[i + 1];
        }

        nodes[len - 1].next = null;

        return nodes[0];
    }

    public static void partition(Node[] nodes, int num){
        int less = -1;
        int more = nodes.length;
        int cur = 0;

        while (cur < more){
            if (nodes[cur].value < num){
                swap(nodes, ++less, cur++);
            }else if (nodes[cur].value > num){
                swap(nodes, --more, cur);
            }else {
                cur++;
            }
        }
    }

    public static void swap(Node[] nodes, int i, int j) {
        Node node = nodes[i];
        nodes[i] = nodes[j];
        nodes[j] = node;
    }

    public static Node SmallEqualBig02(Node head, int num) {
        if (head == null) {
            return null;
        }

        Node sh = null;
        Node st = null;
        Node eh = null;
        Node et = null;
        Node bh = null;
        Node bt = null;
        Node cur = head;
        Node next = null;

        while (cur != null){
            next = cur.next;
            cur.next = null;

            if (cur.value < num){
                if (sh == null){
                    sh = cur;
                    st = cur;
                }else {
                    st.next = cur;
                    st = st.next;
                }
            }else if (cur.value > num){
                if (bh == null){
                    bh = cur;
                    bt = cur;
                }else {
                    bt.next = cur;
                    bt = bt.next;
                }
            }else {
                if (eh == null){
                    eh = cur;
                    et = cur;
                }else{
                    et.next = cur;
                    et = et.next;
                }
            }

            cur = next;
        }

        if (st != null){
            st.next = eh;
            et = et == null ? eh : et;
        }

        if (et != null){
            et.next = bh;
        }

        if (sh != null){
            return sh;
        }else if (eh != null){
            return eh;
        }else {
            return bh;
        }
    }

    public static void printLinkedList(Node head) {
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head = new Node(7);
        head.next = new Node(9);
        head.next.next = new Node(1);
        head.next.next.next = new Node(8);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(2);
        head.next.next.next.next.next.next = new Node(5);

        printLinkedList(head); // 7 9 1 8 5 2 5

//        Node head1 = SmallEqualBig01(head, 5);
//        printLinkedList(head1); // 2 1 5 5 8 9 7

        Node head2 = SmallEqualBig02(head, 5);
        printLinkedList(head2); // 1 2 5 5 7 9 8
    }
}
