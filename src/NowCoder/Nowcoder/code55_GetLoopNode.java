package NowCoder.Nowcoder;

/*
    给一个链表，若其中包含环，请找出该链表的环的入口结点，否则，输出null。
 */
public class code55_GetLoopNode {
    public static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode EntryNodeOfLoop(ListNode pHead) {
        if (pHead == null || pHead.next == null) {
            return null;
        }
        ListNode p1 = pHead.next;
        ListNode p2 = pHead.next.next;
        while (p1 != p2) {
            if (p2.next == null || p2.next.next == null) {
                return null;
            }
            p1 = p1.next;
            p2 = p2.next.next;
        }
        p2 = pHead;
        while (p1 != p2) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p1;
    }
}
