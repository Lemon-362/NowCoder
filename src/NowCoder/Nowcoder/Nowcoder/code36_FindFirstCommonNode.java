package NowCoder.Nowcoder.Nowcoder;

/*
    输入两个链表，找出它们的第一个公共结点。
    （注意因为传入数据是链表，所以错误测试数据的提示是用其他方式显示的，保证传入数据是正确的）
    也就是找到两个链表的第一个相交节点
 */
public class code36_FindFirstCommonNode {
    public static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        if(pHead1 == null || pHead2 == null){
            return null;
        }
        ListNode loop1 = getLoopHead(pHead1);
        ListNode loop2 = getLoopHead(pHead2);

        if(loop1 == null && loop2 == null){
            return bothNoLoop(pHead1, pHead2);
        }
        if(loop1 != null && loop2 != null){
            return bothLoop(pHead1, pHead2, loop1, loop2);
        }
        return null;
    }

    public static ListNode getLoopHead(ListNode head){
        if(head == null){
            return null;
        }
        ListNode p1 = head.next;
        ListNode p2 = head.next.next;
        while(p1 != p2){
            if(p2.next == null || p2.next.next == null){
                return null;
            }
            p1 = p1.next;
            p2 = p2.next.next;
        }
        p2 = head;
        while(p1 != p2){
            p1 = p1.next;
            p2 = p2.next;
        }
        return p1;
    }

    public static ListNode bothNoLoop(ListNode head1, ListNode head2){
        ListNode p1 = head1;
        ListNode p2 = head2;
        int n = 0;
        while(p1.next != null){
            n++;
            p1 = p1.next;
        }
        while(p2.next != null){
            n--;
            p2 = p2.next;
        }
        p1 = n > 0 ? head1: head2;
        p2 = p1 == head1? head2: head1;
        n = Math.abs(n);
        while(n > 0){
            p1 = p1.next;
            n--;
        }
        while(p1 != p2){
            p1 = p1.next;
            p2 = p2.next;
        }
        return p1;
    }

    public static ListNode bothLoop(ListNode head1, ListNode head2, ListNode loop1, ListNode loop2){
        if(loop1 == loop2){
            ListNode p1 = head1;
            ListNode p2 = head2;
            int n = 0;
            while(p1 != loop1){
                n++;
                p1 = p1.next;
            }
            while(p2 != loop1){
                n--;
                p2 = p2.next;
            }
            p1 = n > 0 ? head1: head2;
            p2 = p1 == head1? head2: head1;
            n = Math.abs(n);
            while(n > 0){
                p1 = p1.next;
                n--;
            }
            while(p1 != p2){
                p1 = p1.next;
                p2 = p2.next;
            }
            return p1;
        }else {
            ListNode cur = loop1.next;
            while(cur != loop1){
                if(cur == loop2){
                    return loop1;
                }
                cur = cur.next;
            }
            return null;
        }
    }
}
