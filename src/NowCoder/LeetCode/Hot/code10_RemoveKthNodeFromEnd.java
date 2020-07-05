package NowCoder.LeetCode.Hot;

/**
 * 删除链表的倒数第K个节点：
 *  给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 *
 * 先找到链表的倒数第k+1个节点，TODO 注意，这里要找到倒数第k个节点的前一个，才能将第k个节点删除
 * 然后将倒数第k个节点的前一个节点的next指向第k个节点的下一个，TODO 注意：边界情况，第k个节点的下一个为null
 *
 * TODO 先走k步可以保证快指针再走 len-k 步就到达null位置，此时慢指针也走了 len-k 步，到达了倒数第k个位置
 *  所以不管链表长度是多少，还是新增头节点，都只需要先走k步
 */
public class code10_RemoveKthNodeFromEnd {
    public static class Node {
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node removeKthNodeFromEnd(Node head, int k){
        if (head == null){
            return null;
        }

        Node newHead = new Node(Integer.MIN_VALUE);
        newHead.next = head;

        Node p1 = newHead;
        Node p2 = newHead;

        while (k >= 0){
            k--;
            p2 = p2.next;
        }

        while (p2 != null){
            p1 = p1.next;
            p2 = p2.next;
        }

        if (p1.next.next != null) {
            p1.next = p1.next.next;
        }else {
            p1.next = null;
        }

        Node res = newHead.next;
        newHead.next = null;

        return res;
    }

    public static void print(Node head){
        if (head == null){
            return;
        }

        Node cur = head;
        while (cur != null){
            System.out.print(cur.value + " ");
            cur = cur.next;
        }

        System.out.println();
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);

        print(head);
        Node res = removeKthNodeFromEnd(head, 2);
        print(res);

    }

}
