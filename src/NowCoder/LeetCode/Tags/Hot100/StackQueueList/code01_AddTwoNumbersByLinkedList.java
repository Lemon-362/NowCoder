package NowCoder.LeetCode.Tags.Hot100.StackQueueList;

/*
2. 两数以链表的形式相加：
    给出两个 非空 的链表用来表示两个非负的整数。
    其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字
    如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和

    例如：
        输入：(2 -> 4 -> 3) + (5 -> 6)
        输出：7 -> 0 -> 4
        原因：342 + 65 = 407
 */
public class code01_AddTwoNumbersByLinkedList {
    public static class Node {
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    /*
    链表问题: 双指针法
        p1和p2分别指向两个链表, 需要同时移动
        由于两个链表不是等长的, 所以需要先判断当前指针指向的节点是否为空
        然后计算两个指针的节点的和
        (1) sum>10, 需要模拟进位, 对sum取模作为和的节点
        (2) sum<10, 直接将sum作为和的节点
        (3) 如果p1或者p2走到头了, 那么此时另一个指针仍会继续走, 直到两个指针都走到头
        (4) 最后还要注意, 可能链表是等长的, 最后一个位置的节点和是>10的, 此时需要在最后加上进位的节点

     */
    public static Node addTwoNumbers(Node head1, Node head2){
        if (head1 == null && head2 == null){
            return null;
        }else if (head1 == null){
            return head2;
        }else if (head2 == null){
            return head1;
        }

        Node newHead = new Node(Integer.MIN_VALUE);
        Node cur = newHead;

        int carry = 0;
        Node p1 = head1;
        Node p2 = head2;

        // 循环条件是||,而不是&&, 是要让两个指针都走到头
        while (p1 != null || p2 != null){
            int sum = 0;
            if (p1 != null){
                sum += p1.value;
                p1 = p1.next;
            }
            if (p2 != null){
                sum += p2.value;
                p2 = p2.next;
            }

            // 首先要加上之前的进位结果
            sum += carry;

            carry = sum / 10;
            sum = sum % 10;

            cur.next = new Node(sum);
            cur = cur.next;
        }

        if (carry == 1){
            cur.next = new Node(carry);
        }

        Node res = newHead.next;
        newHead.next = null;

        return res;
    }

    public static void main(String[] args) {
        Node head1 = new Node(2);
        head1.next = new Node(4);
        head1.next.next = new Node(3);

        Node head2 = new Node(5);
        head2.next = new Node(6);
//        head2.next.next = new Node(4);

        Node head = addTwoNumbers(head1, head2);
        while (head != null){
            System.out.print(head.value + " "); // 7 0 4
            head = head.next;
        }
    }
}
