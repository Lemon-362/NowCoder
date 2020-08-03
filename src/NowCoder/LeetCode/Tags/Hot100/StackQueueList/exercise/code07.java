package NowCoder.LeetCode.Tags.Hot100.StackQueueList.exercise;

/*
148. 排序链表
    在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序

 */
public class code07 {
    public static class Node{
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node sortList(Node head){
        if (head == null){
            return null;
        }

        return mergeSort(head);
    }

    public static Node mergeSort(Node head){
        // base case
        if (head == null || head.next == null){
            return head;
        }

        Node mid = findMidNode(head);
        Node next = mid.next;
        mid.next = null;

        Node left = mergeSort(head);
        Node right = mergeSort(next);

        return merge(left, right);
    }

    public static Node merge(Node head1, Node head2){
        // base case
        if (head1 == null && head2 == null){
            return null;
        }else if (head1 == null){
            return head2;
        }else if (head2 == null){
            return head1;
        }

        Node head = null;

        if (head1.value < head2.value){
            head = head1;
            head.next = merge(head1.next, head2);
        }else {
            head = head2;
            head.next = merge(head1, head2.next);
        }

        return head;
    }

    public static Node findMidNode(Node head){
        if (head == null){
            return null;
        }

        Node p1 = head;
        Node p2 = head.next;

        while (p2 != null && p2.next != null){
            p1 = p1.next;
            p2 = p2.next.next;
        }

        return p1;
    }

    public static void printList(Node head) {
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
        Node head = new Node(4);
        head.next = new Node(2);
        head.next.next = new Node(1);
        head.next.next.next = new Node(3);
        head.next.next.next.next = new Node(5);

        Node newHead = sortList(head);

        printList(newHead);
    }

}
