package NowCoder.Nowcoder.Nowcoder;

/*
    复制含有random的链表：
        输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，另一个特殊指针指向任意一个节点），
        返回结果为复制后复杂链表的head。
        （注意，输出结果中请不要返回参数中的节点引用，否则判题程序会直接返回空）
    基础课讲过
 */
public class code25_CopyListWithRandom {
    public class RandomListNode {
        int label;
        RandomListNode next = null;
        RandomListNode random = null;

        RandomListNode(int label) {
            this.label = label;
        }
    }

    public RandomListNode Clone(RandomListNode pHead) {
        if (pHead == null) {
            return null;
        }
        RandomListNode cur = pHead;
        RandomListNode next = null;
        while (cur != null) {
            next = cur.next;

            cur.next = new RandomListNode(cur.label);
            cur.next.next = next;

            cur = next;
        }
        // 先random后next
        cur = pHead;
        while (cur != null) {
            next = cur.next.next;

            cur.next.random = cur.random == null ? null : cur.random.next;

            cur = next;
        }
        cur = pHead;
        RandomListNode res = cur.next;
        RandomListNode copy = null;
        while (cur != null) {
            next = cur.next.next;
            copy = cur.next;

            cur.next = next;
            copy.next = cur.next == null ? null : cur.next.next;

            cur = next;
        }
        return res;
    }
}
