package NowCoder.Nowcoder;

/*
    输入两个单调递增的链表，输出两个链表合成后的链表
    当然我们需要合成后的链表满足单调不减规则。
 */
public class code16_MergeTwoLinkedList {
    // merge的思想，谁小移谁
    public static class ListNode {
        private int value;
        private ListNode next;

        public ListNode(int value) {
            this.value = value;
        }
    }

    public static ListNode Merge(ListNode list1, ListNode list2) {
        if (list1 == null && list2 == null) {
            return null;
        }
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        ListNode p1 = list1;
        ListNode p2 = list2;
        ListNode head = null;
        ListNode cur = null;
        while (p1 != null && p2 != null) {
            if (p1.value <= p2.value) {
                if (head == null) {
                    head = p1;
                    cur = p1;
                } else {
                    cur.next = p1;
                    cur = cur.next;
                }
                p1 = p1.next;
            } else {
                if (head == null) {
                    head = p2;
                    cur = p2;
                } else {
                    cur.next = p2;
                    cur = cur.next;
                }
                p2 = p2.next;
            }
        }
        // 由于链表是递增的，所以当有一个到尾时，直接将另一个剩余的挂在后面，保证是递增的
        if (p1 == null) {
            cur.next = p2;
        } else {
            cur.next = p1;
        }
        return head;
    }

    // 递归法
    public static ListNode Merge02(ListNode list1, ListNode list2) {
        if (list1 == null && list2 == null) {
            return null;
        }
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        if (list1.value <= list2.value) {
            list1.next = Merge02(list1.next, list2);
            return list1;
        } else {
            list2.next = Merge02(list1, list2.next);
            return list2;
        }
    }

    public static void main(String[] args) {
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(3);
        head1.next.next = new ListNode(10);
        head1.next.next.next = new ListNode(20);

        ListNode head2 = new ListNode(4);
        head2.next = new ListNode(5);
        head2.next.next = new ListNode(6);
        head2.next.next.next = new ListNode(7);

//        printList(Merge(head1, head2));
        printList(Merge02(head1, head2));
    }

    public static void printList(ListNode head) {
        if (head == null) {
            return;
        }
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
        System.out.println();
    }
}
