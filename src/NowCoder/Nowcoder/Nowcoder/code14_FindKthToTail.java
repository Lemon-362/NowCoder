package NowCoder.Nowcoder.Nowcoder;

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
    // TODO 需要遍历两遍
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

    /** 双指针(先后走)法
     *  思路:
     *      因为method01转换成了从头走n-k步, 就能到达倒数第k个位置
     *      所以双指针保证了同时走n-k步, 省去了计算总长度n的过程
     *
     * TODO 仅需遍历一遍
     *  需要考虑边界情况: 快指针p1在先走k-1步的过程中, 已经指向了null
     *
     * 双指针(先后走)的方法在相交节点basic_code_24中也用到了:
     *  无环相交的情况:
     *      两个链表, 想让p1和p2相遇, 已知两个链表的长度差为k
     *      那么先让长链表的p1先走k-1步
     *      然后p1和p2一起走, 必会相遇
     */
    public static ListNode method02(ListNode head, int k){
        if (head == null || k < 1){
            return null;
        }

        ListNode p1 = head;
        ListNode p2 = head;

        // 考虑边界条件
        while (k > 0 && p1 != null){
            p1 = p1.next;
            k--;
        }

        while (p1 != null){
            p1 = p1.next;
            p2 = p2.next;
        }

        return p2;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(3);
        head.next = new ListNode(1);
        head.next.next = new ListNode(4);
        head.next.next.next = new ListNode(7);
        head.next.next.next.next = new ListNode(2);

        ListNode res = method(head, 1);
        System.out.println(res.value);
        res = method(head, 2);
        System.out.println(res.value);
        res = method(head, 3);
        System.out.println(res.value);
        res = method(head, 4);
        System.out.println(res.value);
        res = method(head, 5);
        System.out.println(res.value);

        System.out.println("***********************");
        ListNode res1 = method01(head, 1);
        System.out.println(res1.value);
        res1 = method01(head, 2);
        System.out.println(res1.value);
        res1 = method01(head, 3);
        System.out.println(res1.value);
        res1 = method01(head, 4);
        System.out.println(res1.value);
        res1 = method01(head, 5);
        System.out.println(res1.value);

        System.out.println("***********************");
        ListNode res2 = method02(head, 1);
        System.out.println(res2.value);
        res2 = method02(head, 2);
        System.out.println(res2.value);
        res2 = method02(head, 3);
        System.out.println(res2.value);
        res2 = method02(head, 4);
        System.out.println(res2.value);
        res2 = method02(head, 5);
        System.out.println(res2.value);
    }
}
