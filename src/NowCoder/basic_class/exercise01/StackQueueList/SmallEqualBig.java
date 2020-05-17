package NowCoder.basic_class.exercise01.StackQueueList;

import NowCoder.basic_class.exercise.code22_SmallEqualBig;

public class SmallEqualBig {
    public static class Node {
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node method01(Node head, int num) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        Node next = null;
        int len = 0;
        while (cur != null) {
            len++;
            cur = cur.next;
        }
        Node[] nodes = new Node[len];
        cur = head;
        for (int i = 0; i < len; i++) {
            nodes[i] = cur;
            cur = cur.next;
        }
        partition(nodes, num);
        for (int i = 0; i < len - 1; i++) {
            nodes[i].next = nodes[i + 1];
        }
        nodes[len - 1].next = null;
        return nodes[0];
    }

    public static void partition(Node[] nodes, int num) {
        int less = -1;
        int more = nodes.length;
        int cur = 0;
        while (cur < more) {
            if (nodes[cur].value < num) {
                swap(nodes, ++less, cur++);
            } else if (nodes[cur].value > num) {
                swap(nodes, --more, cur);
            } else {
                cur++;
            }
        }
    }

    public static void swap(Node[] nodes, int i, int j) {
        Node tmp = nodes[i];
        nodes[i] = nodes[j];
        nodes[j] = tmp;
    }

    public static Node method02(Node head, int num) {
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

        while (cur != null) {
            next = cur.next;
            cur.next = null;

            if (cur.value < num) {
                if (sh == null) {
                    sh = cur;
                    st = cur;
                } else {
                    st.next = cur;
                    st = cur;
                }
            } else if (cur.value > num) {
                if (bh == null) {
                    bh = cur;
                    bt = cur;
                } else {
                    bt.next = cur;
                    bt = cur;
                }
            } else {
                if (eh == null) {
                    eh = cur;
                    et = cur;
                } else {
                    et.next = cur;
                    et = cur;
                }
            }

            cur = next;
        }

        if (st != null) {
            st.next = eh;
            et = et != null ? et : st;
        }

        if (et != null) {
            et.next = bh;
        }

        if (sh != null) {
            return sh;
        } else if (eh != null) {
            return eh;
        } else {
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

        printLinkedList(head);
//        Node head1 = method01(head, 5);
//        printLinkedList(head1); // 2 1 5 5 8 9 7
        Node head2 = method02(head, 5);
        printLinkedList(head2); // 1 2 5 5 7 9 8
    }
}
