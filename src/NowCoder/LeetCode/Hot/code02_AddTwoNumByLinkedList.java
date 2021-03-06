package NowCoder.LeetCode.Hot;

/**
 * 两数以链表的形式相加：
 *  给出两个 非空 的链表用来表示两个非负的整数。
 *  其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字
 *  如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和
 * 例如：
 *  输入：(2 -> 4 -> 3) + (5 -> 6)
 *  输出：7 -> 0 -> 4
 *  原因：342 + 65 = 407
 *
 * 思路：
 *  模拟加法的过程，用一个全局变量carry保存之前一次的进位结果
 *  TODO 注意：
 *      1. 两个链表可能不是等长的
 *      2. 可能最后一次加上后需要进位，那么需要在全部计算完后将进位1也连上去
 */
public class code02_AddTwoNumByLinkedList {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static ListNode addTwoNumbersByLinkedList(ListNode l1, ListNode l2) {

        ListNode p1 = l1;
        ListNode p2 = l2;

        int carry = 0;
        ListNode newHead = new ListNode(Integer.MIN_VALUE);
        ListNode pre = newHead;
        // 两个链表可能不一样长,必须将两个都遍历完
        while (p1 != null || p2 != null) {
            // 每次循环都初始化sum
            int sum = 0;
            if (p1 != null) {
                sum += p1.val;
                p1 = p1.next;
            }
            if (p2 != null) {
                sum += p2.val;
                p2 = p2.next;
            }
            // 加上进位结果
            sum += carry;
            // 如果sum>10,则需要进位,且sum只能取余
            carry = sum / 10;
            sum = sum % 10;
            // 新链表依次连接sum
            pre.next = new ListNode(sum);
            pre = pre.next;
        }
        // 有可能最后依次相加结果也需要进位,需要把进位补上
        if (carry == 1) {
            pre.next = new ListNode(carry);
        }

        ListNode res = newHead.next;
        newHead.next = null;

        return res;
    }

    public static void main(String[] args) {
        ListNode head1 = new ListNode(3);
        head1.next = new ListNode(6);
        head1.next.next = new ListNode(2);

        ListNode head2 = new ListNode(2);
        head2.next = new ListNode(7);

        ListNode head = addTwoNumbersByLinkedList(head1, head2);
        while (head != null){
            System.out.print(head.val + " "); // 7 0 4
            head = head.next;
        }
    }
}
