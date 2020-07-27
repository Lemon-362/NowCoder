package NowCoder.LeetCode.Tags.Hot100.StackQueueList;

/*
19. 删除链表的倒数第K个节点
    给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。

 */
public class code02_RemoveKthNodeFromEnd {
    public static class Node {
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    /*
    链表问题:
        倒数第k个节点 ==> (1) 栈的方式/两次遍历 (2) 快慢双指针

        因为链表不能从后往前遍历, 所以首先需要知道链表节点个数, 然后再找到倒数第k个
        这样的话就需要遍历两次

        实际上, 两次遍历的目的就是为了能够从前往后走 len-k 步
        那么, 我们可以通过快慢双指针, 让快指针先走k步, 然后和慢指针一起走
        这样, 当快指针走到头时, 快指针走了 len-k 步, 那么慢指针正好也走 len-k 步
        此时慢指针就在倒数第k个节点处

        TODO 注意
            (1) 这里是让删除倒数第k个节点, 所以只能找它前一个节点的位置, 即倒数k+1个节点
            (2) 在删除倒数第k个节点的时候, 首先要判断他是否有next节点, 如果没有, 直接让其前一个结点指向null

        TODO 思想:
            在链表中, 如果让找倒数第k个节点, 可以使用快慢双指针, 让快/慢指针始终保持k的距离

     */
    public static Node removeKthNodeFromEnd(Node head, int k){
        if (head == null){
            return null;
        }

        Node newHead = new Node(Integer.MIN_VALUE);
        newHead.next = head;

        Node p1 = newHead;
        Node p2 = newHead;

        // 找倒数k+1个节点的位置
        while (k + 1 > 0){
            p2 = p2.next;
            k--;
        }

        while (p2 != null){
            p1 = p1.next;
            p2 = p2.next;
        }

        // 需要判断倒数第k个节点是否有next节点
        if (p1.next.next != null){
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
        Node res = removeKthNodeFromEnd(head, 1); // 1 2 3 4
        print(res);

    }

}
