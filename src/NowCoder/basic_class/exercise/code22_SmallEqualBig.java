package NowCoder.basic_class.exercise;

/*
	给定一个头节点和一个值num，调整链表节点，实现左边均是小于num的，中间是等于num的，右边均是大于num的，顺序没有要求。
		1. 将节点放在数组中，用荷兰国旗问题调整，然后重新连接成链表
		——缺点：不稳定（调整前后节点前后顺序不一样）
		2. 稳定：要求调整前后节点前后顺序不变   三个链表头节点，断开后重连，最后三个头尾相接
 */
public class code22_SmallEqualBig {
    public static class Node {
        private int data;
        private Node next;

        public Node(int data) {
            this.data = data;
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

    public static void partition(Node[] nodes, int num){
        int less = -1;
        int more = nodes.length;
        int cur = 0;
        while (cur < more){
            if (nodes[cur].data < num){
                swap(nodes, ++less, cur++);
            }else if (nodes[cur].data > num){
                swap(nodes, --more, cur);
            }else {
                cur++;
            }
        }
    }

    public static void swap(Node[] nodes, int i, int j){
        Node node = nodes[i];
        nodes[i] = nodes[j];
        nodes[j] = node;
    }

    public static Node SmallEqualBig02(Node head, int num){
        if (head == null){
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
            if (cur.data < num){
                if (sh == null){
                    sh = cur;
                    st = cur;
                }else {
                    st.next = cur;
                    st = cur;
                }
            }else if (cur.data > num){
                if (bh == null){
                    bh = cur;
                    bt = cur;
                }else {
                    bt.next = cur;
                    bt = cur;
                }
            }else {
                if (eh == null){
                    eh = cur;
                    et = cur;
                }else {
                    et.next = cur;
                    et = cur;
                }
            }
            cur = next;
        }
        if (st != null){
            st.next = eh;
            et = et == null ? st: et;
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
            System.out.print(head.data + " ");
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
//        Node head1 = SmallEqualBig01(head, 5);
//        printLinkedList(head1); // 2 1 5 5 8 9 7
        Node head2 = SmallEqualBig02(head, 5);
        printLinkedList(head2); // 1 2 5 5 7 9 8
    }

}
