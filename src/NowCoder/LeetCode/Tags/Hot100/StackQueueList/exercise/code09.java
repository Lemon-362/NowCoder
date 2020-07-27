package NowCoder.LeetCode.Tags.Hot100.StackQueueList.exercise;

/*
206. 反转单链表
    反转一个单链表

 */
public class code09 {
    public static class Node {
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    /*
    链表问题:
        反转单链表 ==> (1) 迭代遍历 (2) 递归

        TODO 递归法:
            (1) base case:
                head == null || head.next == null ==> 直接返回head
            (2) 首先递归到尾部, 此时递归函数中的head表示的是当前节点
                而遇到head.next==null就终止了, 所以head最终会停在倒数第二个节点处
                最后一个递归返回的是最后一个节点, 而这个递归是由倒数第二个节点调用的
                所以让此时的head(倒数第二个节点).next(最后一个节点).next(其next指针) = head(指向当前节点, 倒数第二个, 也就是前一个节点)
                然后让head.next=null置空
            (3) 最终都结束后, 返回最后一次递归的返回值
     */
    public static Node reverseList1(Node head){
        if (head == null){
            return null;
        }

        Node cur = head;
        Node next = null;
        Node pre = null;

        while (cur != null){
            next = cur.next;

            cur.next = pre;

            pre = cur;
            cur = next;
        }

        return pre;
    }

    public static Node reverseList2(Node head){
        // base case
        if (head == null || head.next == null){
            return head;
        }

        Node node = reverseList2(head.next);

        head.next.next = head;
        head.next = null;

        return node;
    }

    public static void printSingleLinkedList(Node head) {
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
        head.next.next.next = new Node(4);
        printSingleLinkedList(head);
        printSingleLinkedList(reverseList1(head));
//        1 2 3 4
//        4 3 2 1

    }
}
