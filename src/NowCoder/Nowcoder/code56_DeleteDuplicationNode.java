package NowCoder.Nowcoder;

import java.util.Stack;

/*
    在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。
    例如，链表1->2->3->3->4->4->5 处理后为 1->2->5
 */
public class code56_DeleteDuplicationNode {
    public static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static ListNode deleteDuplication(ListNode pHead) {
        // 双指针，为了能够删除当前节点，必须记录前一个节点
        // 这里为了能够保证头节点也能与后面的比较，必须在头节点前加一个节点
        ListNode newHead = new ListNode(Integer.MIN_VALUE); // 因为是排序链表，所以设置为最小值
        newHead.next = pHead;
        ListNode cur = pHead;
        ListNode pre = newHead;
        while(cur != null){
            // 首先要cur.next不为空，才能比较
            if(cur.next != null && cur.val == cur.next.val){
                while(cur.next != null && cur.val == cur.next.val){
                    cur = cur.next;
                }
                // cur当前指向的是重复节点的最后一个，要让cur走向下一个非重复节点
                cur = cur.next;
                pre.next = cur; // 将pre指向非重复节点
            }else {
                pre = cur;
                cur = cur.next;
            }
        }
        ListNode res = newHead.next;
        newHead.next = null;
        return res;
    }

    public static void print(ListNode head){
        if (head == null){
            return;
        }
        while (head != null){
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(1);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(1);
        head.next.next.next.next = new ListNode(1);
        head.next.next.next.next.next = new ListNode(1);
        head.next.next.next.next.next.next = new ListNode(5);

        print(head);

        ListNode newHead = deleteDuplication(head);
        print(newHead);
    }
}
