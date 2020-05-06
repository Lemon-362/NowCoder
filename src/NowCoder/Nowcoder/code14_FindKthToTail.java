package NowCoder.Nowcoder;

import java.util.Stack;

/*
    输入一个链表，输出该链表中倒数第k个结点。
 */
public class code14_FindKthToTail {
    public static class ListNode {
        private int value;
        private ListNode next;

        public ListNode(int value) {
            this.value = value;
        }
    }

    // Stack
    public static ListNode method(ListNode head, int k){
        if (head == null || k < 1){
            return null;
        }
        int len = 0;
        Stack<ListNode> stack = new Stack<>();
        ListNode cur = head;
        while (cur != null){
            stack.push(cur);
            len++;
            cur = cur.next;
        }
        // 防止k越界
        if (k > len){
            return null;
        }
        while (k > 1){
            stack.pop();
            k--;
        }
        return stack.pop();
    }

    // 不用Stack
    public static ListNode method01(ListNode head, int k){
        if (head == null || k < 1){
            return null;
        }
        int len = 0;
        ListNode cur = head;
        while (cur != null){
            len++;
            cur = cur.next;
        }
        // 防止k越界
        if (k > len){
            return null;
        }
        cur = head;
        for (int i = 1; i <= len - k; i++) {
            cur = cur.next;
        }
        return cur;
    }

    // 快慢指针：倒数的问题转换成双指针，间隔k-1个
    public static ListNode method02(ListNode head, int k){
        if (head == null || k < 1){
            return null;
        }
        int len = 0;
        ListNode cur = head;
        while (cur != null){
            len++;
            cur = cur.next;
        }
        // 防止k越界
        if (k > len){
            return null;
        }
        ListNode p1 = head;
        ListNode p2 = head;
        // 让快指针p2先走k-1个
        while (k > 1){
            p2 = p2.next;
            k--;
        }
        // 快慢指针一起走
        while (p2.next != null){
            p1 = p1.next;
            p2 = p2.next;
        }
        return p1;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(3);
        head.next = new ListNode(1);
        head.next.next = new ListNode(4);
        head.next.next.next = new ListNode(7);
        head.next.next.next.next = new ListNode(2);

        ListNode res = method(head, 3);
        System.out.println(res.value); // 4

        ListNode res1 = method01(head, 3);
        System.out.println(res1.value);

        ListNode res2 = method02(head, 3);
        System.out.println(res2.value);
    }
}
