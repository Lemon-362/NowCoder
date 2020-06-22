package NowCoder.Nowcoder.Additional;

/**
 * 在O(1)时间内删除链表中的节点
 *  前提: 假设要删除的节点在链表中
 *      因为需要O(N)时间才能判断是否在链表中
 *
 *  思路:
 *      单链表可以以O(1)得到其下一个节点,
 *      只需要将next的节点拷贝到要删除的节点, 然后将要删除节点的next指针指向next的节点的下一个节点即可
 *  特殊情况:
 *  (1) 要删除的是头节点
 *  (2) 要删除的节点是最后一个, 没有next的节点 ==> 只能遍历删除
 *
 *  平均时间复杂度: [O(N) + (N-1)*O(1)] / N = O(1)
 */
public class code02_deleteNodeByO1 {
    public static class Node {
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node deleteNode(Node head, Node node){
        // 特殊情况(1)
        if (node == head){
            return head.next;
        }
        // 特殊情况(2)
        if (node.next == null){
            Node cur = head;
            while (cur.next != node){
                cur = cur.next;
            }
            cur.next = null;
            return head;
        }else {
            node.value = node.next.value;
            node.next = node.next.next;
            return head;
        }
    }
    public static void print(Node head){
        while (head != null){
            System.out.print(head.value + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        Node node1 = new Node(2);
        Node node2 = new Node(3);
        Node node3 = new Node(4);
        head.next = node1;
        head.next.next = node2;
        head.next.next.next = node3;

        head = deleteNode(head, head);
        print(head);

        head = deleteNode(head, node3);
        print(head);
    }
}
